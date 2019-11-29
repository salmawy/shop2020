 package App.core.beans;

public class IncomeDetail extends BaseBean {
private int id=-1;
private String typeName;
private Double amount;
private String resipeintName;
private int incomeId;
private String notes;
private Integer sellerId;
private int fridageId;
private int typeId;
private Integer sellerOrderId;
private Income income;

private IncomeType type;





public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getResipeintName() {
	return resipeintName;
}
public void setResipeintName(String resipeintName) {
	this.resipeintName = resipeintName;
}
public int getIncomeId() {
	return incomeId;
}
public void setIncomeId(int incomeId) {
	this.incomeId = incomeId;
}
public String getNotes() {
	return notes;
}
public void setNotes(String notes) {
	this.notes = notes;
}
public Integer getSellerId() {
	return sellerId;
}
public void setSellerId(Integer sellerId) {
	this.sellerId = sellerId;
}
public int getFridageId() {
	return fridageId;
}
public void setFridageId(int fridageId) {
	this.fridageId = fridageId;
}
public int getTypeId() {
	return typeId;
}
public void setTypeId(int typeId) {
	this.typeId = typeId;
}
public Integer getSellerOrderId() {
	return sellerOrderId;
}
public void setSellerOrderId(Integer sellerOrderId) {
	this.sellerOrderId = sellerOrderId;
}
public Income getIncome() {
	return income;
}
public void setIncome(Income income) {
	this.income = income;
}
public void setType(IncomeType type) {
	this.type = type;
}
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}
public IncomeType getType() {
	return type;
}


	
	
	
}
