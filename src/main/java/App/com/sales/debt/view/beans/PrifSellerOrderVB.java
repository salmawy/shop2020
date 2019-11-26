package App.com.sales.debt.view.beans;

import java.util.Date;

public class PrifSellerOrderVB {

	private int id;
	private Date orderDate;
	private String  fridageName;
	private double totalOrderost;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getFridageName() {
		return fridageName;
	}
	public void setFridageName(String fridageName) {
		this.fridageName = fridageName;
	}
	public double getTotalOrderost() {
		return totalOrderost;
	}
	public void setTotalOrderost(double totalOrderost) {
		this.totalOrderost = totalOrderost;
	}
	
}
