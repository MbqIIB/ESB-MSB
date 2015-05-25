package seatechit.msbgateway.consumer;

import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.smsgw.InternalGW;
import seatechit.msbgateway.proxy.smsgw.InternalGWServiceLocator;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessage;

/**
 * Enabled send SMS message
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 */
public class SMSConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(SMSConsumer.class);
	final String CLASS_NAME = this.getClass().getName();
	private InternalGWServiceLocator smsService;
	private InternalGW smsPort;

	public SMSConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_SMS;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			smsService = new InternalGWServiceLocator();
			smsService.setInternalGWPortEndpointAddress(objService.getService_url());
			smsService.setServiceTimeout(objService.getService_timeout() * 1000);
			smsPort = smsService.getInternalGWPort();
		} catch (ServiceException e) {
		}
	}

	@Override
	public void submit(final MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_SMGW);
			this.inMessage = inMessage;
			this.outMessage = outMessage;

			this.baseXmlRequestDoc = ElementUtils.getXMLContent(inMessage);
			this.buildLogOfRequestMessage();

			String tran_code = xmlMessageLogInfo.getTran_code();
			String tran_service_code = xmlMessageLogInfo.getTran_service_code();
			buildTransactionInfo(consumerType, tran_code, tran_service_code);
			this.hostMessageLogInfo.setTran_sn(xmlMessageLogInfo.getSender_tran_sn());

			// 2)Send email
			String app_sender = Global.APP_SENDER;
			String send_time = AppUtils.now(Global.DEF_FORMAT_DATE_YYYYMMDD);
			String sequence_id = ElementUtils.getXMLElementInStringValue(inMessage, Global.XML_HDR_FLD_SENDER_TRAN_SN);

			String sms_content = ElementUtils.getXMLElementInStringValue(inMessage, "/message/body/entity[@name='summary']/field[@name='sms_content']");
			List<MbElement> mobile_list = ElementUtils.getListOfXMLElement(inMessage, "/message/body/list[@name='records']/entity");

			for (int i = 0; i < mobile_list.size(); i++) {
				String[] receiveNumber = new String[1];
				receiveNumber[i] = ((MbElement) mobile_list.get(i)).getValueAsString();

				java.lang.String supply_id = "MSBBO";
				java.lang.String datetime = send_time;
				int priority = 1;

				this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				smsPort.sendSMS(sequence_id, supply_id, receiveNumber, sms_content, datetime, priority, app_sender);
				this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));

				this.retErrorInfo.setErrorCode(ERR_SYSTEM_OK);
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]/?entity[?@name[set-value('" + mobile_list.get(i).getFirstChild().getValueAsString()
						+ "')]]/?$field[set-value('" + retErrorInfo.getErrorCode() + "')][?@name[set-value('result')]]";
				outMessage.evaluateXPath(xpathOutput);

				rebuildTransactionInfo(Global.SIBS_032, Global.APP_DEFAULT_BRANCH_CODE);
				this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
				this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
				this.hostMessageLogInfo.setHostRequestMsg("[app_sender:" + app_sender + ", send_time:" + send_time + ", sequence_id:" + sequence_id + ", receiveNumber:" + receiveNumber[i]
						+ ", sms_content:" + sms_content + "]");
				this.hostMessageLogInfo.setHostResponseMsg(retErrorInfo.toString());
				this.arrRetHOSTMessageLogInfo.add(hostMessageLogInfo);

			}

			// 3)add more detail response time,date....
			this.buildLogOfResponseMessage();
			this.xmlMessageLogInfo.setExecution_date(hostMessageLogInfo.getHost_tran_date());
			this.xmlMessageLogInfo.setRef_tran_no(hostMessageLogInfo.getHost_tran_sn());
			this.buildXMLMessageResponseHeader();
			this.baseXmlResponseDoc = ElementUtils.getXMLContent(outMessage);
			this.buildLogMessageInfo();
			this.logMessageFacade.insertLogMessage(logMessage);
			// System.out.println(getLogTradeMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".submit()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}

	}
}
