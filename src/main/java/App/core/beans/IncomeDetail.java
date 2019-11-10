 package App.core.beans;

public class IncomeDetail extends BaseBean {
private int id=-1;
private String type;
private Double amount;
private String resipeintName;
private int incomeId;
private String notes;
private Integer sellerId;
private int fridageId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
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


	
	
	
}
