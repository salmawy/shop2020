 package App.core.beans;

import java.util.Date;


public class Installment extends BaseBean {
private int id =-1;
private Integer sellerBagLoan;
private Date instalmentDate;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Integer getSellerBagLoan() {
	return sellerBagLoan;
}
public void setSellerBagLoan(Integer sellerBagLoan) {
	this.sellerBagLoan = sellerBagLoan;
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
public int getSellerId() {
	return sellerId;
}
public void setSellerId(int sellerId) {
	this.sellerId = sellerId;
}
private Double amount;
private int sciencere;
private int sellerId;




}
