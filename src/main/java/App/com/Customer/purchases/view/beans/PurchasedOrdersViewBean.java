package App.com.Customer.purchases.view.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchasedOrdersViewBean {
	private int id ;
	private String date;
	private double grossWeight;
	private double nolun;
	private double unitePrice;
	private double buyPrice;
	 static final SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD"); 
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getNolun() {
		return nolun;
	}
	public void setNolun(double nolun) {
		this.nolun = nolun;
	}
	public double getUnitePrice() {
		return unitePrice;
	}
	public void setUnitePrice(double unitePrice) {
		this.unitePrice = unitePrice;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	

}
