package vn.com.m1tech.AS400.entity;

public class AccInfo extends ABCSEntity {
	
	// thong tin chi tiet cua tai khoan khi muon maintain dung hamf lockAcc lockAccForUpdate_MB26901
	// de lock tai khoan lai dong thoi lay cac thong tin chi tiet cua TK ra cac tham so se la dau vao cho ham object nay
	
	
	String accountNo; // Comment for field 67 :
	String accType; // Comment for field 68 :
	String newProduct;
	String newDateProduct;
	String accStatus; // Comment for field 71 :
	String stmt; // Comment for field 74 :
	String serviceChg; // Comment for field 75 :
	String  intCycle; // Comment for field 76 :
	String  serviceChange; // Comment for field 77 :
	String  chequeAllowed; // Comment for field 78 : Cheque allowed
	String  holdMainCode;
	String  introducerCode; // Comment for field 80 :
	String  par_81; // Comment for field 81 :
	String  par_82; // Comment for field 82 :
	String  par_83; // Comment for field 83 :
	String  par_84; // Comment for field 84 :
	String  par_85;
	String  par_86_100; // Comment for field 86 :
	String  par_87; 
	String  par_88_100;
	String  par_89_100; // Comment for field 89 :
	String  par_90_100; // Comment for field 90 :
	String  par_91_100; // Comment for field 91 :
	String  par_92; // Comment for field 92 :
	String  par_93; // Comment for field 93 :
	String par_94; // Comment for field 94 :
	String  par_95; // Comment for field 95 :
	String  par_96; // Comment for field 96 :
	String saleOfficer_97;
	String ProductGroup_98;
	
	
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(String newProduct) {
		this.newProduct = newProduct;
	}
	public String getNewDateProduct() {
		return newDateProduct;
	}
	public void setNewDateProduct(String newDateProduct) {
		this.newDateProduct = newDateProduct;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getStmt() {
		return stmt;
	}
	public void setStmt(String stmt) {
		this.stmt = stmt;
	}
	public String getServiceChg() {
		return serviceChg;
	}
	public void setServiceChg(String serviceChg) {
		this.serviceChg = serviceChg;
	}
	public String getIntCycle() {
		return intCycle;
	}
	public void setIntCycle(String intCycle) {
		this.intCycle = intCycle;
	}
	public String getServiceChange() {
		return serviceChange;
	}
	public void setServiceChange(String serviceChange) {
		this.serviceChange = serviceChange;
	}
	public String getChequeAllowed() {
		return chequeAllowed;
	}
	public void setChequeAllowed(String chequeAllowed) {
		this.chequeAllowed = chequeAllowed;
	}
	public String getHoldMainCode() {
		return holdMainCode;
	}
	public void setHoldMainCode(String holdMainCode) {
		this.holdMainCode = holdMainCode;
	}
	public String getIntroducerCode() {
		return introducerCode;
	}
	public void setIntroducerCode(String introducerCode) {
		this.introducerCode = introducerCode;
	}
	public String getPar_81() {
		return par_81;
	}
	public void setPar_81(String par_81) {
		this.par_81 = par_81;
	}
	public String getPar_82() {
		return par_82;
	}
	public void setPar_82(String par_82) {
		this.par_82 = par_82;
	}
	public String getPar_83() {
		return par_83;
	}
	public void setPar_83(String par_83) {
		this.par_83 = par_83;
	}
	public String getPar_84() {
		return par_84;
	}
	public void setPar_84(String par_84) {
		this.par_84 = par_84;
	}
	public String getPar_85() {
		return par_85;
	}
	public void setPar_85(String par_85) {
		this.par_85 = par_85;
	}
	public String getPar_86_100() {
		return par_86_100;
	}
	public void setPar_86_100(String par_86_100) {
		this.par_86_100 = par_86_100;
	}
	public String getPar_87() {
		return par_87;
	}
	public void setPar_87(String par_87) {
		this.par_87 = par_87;
	}
	public String getPar_88_100() {
		return par_88_100;
	}
	public void setPar_88_100(String par_88_100) {
		this.par_88_100 = par_88_100;
	}
	public String getPar_89_100() {
		return par_89_100;
	}
	public void setPar_89_100(String par_89_100) {
		this.par_89_100 = par_89_100;
	}
	public String getPar_90_100() {
		return par_90_100;
	}
	public void setPar_90_100(String par_90_100) {
		this.par_90_100 = par_90_100;
	}
	public String getPar_91_100() {
		return par_91_100;
	}
	public void setPar_91_100(String par_91_100) {
		this.par_91_100 = par_91_100;
	}
	public String getPar_92() {
		return par_92;
	}
	public void setPar_92(String par_92) {
		this.par_92 = par_92;
	}
	public String getPar_93() {
		return par_93;
	}
	public void setPar_93(String par_93) {
		this.par_93 = par_93;
	}
	public String getPar_94() {
		return par_94;
	}
	public void setPar_94(String par_94) {
		this.par_94 = par_94;
	}
	public String getPar_95() {
		return par_95;
	}
	public void setPar_95(String par_95) {
		this.par_95 = par_95;
	}
	public String getPar_96() {
		return par_96;
	}
	public void setPar_96(String par_96) {
		this.par_96 = par_96;
	}
	public String getSaleOfficer_97() {
		return saleOfficer_97;
	}
	public void setSaleOfficer_97(String saleOfficer_97) {
		this.saleOfficer_97 = saleOfficer_97;
	}
	public String getProductGroup_98() {
		return ProductGroup_98;
	}
	public void setProductGroup_98(String productGroup_98) {
		ProductGroup_98 = productGroup_98;
	}
	public String getPar_99() {
		return par_99;
	}
	public void setPar_99(String par_99) {
		this.par_99 = par_99;
	}
	public String getPar_113() {
		return par_113;
	}
	public void setPar_113(String par_113) {
		this.par_113 = par_113;
	}
	public String getPar_114() {
		return par_114;
	}
	public void setPar_114(String par_114) {
		this.par_114 = par_114;
	}
	public String getPar_115() {
		return par_115;
	}
	public void setPar_115(String par_115) {
		this.par_115 = par_115;
	}
	public String getPar_118_100() {
		return par_118_100;
	}
	public void setPar_118_100(String par_118_100) {
		this.par_118_100 = par_118_100;
	}
	public String getPar_119() {
		return par_119;
	}
	public void setPar_119(String par_119) {
		this.par_119 = par_119;
	}
	public String getPar_120_100() {
		return par_120_100;
	}
	public void setPar_120_100(String par_120_100) {
		this.par_120_100 = par_120_100;
	}
	public String getPar_121_100() {
		return par_121_100;
	}
	public void setPar_121_100(String par_121_100) {
		this.par_121_100 = par_121_100;
	}
	public String getExpense117_100() {
		return expense117_100;
	}
	public void setExpense117_100(String expense117_100) {
		this.expense117_100 = expense117_100;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getStaffAcc_129() {
		return staffAcc_129;
	}
	public void setStaffAcc_129(String staffAcc_129) {
		this.staffAcc_129 = staffAcc_129;
	}
	public String getOverDraffProtect_130() {
		return overDraffProtect_130;
	}
	public void setOverDraffProtect_130(String overDraffProtect_130) {
		this.overDraffProtect_130 = overDraffProtect_130;
	}
	String  par_99; // Comment for field 99 :
	String  par_113; // Comment for field 103 :
	String  par_114; 
	String  par_115;
	String  par_118_100; // Comment for field 107 :
	String  par_119; // Comment for field 108 :
	String  par_120_100; // Comment for field 109 :
	String  par_121_100; // Comment for field 110 :
	String  expense117_100; // Comment for field 114 :	
	String accountName; // Comment for field 116 :
	String staffAcc_129;
	String overDraffProtect_130;
	
	
	
	
	
	
	

}
