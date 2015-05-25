package vn.com.msb.cnn.accounts

import java.util.Date;


class HostMessages {
//	static searchable = true
	
    String messageIn
	String messageOut
	Date sentTime
	String portNumber
	String channel
	int status
	String errCode
	String description	
	
	static constraints={
		messageIn(maxSize:5000,blank:false)
		messageOut(maxSize:5000)
		portNumber(blank:false)
		errCode(nullable:true)
		description(nullable:true)
		channel(nullable:true)
	}
	
}
