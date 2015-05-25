package seatechit.msbgateway.dbaccess.entity;



public class AftInfo {
	private String status  ="";
	private String rollout_acct_no="";
	private String bnfc_acct_no="";
	private String 	started_date="";
	private String expired_date="";
	private String next_transfer_date="";
	private String last_transfer_date="";
	private String amount="";
	private String aft_fee="";
	private String freq_no="";
	private String freq_code="";
	private String debit_seq="";
	private String entered_date="";
	private String rollout_acct_ccy="";
	private String rollout_acct_type="";
	private String bnfc_acct_ccy="";
	private String bnfc_acct_type="";
	private String bnfc_acct_prod_type="";


	public AftInfo() {
	}

	public String getStatus(){
		return status;
		} 
public String getRollout_Acct_No(){
		return rollout_acct_no;
		} 
public String getBnfc_Acct_No(){
		return bnfc_acct_no;
		} 
public String getStarted_Date(){
		return started_date;
		} 
public String getExpired_Date(){
		return expired_date;
		} 
public String getNext_Transfer_Date(){
		return next_transfer_date;
		} 
public String getLast_Transfer_Date(){
		return last_transfer_date;
		} 
public String getAmount(){
		return amount;
		} 
public String getAft_Fee(){
		return aft_fee;
		} 
public String getFreq_No(){
		return freq_no;
		} 
public String getFreq_Code(){
		return freq_code;
		} 
public String getDebit_Seq(){
		return debit_seq;
		} 
public String getEntered_date(){
	return entered_date;
	} 

		
		// set
public void setStatus(String status){
	this.status = status;
	}
public void setRollout_Acct_No(String rollout_acct_no){
	this.rollout_acct_no = rollout_acct_no;
	}
public void setBnfc_Acct_No(String bnfc_acct_no){
	this. bnfc_acct_no= bnfc_acct_no;
	}
public void setStarted_Date(String started_date){
	this.started_date = started_date;
	}
public void setExpired_Date(String expired_date){
	this. expired_date= expired_date;
	}
public void setNext_Transfer_Date(String next_transfer_date){
	this.next_transfer_date =next_transfer_date ;
	}
public void setLast_Transfer_Date(String last_transfer_date){
	this.last_transfer_date = last_transfer_date;
	}
public void setAmount(String amount){
	this.amount = amount;
	}
public void setAft_Fee(String aft_fee){
	this. aft_fee= aft_fee;
	}
public void setFreq_No(String freq_no){
	this. freq_no= freq_no;
	}
public void setFreq_Code(String freq_code){
	this.freq_code =freq_code ;
	}
public void setDebit_Seq(String debit_seq){
	this.debit_seq = debit_seq;
	}
public void setEntered_date(String entered_date){
	this.entered_date = entered_date;
	}

public String getRollout_acct_ccy() {
	return rollout_acct_ccy;
}

public void setRollout_acct_ccy(String rollout_acct_ccy) {
	this.rollout_acct_ccy = rollout_acct_ccy;
}

public String getRollout_acct_type() {
	return rollout_acct_type;
}

public void setRollout_acct_type(String rollout_acct_type) {
	this.rollout_acct_type = rollout_acct_type;
}

public String getBnfc_acct_ccy() {
	return bnfc_acct_ccy;
}

public void setBnfc_acct_ccy(String bnfc_acct_ccy) {
	this.bnfc_acct_ccy = bnfc_acct_ccy;
}

public String getBnfc_acct_type() {
	return bnfc_acct_type;
}

public void setBnfc_acct_type(String bnfc_acct_type) {
	this.bnfc_acct_type = bnfc_acct_type;
}

public String getBnfc_acct_prod_type() {
	return bnfc_acct_prod_type;
}

public void setBnfc_acct_prod_type(String bnfc_acct_prod_type) {
	this.bnfc_acct_prod_type = bnfc_acct_prod_type;
}


}
