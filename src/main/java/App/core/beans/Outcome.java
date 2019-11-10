 package App.core.beans;

import java.util.Date;


public class Outcome extends BaseBean {
	private int id =-1;
	private Date outcomeDate;
	private Double totalOutcome;
	private int seasonId;
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
	

	

}
