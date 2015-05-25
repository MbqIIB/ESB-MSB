package vn.com.m1tech.AS400.entity;

public class WithDrawFD extends ABCSEntity{
	
	
	String creditAmount;
	String debitAmount;
	String fdAcc;
	String glAcc;
	String ddAcc;
	String description;
	String fdCurrency;
	
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getFdAcc() {
		return fdAcc;
	}
	public void setFdAcc(String fdAcc) {
		this.fdAcc = fdAcc;
	}
	public String getGlAcc() {
		return glAcc;
	}
	public void setGlAcc(String glAcc) {
		this.glAcc = glAcc;
	}
	public String getDdAcc() {
		return ddAcc;
	}
	public void setDdAcc(String ddAcc) {
		this.ddAcc = ddAcc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFdCurrency() {
		return fdCurrency;
	}
	public void setFdCurrency(String fdCurrency) {
		this.fdCurrency = fdCurrency;
	}
	
	
	

}
