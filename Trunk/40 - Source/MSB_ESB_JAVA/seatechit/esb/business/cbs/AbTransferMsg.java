package seatechit.esb.business.cbs;

import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import seatechit.esb.utils.ElementUtils;
import seatechit.esb.utils.StringUtils;
import vn.com.msb.cnn.accounts.AS400Service;
import vn.com.msb.cnn.utils.Messages;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbXMLNSC;

public abstract class AbTransferMsg {
	
	protected AS400Service cbsService = new AS400Service();

	/**
	 * Build response message from return object of core (Message)
	 * 
	 * @param outMessage
	 * @param xmlPath
	 * @param retMessage
	 */
	
	
	
	protected void buildMsgFromReturnObject(MbMessage outMessage, String xmlPath, final Messages retMessage, final MessageInfo msgInf) {
		xmlPath = xmlPath.replace("/", "/?");
		if (retMessage != null) {
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$description[set-value('%s')]", retMessage.getDescription());
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$errCode[set-value('%s')]", retMessage.getErrCode());
			setArrStringUsingCDATA(outMessage, "arrString", StringUtils.arrayToString(retMessage.getArrString()));
			msgInf.rsp_code = retMessage.getErrCode();
			msgInf.rsp_msg = retMessage.getDescription();
		} else {
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$description[set-value('%s')]", "System Error");
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$errCode[set-value('%s')]", "-1");
			ElementUtils.setXMLElementValue(outMessage, xmlPath + "Messages/?$arrString[set-value('%s')]", "");
			msgInf.rsp_code = "-1";
			msgInf.rsp_msg = "System Error";

		}
	}
	
	private void setArrStringUsingCDATA(MbMessage outMessage, String tagName, String arrStringWithCDATA) {
		MbElement outBody;
		try {
			outBody = outMessage.getRootElement().getLastChild();
			outBody.getLastChild().getLastChild().getLastChild().getLastChild().getLastChild().createElementAsLastChild(MbXMLNSC.CDATA_FIELD, "arrString", arrStringWithCDATA);
		} catch (MbException e) {
			e.printStackTrace();
		}

	}
}
