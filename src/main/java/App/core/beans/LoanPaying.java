 package App.core.beans;

import java.util.Date;


public class LoanPaying extends BaseBean {
private int id=-1;
private int loanAccountId;
private Double paidAmunt;
private Date payingDate;
private String notes;




public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getLoanAccountId() {
	return loanAccountId;
}
public void setLoanAccountId(int loanAccountId) {
	this.loanAccountId = loanAccountId;
}
public Double getPaidAmunt() {
	return paidAmunt;
}
public void setPaidAmunt(Double paidAmunt) {
	this.paidAmunt = paidAmunt;
}
public Date getPayingDate() {
	return payingDate;
}
public void setPayingDate(Date payingDate) {
	this.payingDate = payingDate;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}




}
