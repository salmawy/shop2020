	package App.core.beans;
	
	import java.util.Date;
	
public class PurchasedCustomerInst extends BaseBean {
		
		
		private 	int id=-1;
			
		private int customerId;
		private Date instalmentDate;
		private	double amount;
		private	String notes;
		private	int seasonId;
		private Customer customer;
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
	public Date getInstalmentDate() {
		return instalmentDate;
	}
	public void setInstalmentDate(Date instalmentDate) {
		this.instalmentDate = instalmentDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	
	}
