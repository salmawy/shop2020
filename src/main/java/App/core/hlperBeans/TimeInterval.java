package App.core.hlperBeans;

import java.lang.IllegalArgumentException;

/**
 * 
 * @author Sameh M. Omar
 * a utitlty class that provide functionality to maintain 
 * the basic operation on a time interval (defined by: hours,minutes,seconds) 
 * */
public class TimeInterval 
{
	private long seconds=0;
	private long minutes=0;
	private long hours=0;

	/**make new instance of TimeInterval that represents given time in milliSeconds 
	 * */
	public TimeInterval(long millis) {
		this.setSeconds(millis/1000);
	}
	
	public long getHours() {
		return hours;
	}
	
	public void setHours(long hours) {
		this.hours = hours;
	}
	
	public long getMinutes() {
		return minutes;
	}
	
	public void setMinutes(long minutes) {
		long x=minutes/60;
		if(x>0)
		{
			this.setHours(this.getHours()+x);
			this.setMinutes(minutes%60);
		}
		else if(minutes<0)
			this.minutes=0;
		else
			this.minutes = minutes;
	}
	
	public long getSeconds() {
		return seconds;
	}
	
	public void setSeconds(long seconds) {
		long x=seconds/60;
		if(x>0)
		{
			this.setMinutes(this.getMinutes()+x);
			this.setSeconds(seconds%60);
		}
		else if(seconds<0)
			this.seconds=0;
		else
			this.seconds = seconds;
	}
	
	/**@return 0 if the two intervals are equals equal
	 * @return 1 if this object is greater 
	 * @return -1 if this object is smaller
	 * */
	public int compare(TimeInterval that) throws NullPointerException{
		
		if(that==null)
			throw new NullPointerException();
		
		if(this.getHours()==that.getHours())
		{
			if(this.getMinutes()==that.getMinutes())
			{
				if(this.getSeconds()==that.getSeconds())
					return 0;
				else if(this.getSeconds()>that.getSeconds())
					return 1;
				else
					return -1;
			}
			else if(this.getMinutes()>that.getMinutes())
				return 1;
			else
				return -1;				
		}
		else if(this.getHours()>that.getHours())
			return 1;
		else
			return -1; 		
	}
	
	/**adds a time interval to the current oject(modify the current object)
	 * @throws NullPointerException if the parameter is null
	 * @return the new TimeInterval after addition
	 * */
	public TimeInterval add(TimeInterval that) throws NullPointerException{
		if (that==null)
			throw new NullPointerException("TimeInterval object is null");
		this.setSeconds(this.getSeconds()+that.getSeconds());
		this.setMinutes(this.getMinutes()+that.getMinutes());
		this.setHours(this.getHours()+that.getHours());
		return this;
	}
	
	/**subtract a time interval from the current oject(result=this-that)
	 * @throws NullPointerException if the parameter is null
	 * @throws IllegalArgumentException if the parameter object is smaller than the current object
	 * @return the new TimeInterval after subtraction
	 */
	public TimeInterval subtract(TimeInterval that) throws NullPointerException,IllegalArgumentException{
		if (that==null)
			throw new NullPointerException("TimeInterval object is null");
		int code=this.compare(that);
		if(code==0)
		{
			this.setSeconds(0);
			this.setMinutes(0);
			this.setHours(0);
		}
		else if(code==1)
		{
			if((this.getSeconds()-that.getSeconds())<0)
			{
				this.setMinutes(this.getMinutes()-1);
				this.setSeconds(60+this.getSeconds()-that.getSeconds());
				
			}
			else{
				this.setSeconds(this.getSeconds()-that.getSeconds());
			}
			
			if((this.getMinutes()-that.getMinutes())<0)
			{
				this.setHours(this.getHours()-1);
				this.setMinutes(60+this.getMinutes()-that.getMinutes());
				
			}
			else{
				this.setMinutes(this.getMinutes()-that.getMinutes());
			}
			
			this.setHours(this.getHours()-that.getHours());
		}
		else
			throw new IllegalArgumentException("Cannot perform Subtraction ");
		return this;
	}
}