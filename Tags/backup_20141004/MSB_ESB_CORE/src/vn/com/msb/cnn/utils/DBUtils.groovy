package vn.com.msb.cnn.utils


import vn.com.msb.cnn.accounts.HostMessages;

class DBUtils {

//	def sessionFactory
	private static propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP

	public static int MESSAGE_OK = 0

	public static int MESSAGE_FAILS = 1

	public static int MESSAGE_NULL = 2

	public static def _setHostMessagesValue(portNumber,messageIn,messageOut,status,errorCode,description, channel){

		def message = new HostMessages()
		message.messageIn = messageIn
		message.messageOut = messageOut
		//		message.messageIn = ""
		//		message.messageOut = ""
		message.status = status
		message.errCode = errorCode
		message.description = description
		message.portNumber = portNumber
		message.sentTime = new Date()
		message.channel = channel

//		if(count % 5 == 0 ){
//			println "flush here"
//			cleanUpGorm()
//			count = 0
//		}else{
//			count ++
//		}
		boolean result = message.save(flush:true)
		
		propertyInstanceMap.get().clear()
		
		return result
	}


//	def cleanUpGorm() {
//		def session = sessionFactory.currentSession
//		session.flush()
//		session.clear()
//		propertyInstanceMap.get().clear()
//	}
}
