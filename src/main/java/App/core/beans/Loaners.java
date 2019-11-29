 package App.core.beans;

public class Loaners extends BaseBean {
private int id=-1;
private String name;
private int loanAccountId;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getLoanAccountId() {
	return loanAccountId;
}
public void setLoanAccountId(int loanAccountId) {
	this.loanAccountId = loanAccountId;
}



  
}
