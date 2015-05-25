package vn.com.msb.cnn.processor;

import java.net.Socket;
import java.util.Date;
import org.apache.log4j.Logger;

import vn.com.msb.as400.dsp.DSPField;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.as400.dsp.DSPPackager;
import vn.com.msb.cnn.accounts.HostParameter;
import vn.com.msb.cnn.utils.AppUtils;
import vn.com.msb.cnn.utils.DBUtils;
import vn.com.msb.cnn.utils.DateUtils;
import vn.com.msb.cnn.utils.ErrorConstants;
import vn.com.msb.cnn.utils.Global;
import vn.com.msb.cnn.utils.HostConstants;
import vn.com.msb.cnn.utils.HostUtils;
import vn.com.msb.cnn.utils.Messages;
import vn.com.msb.cnn.utils.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;

public class HostProcessor {
	static {
		try {
			DOMConfigurator.configure(Global.APP_LOG_FILEPATH);
		} catch (Exception ex) {
			try {
				throw ex;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public static Messages sendMessage(int socketNumber, String[] messageArray, String channel, String hostIP) {

		Messages messages = null;
		try {

			String seq = DateUtils.seqDateTime(new Date());

			// String[] strValue = message.split(HostConstants.separator)
			// TODO Detect and convert off-line messages if exist //check
			// offline
			// HostParameter hostParam = in_hostParam;//
			// HostParameter.findByNameAndValue("offline",
			HostParameter hostParam = null; // "on");
			if (hostParam != null) {
				messageArray = HostUtils._convertOfflineMessage(messageArray);
				socketNumber = Integer.parseInt(hostParam.getParam1());
			}

			Logger.getLogger(HostProcessor.class).info("Input Mess:" + AppUtils.convertArrayToString(messageArray));

			int value = HostUtils.detectMBASE(messageArray[HostConstants.DSP_HEADER_CODE]);
			if (value == 1)
				messages = sendMBASE(socketNumber, messageArray, seq, hostIP);
			else if (value == 2)
				messages = sendABCS(socketNumber, messageArray, seq, hostIP);
			else if (value == 0)
				messages = null;

			// set to DB
			if (messages == null) {
				DBUtils._setHostMessagesValue(String.valueOf(socketNumber), seq, "", DBUtils.MESSAGE_NULL, HostConstants.HOST_MESSAGE_NULL_CODE,
						HostConstants.HOST_MESSAGE_NULL_DESCRIPTION, channel);

				Logger.getLogger(HostProcessor.class).info(
						"Err Mess:  " + seq + ":" + "" + HostConstants.HOST_MESSAGE_NULL_CODE + HostConstants.HOST_MESSAGE_NULL_DESCRIPTION + channel);
			} else {

				boolean isOK = messages.getErrCode() == null ? true : false;
				// TODO: kienvt save message to db
				// isOK=DBUtils._setHostMessagesValue(String.valueOf(socketNumber),seq,"",
				// DBUtils.MESSAGE_OK, "", "",channel):
				// DBUtils._setHostMessagesValue(String.valueOf(socketNumber),seq,"",
				// DBUtils.MESSAGE_FAILS,messages.getErrCode(),messages.getDescription(),
				// channel);
			}

		} catch (Exception ex) {
			Logger.getLogger(HostProcessor.class).info(ex.toString());
		} finally {
			return messages;
		}
	}

	/*
	 * public static def sendTFMessage(int socketNumber, String[] messageArray,
	 * String channel){
	 * 
	 * Messages messages = null try{ Logger.getLogger(this).info ("Input Mess:"
	 * +messageArray)
	 * 
	 * // String[] strValue = message.split(HostConstants.separator) int value =
	 * HostUtils.detectMBASE(messageArray[HostConstants.DSP_HEADER_CODE])
	 * 
	 * if( value == 1) messages = sendTFMBASE(socketNumber,messageArray) else
	 * if(value == 2) messages = sendABCS(socketNumber,messageArray) else
	 * if(value == 0) messages = null
	 * 
	 * //set to DB // if(messages == null){ //
	 * DBUtils._setHostMessagesValue(String.valueOf(socketNumber),
	 * StringUtils.arrayToString (messageArray), "", DBUtils.MESSAGE_NULL,
	 * HostConstants.HOST_MESSAGE_NULL_CODE,
	 * HostConstants.HOST_MESSAGE_NULL_DESCRIPTION, channel) // }else{ //
	 * boolean isOK = messages.errCode == null?true:false //
	 * isOK?DBUtils._setHostMessagesValue(String.valueOf(socketNumber),
	 * StringUtils.arrayToString (messageArray),
	 * StringUtils.arrayToString(messages.getArrString()), DBUtils.MESSAGE_OK,
	 * "", "",
	 * channel):DBUtils._setHostMessagesValue(String.valueOf(socketNumber
	 * ),StringUtils.arrayToString (messageArray),
	 * StringUtils.arrayToString(messages.getArrString()),
	 * DBUtils.MESSAGE_FAILS, messages.getErrCode(), messages.getDescription(),
	 * channel) // } }catch(Exception ex){ Logger.getLogger(this).info
	 * (ex.printStackTrace()) }finally{ return messages } }
	 * 
	 * private static def sendTFMBASE(int socketNumber, String[] message){
	 * String[] strReturn = null
	 * 
	 * Socket sk = null Messages messages = null try { sk =
	 * HostUtils.getSocket(socketNumber)
	 * 
	 * DSPPackager mbasePackage =
	 * DSPPackager.mapDSPPackager.get(DSPMessageConstant.MBSD_NAME +
	 * message[HostConstants.DSP_MBASE_TF_REQUEST_POSTION]+"I")
	 * 
	 * if(mbasePackage == null){ return messages } Logger.getLogger(this).info
	 * ("SIBS requested: " + StringUtils.arrayToString (message))
	 * 
	 * // Packaging message byte[] btRollback = mbasePackage.pack(message) //
	 * Put data to Host sk.getOutputStream().write(btRollback)
	 * 
	 * // Get response from Host byte[] btResponse = (byte[])
	 * DSPPackager.loadDSPMessage(sk.getInputStream())
	 * 
	 * // Unpacking and check the message status DSPPackager mbsdPackager =
	 * DSPPackager.getMBSDResponsePackager(btResponse, mbasePackage) DSPField[]
	 * fld = mbsdPackager.getFieldDefinitionList() String strResponse =
	 * mbsdPackager.unpackMultiRecords(btResponse, DSPMessageConstant.MBSD_NAME,
	 * HostConstants.localSeparator)
	 * 
	 * //set object return Logger.getLogger(this).info ("SIBS responsed: " +
	 * strResponse) strReturn = strResponse.split(HostConstants.separator)
	 * messages = new Messages() messages.setArrString strReturn
	 * 
	 * if
	 * (!strReturn[DSPMessageConstant.IDX_MESSAGE_STATUS].equals(DSPMessageConstant
	 * .STATUS_SUCCESS)) { // Control Error here messages.setDescription
	 * strReturn[HostConstants.MBASE_ERROR_DESCRIPTION] messages.setErrCode
	 * strReturn[HostConstants.MBASE_ERROR_CODE] } } catch (Exception ex) {
	 * Logger.getLogger(this).info (ex.printStackTrace()) } finally {
	 * HostUtils.releaseSocket(sk) return messages } }
	 */
	@SuppressWarnings("finally")
	private static Messages sendMBASE(int socketNumber, String[] message, String seq, String hostIP) {
		String[] strReturn = null;

		Socket sk = null;
		Messages messages = null;
		try {
			sk = HostUtils.getSocket(socketNumber, hostIP);

			DSPPackager mbasePackage = DSPPackager.mapDSPPackager.get(DSPMessageConstant.MBSD_NAME + message[HostConstants.DSP_MBASE_REQUEST_POSTION] + "I");

			if (mbasePackage == null) {
				return messages;
			}
			Logger.getLogger(HostProcessor.class).info("SIBS requested " + seq + ":" + StringUtils.arrayToString(message));

			// Packaging message
			byte[] btRollback = mbasePackage.pack(message);
			// Put data to Host
			sk.getOutputStream().write(btRollback);

			// Get response from Host
			byte[] btResponse = (byte[]) DSPPackager.loadDSPMessage(sk.getInputStream());

			// Unpacking and check the message status
			DSPPackager mbsdPackager = DSPPackager.getMBSDResponsePackager(btResponse, mbasePackage);
			DSPField[] fld = mbsdPackager.getFieldDefinitionList();
			String strResponse = mbsdPackager.unpackMultiRecords(btResponse, DSPMessageConstant.MBSD_NAME, HostConstants.localSeparator);

			// set object return
			Logger.getLogger(HostProcessor.class).info("SIBS responsed " + seq + ":" + strResponse);
			strReturn = strResponse.split(HostConstants.separator);
			messages = new Messages();
			messages.setArrString(strReturn);
			messages.setRequestMsg(StringUtils.arrayToString(message));
			messages.setResponseMsg(strResponse);

			// kienvt: add them response/request msg
			messages.setRequestMsg(StringUtils.arrayToString(message));
			messages.setResponseMsg(strResponse);
			if (!strReturn[DSPMessageConstant.IDX_MESSAGE_STATUS].equals(DSPMessageConstant.STATUS_SUCCESS)) {
				// Control Error here
				messages.setDescription(strReturn[HostConstants.MBASE_ERROR_DESCRIPTION]);
				messages.setErrCode(strReturn[HostConstants.MBASE_ERROR_CODE]);

			} else {
				// Kienvt: add them
				messages.setErrCode("0");
				messages.setDescription("System Ok");
			}
		} catch (Exception ex) {
			Logger.getLogger(HostProcessor.class).info(ex.toString());
		} finally {
			HostUtils.releaseSocket(sk);
			return messages;
		}
	}

	@SuppressWarnings("finally")
	private static Messages sendABCS(int socketNumber, String[] message, String seq, String hostIP) {
		String[] strReturn = null;

		Socket sk = null;
		Messages messages = null;
		try {
			sk = HostUtils.getSocket(socketNumber, hostIP);

			DSPPackager mbasePackage = DSPPackager.mapDSPPackager.get(DSPMessageConstant.ABCS_NAME + HostConstants.ABCS_BODY_NAME);

			if (mbasePackage == null) {
				messages = null;
				return messages;
			}
			// println "start: " + new Date()
			Logger.getLogger(HostProcessor.class).info("SIBS requested:  " + seq + ":" + StringUtils.arrayToString(message));

			// Packaging message
			byte[] btRollback = mbasePackage.pack(message);
			// Put data to Host
			sk.getOutputStream().write(btRollback);

			// Get response from Host
			byte[] btResponse = DSPPackager.loadDSPMessage(sk.getInputStream());
			// println "end: " +new Date()

			// Unpacking and check the message status
			DSPPackager mbsdPackager = DSPPackager.getABCSResponsePackager(btResponse);
			DSPField[] fld = mbsdPackager.getFieldDefinitionList();
			String strResponse = mbsdPackager.unpackMultiRecords(btResponse, DSPMessageConstant.ABCS_NAME, HostConstants.localSeparator);
			// String strResponse = mbsdPackager.unpack(btResponse,
			// HostConstants.localSeparator)

			// set object return
			Logger.getLogger(HostProcessor.class).info("SIBS responsed:  " + seq + ":" + strResponse);
			strReturn = strResponse.split(HostConstants.separator);
			messages = new Messages();
			messages.setArrString(strReturn);
			messages.setRequestMsg(StringUtils.arrayToString(message));
			messages.setResponseMsg(strResponse);

			if (!strReturn[DSPMessageConstant.IDX_MESSAGE_STATUS].equals(DSPMessageConstant.STATUS_SUCCESS)) {
				// Control Error here
				messages.setErrCode(strReturn[HostConstants.ABCS_ERROR_CODE]);
				messages.setDescription(ErrorConstants.getError(strReturn[HostConstants.ABCS_ERROR_CODE]));

			} else if (message[47].endsWith("1321")) {// check transfer
														// internal??
				String[] destArray = new String[45];
				System.arraycopy(strReturn, 0, destArray, 0, 44);
				messages.setArrString(destArray);
				messages.setErrCode("0");
				messages.setDescription("System Ok");
				Logger.getLogger(HostProcessor.class).info(destArray);
			} else {
				// kienvt: add them response/request msg
				messages.setErrCode("0");
				messages.setDescription("System Ok");
			}
		} catch (Exception ex) {
			Logger.getLogger(HostProcessor.class).info(ex.toString());
		} finally {
			HostUtils.releaseSocket(sk);
			return messages;
		}
	}
}
