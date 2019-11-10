 package App.core.beans;

import java.util.Date;


public class Income extends BaseBean {

private int id =-1;
private Double totalAmount;
private Date incomeDate;
private int  seasonId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount = totalAmount;
}
public Date getIncomeDate() {
	return incomeDate;
}
public void setIncomeDate(Date incomeDate) {
	this.incomeDate = incomeDate;
}
public int getSeasonId() {
	return seasonId;
}
public void setSeasonId(int seasonId) {
	this.seasonId = seasonId;
}

	
	  

}
