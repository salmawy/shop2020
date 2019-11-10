package  App.core.exception;

import java.lang.Exception;

/**
 * @author YeHia
 *
 */
/**
 * 
 * this class is the exception for the krimary key violated
 *
 */
public class PrimaryKeyViolatedException extends Exception{

	final static long serialVersionUID = 8;
	/**
	 * 
	 * @param arg0
	 */
	public PrimaryKeyViolatedException(String arg0) {
		super(arg0);
	}
}
