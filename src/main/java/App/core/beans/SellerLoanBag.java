 package App.core.beans;

public class SellerLoanBag extends BaseBean {
	private int id=-1;
	private double priorLoan;
	private double currentLoan;
	private double dueLoan;
	private double paidAmount;
	private int sellerId;
	private int seasonId;
	private String notes;
	
	public SellerLoanBag() {
		this.currentLoan=0.0;
		this.priorLoan=0.0;
		this.dueLoan=0.0;
		this.paidAmount=0.0;

	
	
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPriorLoan() {
		return priorLoan;
	}
	public void setPriorLoan(double priorLoan) {
		this.priorLoan = priorLoan;
	}
	public double getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(double currentLoan) {
		this.currentLoan = currentLoan;
	}
	public double getDueLoan() {
		return dueLoan;
	}
	public void setDueLoan(double dueLoan) {
		this.dueLoan = dueLoan;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
 
	
	
}



