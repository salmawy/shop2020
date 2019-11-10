 package App.core.beans;

public class SellerLoanBag extends BaseBean {
	private int id=-1;
	private Double priorLoan;
	private Double currentLoan;
	private Double dueLoan;
	private Double paidAmount;
	private int sellerId;
	private int seasonId;
	private String notes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPriorLoan() {
		return priorLoan;
	}
	public void setPriorLoan(Double priorLoan) {
		this.priorLoan = priorLoan;
	}
	public Double getCurrentLoan() {
		return currentLoan;
	}
	public void setCurrentLoan(Double currentLoan) {
		this.currentLoan = currentLoan;
	}
	public Double getDueLoan() {
		return dueLoan;
	}
	public void setDueLoan(Double dueLoan) {
		this.dueLoan = dueLoan;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
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



