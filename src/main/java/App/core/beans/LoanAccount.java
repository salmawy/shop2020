 package App.core.beans;

public class LoanAccount extends BaseBean {
private int id =-1;

private int loanerId;
private String type;
private Double dueAmount;
private int finished;
private Double totalAmount;
private Loaners loaner;

public LoanAccount() {
	dueAmount=0.0;
	finished=0;
	totalAmount=0.0;
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getLoanerId() {
	return loanerId;
}
public void setLoanerId(int loanerId) {
	this.loanerId = loanerId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Double getDueAmount() {
	return dueAmount;
}
public void setDueAmount(Double dueAmount) {
	this.dueAmount = dueAmount;
}
public int getFinished() {
	return finished;
}
public void setFinished(int finished) {
	this.finished = finished;
}
public Double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount = totalAmount;
}
public Loaners getLoaner() {
	return loaner;
}
public void setLoaner(Loaners loaner) {
	this.loaner = loaner;
}








}
