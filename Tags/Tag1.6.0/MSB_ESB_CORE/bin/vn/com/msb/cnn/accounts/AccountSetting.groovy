package vn.com.msb.cnn.accounts


class AccountSetting {
	static mapping = {
		table 'settings'
	}
	
	long sequence
	String branch_code
	String group_code
	String currency_code	
	long sequence_limit
	String param1 = ""
	String param2 = ""
	
	static constraints={		
		branch_code(maxSize:10,blank:false, unique:['group_code','currency_code'])		
		group_code(blank:false,unique:['branch_code','currency_code'])
		currency_code(blank:false,unique:['branch_code','group_code'])
		param1(maxSize:40,nullable:true)
		param2(maxSize:40,nullable:true)
	}
}
