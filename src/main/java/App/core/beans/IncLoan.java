package App.core.beans;

import java.util.Date;


public class IncLoan extends BaseBean {
private int id=-1;
private Date loanDate;
private Double amount;
private int loanAccountId;
private LoanAccount loanAccount;
private String notes;














public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getLoanDate() {
	return loanDate;
}
public void setLoanDate(Date loanDate) {
	this.loanDate = loanDate;
}


public int getLoanAccountId() {
	return loanAccountId;
}
public void setLoanAccountId(int loanAccountId) {
	this.loanAccountId = loanAccountId;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
public LoanAccount getLoanAccount() {
	return loanAccount;
}
public void setLoanAccount(LoanAccount loanAccount) {
	this.loanAccount = loanAccount;
}


}
