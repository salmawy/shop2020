package App.core.beans;

import java.util.Date;


public class CustomerOrder extends BaseBean {

	private int id =-1;


private int customerId;
private int productId;
private int  storeId;
private Double nolun;
private Double grossweight;
private Double netWeight;
private  Date orderDate;
private  Double totalPrice;
private Double netPrice;
private Date editeDate;
private Date dueDate;
private Double  tips;
private Double commision;
private String notes;
private int finished;
private int seasonId;
private  int dued;
private int units;
private Double unitePrice;

private Double  ratio;
private Double buyPrice;
private int periodId;
private int fridageId;
private Integer vechileTypeId;
private  Customer customer ;
private  Product product ;
private  Fridage fridage ;
private String orderTag;
private VehicleType vehicleType;






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
public Double getNolun() {
	return nolun;
}
public void setNolun(Double nolun) {
	this.nolun = nolun;
}
public Double getGrossweight() {
	return grossweight;
}
public void setGrossweight(Double grossweight) {
	this.grossweight = grossweight;
}

public Double getNetWeight() {
	return netWeight;
}
public void setNetWeight(Double netWeight) {
	this.netWeight = netWeight;
}
public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
public Double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}
public Double getNetPrice() {
	return netPrice;
}
public void setNetPrice(Double netPrice) {
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
public Double getTips() {
	return tips;
}
public void setTips(Double tips) {
	this.tips = tips;
}
public Double getCommision() {
	return commision;
}
public void setCommision(Double commision) {
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
public Double getRatio() {
	return ratio;
}
public void setRatio(Double ratio) {
	this.ratio = ratio;
}
public Double getBuyPrice() {
	return buyPrice;
}
public void setBuyPrice(Double buyPrice) {
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
public Double getUnitePrice() {
	return unitePrice;
}
public void setUnitePrice(Double unitePrice) {
	this.unitePrice = unitePrice;
}
public Integer getVechileTypeId() {
	return vechileTypeId;
}
public void setVechileTypeId(Integer vechileTypeId) {
	this.vechileTypeId = vechileTypeId;
}
public VehicleType getVehicleType() {
	return vehicleType;
}
public void setVehicleType(VehicleType vehicleType) {
	this.vehicleType = vehicleType;
}


}
