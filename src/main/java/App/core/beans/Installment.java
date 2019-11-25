 package App.core.beans;

import java.util.Date;


public class Installment extends BaseBean {
private int id =-1;
private Date instalmentDate;
private double amount;
private int sciencere;
private int sellerLoanBagId;
private String notes;
private Integer sellerOrderId;


public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}

public Installment() {
	sciencere=0;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public Date getInstalmentDate() {
	return instalmentDate;
}
public void setInstalmentDate(Date instalmentDate) {
	this.instalmentDate = instalmentDate;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public int getSciencere() {
	return sciencere;
}
public void setSciencere(int sciencere) {
	this.sciencere = sciencere;
}
public int getSellerLoanBagId() {
	return sellerLoanBagId;
}
public void setSellerLoanBagId(int sellerLoanBagId) {
	this.sellerLoanBagId = sellerLoanBagId;
}
public void setAmount(double amount) {
	this.amount = amount;
}



public Integer getSellerOrderId() {
	return sellerOrderId;
}

public void setSellerOrderId(Integer sellerOrderId) {
	this.sellerOrderId = sellerOrderId;
}




}
