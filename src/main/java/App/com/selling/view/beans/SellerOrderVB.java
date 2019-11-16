package App.com.selling.view.beans;

import javafx.beans.property.SimpleBooleanProperty;

public class SellerOrderVB {
	
	private SimpleBooleanProperty chk;
	private int id;
	private String sellerName;
	private String sellerType;
	private double totalAmount;
	private int paidAmount;
	public SimpleBooleanProperty getChk() {
		return chk;
	}
	public void setChk(SimpleBooleanProperty chk) {
		this.chk = chk;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerType() {
		return sellerType;
	}
	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	
	

}
