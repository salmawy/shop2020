package App.core.beans;

import java.util.Date;


public class CustomerOrder extends BaseBean {

	private int id =-1;


private int customerId;
private int productId;
private int  storeId;
private double nolun;
private double grossweight;
private double netWeight;
private  Date orderDate;
private  double totalPrice;
private double netPrice;
private Date editeDate;
private Date dueDate;
private double  tips;
private double commision;
private String notes;
private int finished;
private int seasonId;
private String vechileType;
private  int dued;
private int units;
private double unitePrice;

private double  ratio;
private double buyPrice;
private int periodId;
private int fridageId;
private  Customer customer ;
private  Product product ;
private  Fridage fridage ;
private String orderTag;


public String getOrderTag() {
	return orderTag;
}
public void setOrderTag(String orderTag) {
	this.orderTag = orderTag;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getStoreId() {
	return storeId;
}
public void setStoreId(int storeId) {
	this.storeId = storeId;
}
public double getNolun() {
	return nolun;
}
public void setNolun(double nolun) {
	this.nolun = nolun;
}
public double getGrossweight() {
	return grossweight;
}
public void setGrossweight(double grossweight) {
	this.grossweight = grossweight;
}

public double getNetWeight() {
	return netWeight;
}
public void setNetWeight(double netWeight) {
	this.netWeight = netWeight;
}
public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
public double getNetPrice() {
	return netPrice;
}
public void setNetPrice(double netPrice) {
	this.netPrice = netPrice;
}
public Date getEditeDate() {
	return editeDate;
}
public void setEditeDate(Date editeDate) {
	this.editeDate = editeDate;
}

public Date getDueDate() {
	return dueDate;
}
public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}
public double getTips() {
	return tips;
}
public void setTips(double tips) {
	this.tips = tips;
}
public double getCommision() {
	return commision;
}
public void setCommision(double commision) {
	this.commision = commision;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
public int getFinished() {
	return finished;
}
public void setFinished(int finished) {
	this.finished = finished;
}
public String getVechileType() {
	return vechileType;
}
public void setVechileType(String vechileType) {
	this.vechileType = vechileType;
}
public int getDued() {
	return dued;
}
public void setDued(int dued) {
	this.dued = dued;
}

public int getUnits() {
	return units;
}
public void setUnits(int units) {
	this.units = units;
}
public double getRatio() {
	return ratio;
}
public void setRatio(double ratio) {
	this.ratio = ratio;
}
public double getBuyPrice() {
	return buyPrice;
}
public void setBuyPrice(double buyPrice) {
	this.buyPrice = buyPrice;
}
public int getPeriodId() {
	return periodId;
}
public void setPeriodId(int periodId) {
	this.periodId = periodId;
}
public int getFridageId() {
	return fridageId;
}
public void setFridageId(int fridageId) {
	this.fridageId = fridageId;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public Fridage getFridage() {
	return fridage;
}
public void setFridage(Fridage fridage) {
	this.fridage = fridage;
}
public int getSeasonId() {
	return seasonId;
}
public void setSeasonId(int seasonId) {
	this.seasonId = seasonId;
}
public double getUnitePrice() {
	return unitePrice;
}
public void setUnitePrice(double unitePrice) {
	this.unitePrice = unitePrice;
}


}
