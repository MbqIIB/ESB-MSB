package vn.com.msb.cnn.accounts


class HostParameter {

	String name
	String value
	String param1
	String param2
		
    static constraints = {
		name(blank:false)
		value(blank:false)
		param1(nullable:true)
		param2(nullable:true)
    }
}
