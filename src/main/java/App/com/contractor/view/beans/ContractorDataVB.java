package App.com.contractor.view.beans;

import java.text.SimpleDateFormat;

public class ContractorDataVB {

	private int id;
	
	private String date;
	private double amount;
	private int paid ;
	private String notes;
	
	
	
	public static final SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd"); 

	
	
	
	
	
	
	
	
	
	
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

	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
	
	
	
}
