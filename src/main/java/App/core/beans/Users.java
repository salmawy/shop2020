 package App.core.beans;

import java.security.Timestamp;
import java.util.Date;

public class Users extends BaseBean  implements java.io.Serializable  {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id=-1;
	private String username;
	private String password;


	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
