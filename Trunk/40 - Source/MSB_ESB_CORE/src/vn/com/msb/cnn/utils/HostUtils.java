package vn.com.msb.cnn.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.log4j.Logger;

import vn.com.msb.as400.dsp.DSPMessageConstant;

public class HostUtils {

	public static Socket getSocket(int socketNumber, String ip) {
		Socket sock = null;
		try {
			sock = new Socket();
			InetAddress addr = InetAddress.getByName(ip);
			SocketAddress sockaddr = new InetSocketAddress(addr, socketNumber);
			int timeoutMs = 60000; // 60 seconds
			sock.connect(sockaddr, timeoutMs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sock;
	}

	public static void releaseSocket(Socket sk) {
		try {
			if (sk != null && sk.isConnected()) {
				sk.close();
			}
		} catch (Exception exs) {
			Logger.getLogger(HostUtils.class).info(exs.toString());
		}
	}

	public static int detectMBASE(String code) {
		if (DSPMessageConstant.MBSD_NAME.equals(code))
			return 1;
		if (DSPMessageConstant.ABCS_NAME.equals(code))
			return 2;
		return 0;
	}

	public static String[] _convertOfflineMessage(String[] messageArray) {

		// change header type, change scenario and tran code if is hold message
		messageArray[0] = HostConstants.HEADER_TYPE_OFFLINE;
		messageArray[10] = HostConstants.HEADER_TYPE_OFFLINE;
		if (messageArray[26].indexOf(HostConstants.HOLD_TRANSCODE_ONLINE) != -1) { // hold
			// message
			messageArray[26] = HostConstants.HOLD_TRANSCODE_OFFLINE;
			messageArray[45] = HostConstants.HOLD_TRANSCODE_OFFLINE;
			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE;
		}

		if (messageArray[26].indexOf(HostConstants.UNHOLD_TRANSCODE_ONLINE) != -1) { // unhold
			// message
			messageArray[26] = HostConstants.UNHOLD_TRANSCODE_OFFLINE;
			messageArray[45] = HostConstants.UNHOLD_TRANSCODE_OFFLINE;
			messageArray[17] = HostConstants.HOLD_SCHENARIO_METHOD_OFFLINE;
		}

		return messageArray;
	}
}
