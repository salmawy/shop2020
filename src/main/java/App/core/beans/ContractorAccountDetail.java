package App.core.beans;

import java.util.Date;


public class ContractorAccountDetail extends BaseBean {
	
	
	private int id=-1;
	private int contractorAccountId;
	private Date detailDate;
	private Double amount;
	private String report;
	private String spenderName ;
	private int paid;
	private int seasonId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContractorAccountId() {
		return contractorAccountId;
	}
	public void setContractorAccountId(int contractorAccountId) {
		this.contractorAccountId = contractorAccountId;
	}
	public Date getDetailDate() {
		return detailDate;
	}
	public void setDetailDate(Date detailDate) {
		this.detailDate = detailDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getSpenderName() {
		return spenderName;
	}
	public void setSpenderName(String spenderName) {
		this.spenderName = spenderName;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	
	

   


}
