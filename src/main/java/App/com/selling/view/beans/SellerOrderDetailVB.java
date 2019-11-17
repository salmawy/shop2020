package App.com.selling.view.beans;

public class SellerOrderDetailVB {
	
 private int customerOrderId;
 private int productId;
 private int sellerWeightId=0;
 private String productName;
 private int count;
 private double grossWeight;
 private double netWeight;
 private double unitePrice;
 private double amount;
 private String customerOrderName;
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public double getGrossWeight() {
	return grossWeight;
}
public void setGrossWeight(double grossWeight) {
	this.grossWeight = grossWeight;
}
public double getNetWeight() {
	return netWeight;
}
public void setNetWeight(double netWeight) {
	this.netWeight = netWeight;
}

public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getCustomerOrderName() {
	return customerOrderName;
}
public void setCustomerOrderName(String customerOrderName) {
	this.customerOrderName = customerOrderName;
}
public double getUnitePrice() {
	return unitePrice;
}
public void setUnitePrice(double unitePrice) {
	this.unitePrice = unitePrice;
}
public int getCustomerOrderId() {
	return customerOrderId;
}
public void setCustomerOrderId(int customerOrderId) {
	this.customerOrderId = customerOrderId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getSellerWeightId() {
	return sellerWeightId;
}
public void setSellerWeightId(int sellerWeightId) {
	this.sellerWeightId = sellerWeightId;
}
 
 

}
