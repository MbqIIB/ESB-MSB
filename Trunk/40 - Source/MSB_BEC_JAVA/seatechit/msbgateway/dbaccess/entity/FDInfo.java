package seatechit.msbgateway.dbaccess.entity;

import seatechit.msbgateway.utils.CoreBankUtils;

public class FDInfo {
	private String	group_acct ="";
	private String	receipt_no="";
	private String	receipt_product_code="";
//	private String	fd_receipt_code ="";
	private String	interest_rate ="";
	private String	original_balance="";
	private String	issue_date ="";
	private String	maturity_date ="";
	private String	accurated_interest=""; 
	private String	cur_type ="";
	private String	cd_term ="";
	private String	available_balance ="";
	private String	hold="";
	private String	status ="";


	public FDInfo() {
	}

	public String getGroup_Acct(){
		return group_acct;
} 
public String getReceipt_No(){
		return receipt_no;
} 
public String getReceipt_Product_Code(){
		return receipt_product_code;
} 
//public String getFd_Receipt_Code (){
//		return fd_receipt_code;
//} 
public String getInterest_Rate (){
		return interest_rate;
} 
public String getOriginal_Balance(){
		return original_balance;
} 
public String getIssue_Date (){
		return issue_date;
} 
public String getMaturity_Date (){
		return maturity_date;
} 
public String getAccurated_Interest (){
		return accurated_interest;
} 
public String getCur_Type (){
		return cur_type;
} 
public String getCd_Term (){
		return cd_term;
} 
public String getAvailable_Balance (){
		return available_balance;
} 
public String getHold(){
		return hold;
} 
public String getStatus(){
		return status;
} 
 


//set

public void setGroup_Acct(String group_acct){
	this. group_acct=group_acct ;
}
public void setReceipt_No(String receipt_no){
	this.receipt_no =receipt_no ;
}
public void setReceipt_Product_Code(String receipt_product_code){
	this. receipt_product_code= receipt_product_code;
}
//public void setFd_Receipt_Code (String fd_receipt_code){
//	this.fd_receipt_code = fd_receipt_code;
//}
public void setInterest_Rate (String interest_rate){
	this. interest_rate= interest_rate;
}
public void setOriginal_Balance(String original_balance){
	this.original_balance =original_balance ;
}
public void setIssue_Date (String issue_date  ){
	this.issue_date =issue_date ;
}
public void setMaturity_Date (String maturity_date){
	this. maturity_date= maturity_date;
}
public void setAccurated_Interest (String accurated_interest){
	this. accurated_interest= accurated_interest;
}
public void setCur_Type (String cur_type ){
	this. cur_type = cur_type ;
}
public void setCd_Term (String cd_term){
	this. cd_term= cd_term ;
}
public void setAvailable_Balance (String available_balance ){
	this. available_balance = available_balance ;
}
public void setHold(String hold){
	this.hold = hold;
}
public void setStatus(String status){
	this. status= status;
}




}
