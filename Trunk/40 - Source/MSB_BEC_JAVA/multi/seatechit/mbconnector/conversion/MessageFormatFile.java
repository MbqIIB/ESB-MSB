package multi.seatechit.mbconnector.conversion;

import java.util.Hashtable;
import java.util.Properties;
import multi.seatechit.mbconnector.utils.Utils;

/**
 * 
 * @author Hungpd
 */
public class MessageFormatFile {

	private static Properties properties;
	private static final int MAX_FIELD = 253;
	private static int unRepeatedKey;

	public static int getTotalByte(String sFormatID) {
		int iTotalByte = 0;
		int iFormatIDExt = 1;
		String sFormatIDExt = "";
		boolean bEOR = true;
		try {
			label0: do {
				String sColumnValue = "";
				String sByte = "";
				int iFieldNo = 1;
				String formatMessage = properties.getProperty(new StringBuffer(sFormatID).append(sFormatIDExt).toString(), "not specified").trim();
				String[] fieldArr = formatMessage.split("\\|");
				for (int i = iFieldNo; i < fieldArr.length; i++) {
					if (iFieldNo > MAX_FIELD) {
						sFormatIDExt = "-".concat(String.valueOf(String.valueOf(String.valueOf(iFormatIDExt))));
						iFormatIDExt++;
						bEOR = false;
						continue label0;
					}
					sColumnValue = fieldArr[i].trim();
					if (sColumnValue != null && !sColumnValue.trim().equals("EOR") && !sColumnValue.trim().equals("")) {
						sByte = Utils.getToken(sColumnValue, 2, ',');
						if (!sByte.equals(""))
							iTotalByte += Integer.parseInt(sByte.trim());
					} else {
						bEOR = true;
						continue label0;
					}
					iFieldNo++;
				}
				bEOR = true;
			} while (!bEOR);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return iTotalByte;
	}

	public static Hashtable getFieldInformation(String sFormatID) {
		Hashtable htFieldInformation = new Hashtable();
		String sFormatIDExt = "";
		String sSendData = "";
		int iFormatIDExt = 1;
		int iCurrentFieldNo = 1;
		boolean bEOR = true;
		try {
			label0: do {
				String formatMessage = properties.getProperty(new StringBuffer(sFormatID).append(sFormatIDExt).toString(), "not specified").trim();
				String[] fieldArr = formatMessage.split("\\|");
				String sColumnName = "";
				String sColumnValue = "";
				if (iCurrentFieldNo == 1) {
					String sUnRepeatKey = fieldArr[0].trim();
					htFieldInformation.put("UNREPEATKEY", sUnRepeatKey);
					unRepeatedKey = Integer.parseInt(sUnRepeatKey);
				}
				for (int iFieldNo = 1; iFieldNo < fieldArr.length; iFieldNo++) {
					if (iFieldNo > MAX_FIELD) {
						sFormatIDExt = "-".concat(String.valueOf(String.valueOf(String.valueOf(iFormatIDExt))));
						iFormatIDExt++;
						bEOR = false;
						continue label0;
					}
					sColumnValue = fieldArr[iFieldNo].trim();
					if (sColumnValue != null && !sColumnValue.trim().equals("EOR") && !sColumnValue.trim().equals("")) {
						sColumnName = "FIELD".concat(String.valueOf(String.valueOf(String.valueOf(iCurrentFieldNo))));
						htFieldInformation.put(sColumnName, sColumnValue);
					} else {
						bEOR = true;
						continue label0;
					}
					iCurrentFieldNo++;

				}

				bEOR = true;
			} while (!bEOR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htFieldInformation;
	}

	public String processMessage(Hashtable hashTableFormat, String sFormatID) {

		String formatMessage = "";
		try {

			String sFormatIDExt = "";
			int iFormatIDExt = 1;
			int iCurrentFieldNo = 1;
			boolean bEOR = true;
			label0: do {
				formatMessage = properties.getProperty(new StringBuffer(sFormatID).append(sFormatIDExt).toString(), "not specified").trim();
				String[] fieldArr = formatMessage.split("\\|");
				String sColumnValue = "";
				for (int iFieldNo = 1; iFieldNo <= fieldArr.length; iFieldNo++) {
					if (iFieldNo > MAX_FIELD) {
						sFormatIDExt = "-".concat(String.valueOf(String.valueOf(String.valueOf(iFormatIDExt))));
						iFormatIDExt++;
						bEOR = false;
						continue label0;
					}
					sColumnValue = fieldArr[iFieldNo].trim();
					if (sColumnValue != null && !sColumnValue.trim().equals("EOR") && !sColumnValue.trim().equals("")) {
						formatMessage = formatMessage.replaceAll(sColumnValue, Utils.getToken(sColumnValue, 4, ','));
					} else {
						bEOR = true;
						continue label0;
					}
					iCurrentFieldNo++;
				}
				bEOR = true;
			} while (!bEOR);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return formatMessage;
	}

	public static String getRcvCode(String sSendCode) {
		String sRcv = null;
		// try
		// {
		// if(rs != null)
		// {
		// rs.close();
		// rs = null;
		// }
		// String sSQL = String.valueOf(String.valueOf((new
		// StringBuffer("SELECT RCVCODE FROM TRANCODE WHERE SENDCODE ='")).append(sSendCode).append("'")));
		// Statement statement = conn.createStatement();
		// rs = statement.executeQuery(sSQL);
		// if(rs.next())
		// sRcv = rs.getString("RCVCODE");
		if (sRcv == null)
			sRcv = "";
		// }
		// catch(SQLException sqle)
		// {
		// sqle.printStackTrace();
		// }
		return sRcv;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public MessageFormatFile(Properties newProperties) {
		properties = newProperties;
	}

	protected boolean isUnRepeatedKey() {
		return unRepeatedKey > 0;
	}

	public static int getUnRepeatedKey() {
		return unRepeatedKey;
	}

}
