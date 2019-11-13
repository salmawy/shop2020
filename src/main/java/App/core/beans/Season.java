 package App.core.beans;

import java.util.Date;


public class Season extends BaseBean {
	int id =-1;

	private Date startDate;
	private Date enddate;
	private int currentSeason;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getCurrentSeason() {
		return currentSeason;
	}
	public void setCurrentSeason(int currentSeason) {
		this.currentSeason = currentSeason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
