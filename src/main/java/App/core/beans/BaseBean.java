package App.core.beans;

import java.security.Timestamp;
import java.util.Date;

public class BaseBean {
	
	private Integer changerId;
	private Timestamp timestamp;
	private Date changeDate;
	private Integer changed;

	
	
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
