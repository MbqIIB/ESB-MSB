package vn.com.m1tech.AS400.entity;

public class Fee extends ABCSEntity {
	
	String creditAcc;
	String debitAcc;
	String totalAmount;
	String amount;
	String vatAmount;
	String description;
	
	
	public String getCreditAcc() {
		return creditAcc;
	}
	public void setCreditAcc(String creditAcc) {
		this.creditAcc = creditAcc;
	}
	public String getDebitAcc() {
		return debitAcc;
	}
	public void setDebitAcc(String debitAcc) {
		this.debitAcc = debitAcc;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
