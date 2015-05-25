package vn.com.msb.cnn.utils

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.log4j.Logger;

import com.ftl.util.StringUtil;

import vn.com.msb.as400.dsp.DSPConstants;
import vn.com.msb.as400.dsp.DSPMessageConstant;
import vn.com.msb.cnn.accounts.HostParameter;
import grails.util.Environment;

class HostUtils {

	private static def getSocketIPServer(){
		String ipNumber = ""
		switch(Environment.current) {
			case Environment.DEVELOPMENT:
							ipNumber = "10.2.1.82" //UAT
//				ipNumber = "10.2.1.81" //IAT
				break
			case Environment.TEST:
							ipNumber = "10.2.1.82" //UAT
//				ipNumber = "10.2.1.81" //IAT
				break
			case Environment.PRODUCTION:
				ipNumber = "10.2.1.1"
				break
		}
		return ipNumber
	}

	public static def getSocket(socketNumber){
		Socket sock = new Socket()
		InetAddress addr = InetAddress.getByName(getSocketIPServer())
		SocketAddress sockaddr = new InetSocketAddress(addr, socketNumber)

		int timeoutMs = 60000; // 60 seconds
		sock.connect(sockaddr, timeoutMs)

		return sock
	}

	public static def releaseSocket(Socket sk) {
		try {
			if(sk && sk.isConnected()){
				sk.close()
			}
		} catch (Exception exs) {
			Logger.getLogger(this).info ( exs.printStackTrace())
		}
	}

	public static def detectMBASE(code){
		if (DSPMessageConstant.MBSD_NAME.equals(code)) return 1
		if (DSPMessageConstant.ABCS_NAME.equals(code)) return 2
		return 0
	}

	synchronized public static def buildRefId(){
		//set hostdate
		if(Environment.current == Environment.PRODUCTION) {
			new HostUtils()._checkHostDate()
		}

		if(Environment.current == Environment.DEVELOPMENT) {
			new HostUtils()._checkHostDate()
		}

		//compare date with db and ref_id
		def hostParam = HostParameter.findByName("host_ref_id")
		char c = '0'
		int counter = hostParam.param1.length() + hostParam.value.length()
		//increase sequence
		_increaseSequence(hostParam)
		String refId = (new StringBuilder(hostParam.param1).append(hostParam.value).append(StringUtil.lpad(hostParam.param2, 15-counter,c))).toString() 
		return refId
	}

	private static def _increaseSequence(HostParameter hostParam){
//		def hostParam = HostParameter.findByName("host_ref_id")
		String currentSeq = hostParam.param2
		String nextSeq = Integer.parseInt(currentSeq)+1

		hostParam.param2 = nextSeq
		hostParam.save(flush:true)
	}

	private def _checkHostDate(){
		//set current date to yymmdd
		String currentDate = DateUtils.dateToYYMMDD(new Date())

		//compare date with db and ref_id
		def hostParam = HostParameter.findByNameAndValue("host_ref_id",currentDate)

		if(!hostParam){
			def paramObject = HostParameter.findByName("host_ref_id")
			//String prefix = paramObject.param1

			paramObject.value = currentDate
			paramObject.param2 = "1"
			paramObject.save(flush:true)
		}

	}

	public static def _convertOfflineMessage(messageArray){

		//change header type, change schenario and transcode if is hold message
		messageArray[0] = HostConstants.HEADER_TYPE_OFFLINE
		messageArray[10] = HostConstants.HEADER_TYPE_OFFLINE
		if(messageArray[26].indexOf(HostConstants.HOLD_TRANSCODE_ONLINE)!=-1){ //hold message
			messageArray[26] = HostConstants.HOLD_TRANSCODE_OFFLINE
			messageArray[45] = HostConstants.HOLD_TRANSCODE_OFFLINE
			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE
		}

		if(messageArray[26].indexOf(HostConstants.UNHOLD_TRANSCODE_ONLINE)!=-1){ //unhold message
			messageArray[26] = HostConstants.UNHOLD_TRANSCODE_OFFLINE
			messageArray[45] = HostConstants.UNHOLD_TRANSCODE_OFFLINE
			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE
		}
		
		return messageArray
	}
}
