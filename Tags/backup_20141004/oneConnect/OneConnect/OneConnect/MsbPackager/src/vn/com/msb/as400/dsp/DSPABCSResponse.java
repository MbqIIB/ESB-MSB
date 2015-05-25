package vn.com.msb.as400.dsp;


public class DSPABCSResponse {

	private String strBody;
	private DSPField[] dspField;
	
	public String getStrBody() {
		return strBody;
	}
	public void setStrBody(String strBody) {
		this.strBody = strBody;
	}
	public DSPField[] getDspField() {
		return dspField;
	}
	public void setDspField(DSPField[] dspField) {
		this.dspField = dspField;
	}
	
//	public DSPPackager getABCSResponsePackager(byte[] btResponse)	throws Exception {
//	// Get Header
//	String strDSPHeader = DSPPackager.ABCSHEADERPACKAGER
//			.getFieldDefinitionList()[DSPMessageConstant.DSP_DATA_FORMAT_ID]
//			.unpack(btResponse);
//	String strResponseFormatCode = null;
//	HashMap<String, String> hMap = new HashMap<String, String>();
//
//	int iCurrentBytePos = 319;
//	int iTempTotalByteReceive = btResponse.length - iCurrentBytePos;
//	int iResponseLength = DSPPackager.ABCSRESPONSELENGTH.getOffset();
//	int count = 0;
//
//	if (strDSPHeader.equals(DSPMessageConstant.ABCS_NAME)) {
//		//
//		for (; iTempTotalByteReceive > 0; iTempTotalByteReceive -= iResponseLength) {
//			//
//
//			DSPPackager.ABCSRESPONSEFORMATCODE.setOffset(iCurrentBytePos + 2);
//			DSPPackager.ABCSRESPONSELENGTH.setOffset(iCurrentBytePos);
//
//			strResponseFormatCode = DSPPackager.ABCSRESPONSEFORMATCODE
//					.unpack(btResponse);
//			count++;
//			iResponseLength = Integer.parseInt(DSPPackager.ABCSRESPONSELENGTH
//					.unpack(btResponse)) + 2;
//
//			hMap.put(String.valueOf(count), strResponseFormatCode);
//			iCurrentBytePos += iResponseLength;
//		}
//	}
//	return DSPPackager.createABCSPackager(strDSPHeader, hMap);
//
//	}
	
}
