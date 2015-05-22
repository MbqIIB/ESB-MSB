package seatechit.msbgateway.consumer;

import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import seatechit.msbgateway.dbaccess.entity.ServiceInfo;
import seatechit.msbgateway.proxy.emlgw.SendEmail_PortType;
import seatechit.msbgateway.proxy.emlgw.SendEmail_ServiceLocator;
import seatechit.msbgateway.utils.AppUtils;
import seatechit.msbgateway.utils.CachedParam;
import seatechit.msbgateway.utils.ElementUtils;
import seatechit.msbgateway.utils.Global;

import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbMessage;

/**
 * Enabled send email
 * 
 * @author Vu Trung Kien
 * @version 1.0
 * @since 1.6
 * 
 */
public class EmailConsumer extends BaseConsumer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final Logger logger = Logger.getLogger(EmailConsumer.class);
	final String CLASS_NAME = this.getClass().getName();
	private SendEmail_ServiceLocator emailService = null;
	private SendEmail_PortType emailPort;

	public EmailConsumer() {
		super();
		try {
			this.consumerType = Global.CONSUMER_CODE_EMAIL;
			ServiceInfo objService = CachedParam.getServiceInfoByServiceType(consumerType);
			this.emailService = new SendEmail_ServiceLocator();
			this.emailService.setsendEmailHttpPortEndpointAddress(objService.getService_url());
			this.emailService.setServiceTimeout(objService.getService_timeout() * 1000);
			this.emailPort = emailService.getsendEmailHttpPort();
		} catch (ServiceException e) {
		}
	}

	@Override
	public void submit(MbMessage inMessage, MbMessage outMessage) {
		try {
			this.retErrorInfo.setErrorType(Global.MESSAGE_TYPE_EMGW);
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
			String email_title = ElementUtils.getXMLElementInStringValue(inMessage, "/message/body/entity[@name='summary']/field[@name='email_title']");
			String email_content = ElementUtils.getXMLElementInStringValue(inMessage, "/message/body/entity[@name='summary']/field[@name='email_content']");
			List<MbElement> email_list = ElementUtils.getListOfXMLElement(inMessage, "/message/body/list[@name='records']/entity");
			String template = "IBContent2";
			for (int i = 0; i < email_list.size(); i++) {
				String[] receiveEmail = new String[email_list.size()];
				String elmPath = "/message/body/list[@name='records']/entity[@name='" + email_list.get(i).getFirstChild().getValueAsString() + "']/field[@name='email']";
				receiveEmail[i] = ElementUtils.getXMLElementInStringValue(inMessage, elmPath);

				this.hostMessageLogInfo.setSend_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				int errCode = ERR_SYSTEM_OK;
				emailPort.sendEmail(template, receiveEmail[i], "", email_title, email_content);
				this.hostMessageLogInfo.setReceive_time(AppUtils.now(Global.DEF_FORMAT_LOGMSG_DATE_TIME));
				this.retErrorInfo.setErrorCode(errCode);
				String xpathOutput = "/message/?body/?list[?@name[set-value('records')]]/?entity[?@name[set-value('" + email_list.get(i).getFirstChild().getValueAsString() + "')]]/?field[set-value('"
						+ retErrorInfo.getErrorCode() + "')][?@name[set-value('result')]]";
				outMessage.evaluateXPath(xpathOutput);

				rebuildTransactionInfo(Global.SIBS_033, Global.APP_DEFAULT_BRANCH_CODE);
				this.hostMessageLogInfo.setResp_code(String.valueOf(retErrorInfo.getErrorCode()));
				this.hostMessageLogInfo.setResp_msg(retErrorInfo.getErrorMessage());
				this.hostMessageLogInfo.setHostRequestMsg("[app_sender:" + app_sender + ", template:" + template + ", send_time:" + send_time + ", sequence_id:" + sequence_id + ", receiveEmail:"
						+ receiveEmail[i] + ", email_title:" + email_title + ", email_content:" + email_content + "]");
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
		} catch (Exception ex) {
			logger.error(new StringBuffer().append("Error source:").append(CLASS_NAME + ".submit()\n").append("Error message:").append(ex.toString() + "\n"));
			this.retErrorInfo.setErrorCode(Global.ERR_SYSTEM_UNKNOWN_ERROR);
			this.retErrorInfo.setErrorMessage(ex.toString());
		}
	}
}
