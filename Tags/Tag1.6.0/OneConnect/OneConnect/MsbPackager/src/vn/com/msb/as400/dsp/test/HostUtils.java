package vn.com.msb.as400.dsp.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import vn.com.msb.as400.dsp.DSPMessageConstant;

class HostUtils {

	private static String getSocketIPServer(){
		String ipNumber = "";		
		ipNumber = "10.2.1.81"; //IAT
		
		return ipNumber;
	}

	public static Socket getSocket(int socketNumber){
		Socket sock = new Socket();
		try{
			InetAddress addr = InetAddress.getByName(getSocketIPServer());
			SocketAddress sockaddr = new InetSocketAddress(addr, socketNumber);

			int timeoutMs = 60000; // 60 seconds
			sock.connect(sockaddr, timeoutMs);
		}catch(UnknownHostException uhe){
			uhe.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return sock;
	}

	public static void releaseSocket(Socket sk) {
		try {
			if(sk != null && sk.isConnected()){
				sk.close();
			}
		} catch (Exception exs) {
			exs.printStackTrace();
		}
	}

	public static int detectMBASE(String code){
		if (DSPMessageConstant.MBSD_NAME.equals(code)) return 1;
		if (DSPMessageConstant.ABCS_NAME.equals(code)) return 2;
		return 0;
	}

//	public static def _convertOfflineMessage(messageArray){
//
//		//change header type, change schenario and transcode if is hold message
//		messageArray[0] = HostConstants.HEADER_TYPE_OFFLINE
//		messageArray[10] = HostConstants.HEADER_TYPE_OFFLINE
//		if(messageArray[26].indexOf(HostConstants.HOLD_TRANSCODE_ONLINE)!=-1){ //hold message
//			messageArray[26] = HostConstants.HOLD_TRANSCODE_OFFLINE
//			messageArray[45] = HostConstants.HOLD_TRANSCODE_OFFLINE
//			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE
//		}
//
//		if(messageArray[26].indexOf(HostConstants.UNHOLD_TRANSCODE_ONLINE)!=-1){ //unhold message
//			messageArray[26] = HostConstants.UNHOLD_TRANSCODE_OFFLINE
//			messageArray[45] = HostConstants.UNHOLD_TRANSCODE_OFFLINE
//			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE
//		}
//		
//		return messageArray
//	}
}
