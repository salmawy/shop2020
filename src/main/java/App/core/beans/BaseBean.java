package App.core.beans;

import java.sql.Timestamp;
import java.util.Date;

import App.core.applicationContext.ApplicationContext;

public class BaseBean {
	
	private Integer changerId;
	private Timestamp timestamp;
	private Date changeDate;
	private Integer changed=1;

	
	public BaseBean() {
		//this.changed=1;
	//	this.timestamp=new Timestamp(new Date().getTime());
	//	this.changeDate=new Date();
	//	this.changerId=ApplicationContext.currentUser.getId();
	
	
	}
	public Integer getChangerId() {
		return changerId;
	}
	public Integer getChanged() {
		return changed;
	}
	public void setChanged(Integer changed) {
		this.changed = changed;
	}
	public void setChangerId(Integer changerId) {
		this.changerId = changerId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	

}
