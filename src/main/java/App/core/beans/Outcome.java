 package App.core.beans;

import java.util.Date;


public class Outcome extends BaseBean {
	private int id =-1;
	private Date outcomeDate;
	private Double totalOutcome;
	private int seasonId;
	private Integer safeId;
	private Safe safe;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}
	public Double getTotalOutcome() {
		return totalOutcome;
	}
	public void setTotalOutcome(Double totalOutcome) {
		this.totalOutcome = totalOutcome;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public Integer getSafeId() {
		return safeId;
	}
	public void setSafeId(Integer safeId) {
		this.safeId = safeId;
	}
	public Safe getSafe() {
		return safe;
	}
	public void setSafe(Safe safe) {
		this.safe = safe;
	}
	

	

}
