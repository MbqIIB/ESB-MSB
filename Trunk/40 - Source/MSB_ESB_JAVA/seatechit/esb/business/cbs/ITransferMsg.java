package seatechit.esb.business.cbs;

import java.util.ArrayList;

import seatechit.esb.business.cbs.TCPOutFlow_SendReceiveAs400.MessageInfo;
import vn.com.msb.cnn.accounts.HostParameter;

import com.ibm.broker.plugin.MbMessage;

public interface ITransferMsg {
	 void buildMessage(final MbMessage inMessage, MbMessage outMessage, MessageInfo msgInf,ArrayList<HostParameter> paramList) ;
}


