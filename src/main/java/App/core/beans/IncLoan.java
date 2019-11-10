package App.core.beans;

import java.util.Date;


public class IncLoan extends BaseBean {
private int id=-1;
private Date loanDate;
private Double amount;




private int loanAccountId;
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
private String notes;
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


}
