package seatechit.msbgateway.dbaccess.entity;

import java.util.ArrayList;

public class LogMessage {
	private XMLMessageLogInfo xmlMessageLogInfo;
	private String tradeMessageRequest = null;
	private String tradeMessageResponse = null;
	private ArrayList<HOSTMessagLogInfo> arrHOSTMessagLogInfo;

	public XMLMessageLogInfo getXMLMessageLogInfo() {
		return xmlMessageLogInfo;
	}

	public void setMessageInfo(XMLMessageLogInfo xmlMessageLogInfo) {
		this.xmlMessageLogInfo = xmlMessageLogInfo;
	}

	public String getTradeMessageRequest() {
		return tradeMessageRequest;
	}

	public void setTradeMessageRequest(String tradeMessageRequest) {
		this.tradeMessageRequest = tradeMessageRequest;
	}

	public String getTradeMessageResponse() {
		return tradeMessageResponse;
	}

	public void setTradeMessageResponse(String tradeMessageResponse) {
		this.tradeMessageResponse = tradeMessageResponse;
	}

	public void setArrHOSTMessagLogInfo(ArrayList<HOSTMessagLogInfo> arrHOSTMessagLogInfo) {
		this.arrHOSTMessagLogInfo = arrHOSTMessagLogInfo;
	}

	public ArrayList<HOSTMessagLogInfo> getArrHOSTMessagLogInfo() {
		return arrHOSTMessagLogInfo;
	}
}
