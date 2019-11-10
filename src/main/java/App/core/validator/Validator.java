package  App.core.validator;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.validator.DateValidator;
import org.apache.commons.validator.GenericValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import App.core.hlperBeans.TimeInterval;

/**
 * @author YeHia
 *
 */
public class Validator
{
	private ExceptionStackTrace exceptionStack;
	
	public Validator()
	{
		this.exceptionStack = new ExceptionStackTrace();
	}
	
	public Date getValidDate(String date, String datePattern, String fieldId, boolean required)
	{
		if((!required)&&((date==null) || (date.equals(""))))
			return null;
		
		else if((required)&&((date==null) || (date.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			DateValidator dateValidator = DateValidator.getInstance();
			SimpleDateFormat sDF = new SimpleDateFormat(datePattern);
			
			if(!dateValidator.isValid(date,datePattern,false))
			{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.date");
			}
			
			try {
				return sDF.parse(date);
			} 
			catch (ParseException e) 
			{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.date");
			}
		}
		return null;
	}
		
	public Date getValidDate(String date, String time) {
		try {
			if (date == null || date.equals(""))
				return null;

			SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(sDF.parse(date));
			if (!(time == null) && !time.equals("")) {
				String[] HourMin = time.split(":");
				calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HourMin[0]));
				calendar.set(Calendar.MINUTE, Integer.parseInt(HourMin[1]));
			}
			return calendar.getTime();
		} catch (Exception e) {
		}

		return null;
	}
	
	/**
	 * @author SSamra
	 * 
	 */	
	public Date getValidDate(String date, String hour, String min, String datePattern, 
							 Date lowerBound, Date upperBound, String dateFieldId, String timeFieldId, boolean required)
	{
		if((!required)&&((date==null) || (date.equals(""))))
			return null;
		
		else if((required)&&((date==null) || (date.equals(""))))
			this.exceptionStack.addNewException(dateFieldId,"error.required");
		else
		{
			Date resultDate = new Date();
			DateValidator dateValidator = DateValidator.getInstance();
			SimpleDateFormat sDF = new SimpleDateFormat(datePattern);
			
			if(!dateValidator.isValid(date,datePattern,false))
			{
				this.exceptionStack.addNewException(dateFieldId,"error.badFormat.date");
			}
			
			try 
			{
				resultDate = sDF.parse(date);
			} 
			catch (ParseException e1) 
			{
				this.exceptionStack.addNewException(dateFieldId,"error.badFormat.date");
			}
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(resultDate);
			calendar.set(Calendar.HOUR_OF_DAY, this.getValidHour(hour,timeFieldId,false));
			calendar.set(Calendar.MINUTE, this.getValidMin(min,timeFieldId,false));
			
			resultDate = calendar.getTime();
			/*
			resultDate.setHours(this.getValidHour(hour,timeFieldId,false));
			resultDate.setMinutes(this.getValidMin(min,timeFieldId,false)); 
			*/
				if((lowerBound!=null)&&(lowerBound.compareTo(resultDate)==1))
				{
					this.exceptionStack.addNewException(dateFieldId,"error.outOfRange.date");
				}
				else if((upperBound!=null)&&(upperBound.compareTo(resultDate)==-1))
				{
					this.exceptionStack.addNewException(dateFieldId,"error.outOfRange.date");
				}
				else 
					return resultDate;
			 
			
		}
		return null;
	}
//  ---------------------------------------------------------------
	public int getValidHour(String intValue, String fieldId, boolean required)
	{
		if((!required)&&((intValue==null) || (intValue.equals(""))))
			return 0;
		
		else if((required)&&((intValue==null) || (intValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isInt(intValue))
				{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.time");
				return 0;
				}
			
			else
			{
				int validInt = Integer.parseInt(intValue);
				
				if(!GenericValidator.isInRange(validInt, 0, 23))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.time");
				
				return validInt;
			}
		}
		return 0;
	}
//  ---------------------------------------------------------------
	public int getValidMin(String intValue, String fieldId, boolean required)
	{
		if((!required)&&((intValue==null) || (intValue.equals(""))))
			return 0;
		
		else if((required)&&((intValue==null) || (intValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isInt(intValue))
				{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.time");
				return 0;
				}
			
			else
			{
				int validInt = Integer.parseInt(intValue);
				
				if(!GenericValidator.isInRange(validInt, 0, 59))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.time");
				
				return validInt;
			}
		}
		return 0;
	}

//	---------------------------------------------------------------
	public Boolean getValidBoolean(String booleanValue, String fieldId, boolean required)
	{
		if((!required)&&((booleanValue==null) || (booleanValue.equals(""))))
			return null;
		
		else if((required)&&((booleanValue==null) || (booleanValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(booleanValue.equalsIgnoreCase("1")||booleanValue.equalsIgnoreCase("true"))
				return Boolean.TRUE;
			
			else if(booleanValue.equalsIgnoreCase("0")||booleanValue.equalsIgnoreCase("false"))
				return Boolean.FALSE;
			
			this.exceptionStack.addNewException(fieldId,"error.badFormat.bool");
		}
		return null;
	}
	
	public Date getValidDate(String date, String datePattern,Date lowerBound ,Date upperBound, String fieldId, boolean required)
	{
		if((!required)&&((date==null) || (date.equals(""))))
			return null;
		
		else if((required)&&((date==null) || (date.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			DateValidator dateValidator = DateValidator.getInstance();
			SimpleDateFormat sDF = new SimpleDateFormat(datePattern);
			
			if(!dateValidator.isValid(date,datePattern,false))
			{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.date");
			}
			
			try 
			{
				if((lowerBound!=null)&&(lowerBound.compareTo(sDF.parse(date))==1))
				{
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.date");
				}
				else if((upperBound!=null)&&(upperBound.compareTo(sDF.parse(date))==-1))
				{
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.date");
				}
				else 
					return sDF.parse(date);
			} 
			catch (ParseException e) 
			{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.date");
			}
		}
		return null;
	}
	
	public int getValidInt(String intValue, int lowerBound, int maxBound, String fieldId, boolean required)
	{
		if((!required)&&((intValue==null) || (intValue.equals(""))))
			return 0;
		
		else if((required)&&((intValue==null) || (intValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isInt(intValue))
				{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.number");
				return 0;
				}
			
			else
			{
				int validInt = Integer.parseInt(intValue);
				
				if(!GenericValidator.isInRange(validInt, lowerBound, maxBound))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.number");
				
				return validInt;
			}
		}
		return 0;
	}
	
	public int getValidInt(String intValue, int lowerBound, int maxBound, int defaultValue, String fieldId, boolean required)
	{
		if((!required)&&((intValue==null) || (intValue.equals(""))))
			return defaultValue;
		
		else if((required)&&((intValue==null) || (intValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isInt(intValue))
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.number");
					return defaultValue;
				}
			
			else
			{
				int validInt = Integer.parseInt(intValue);
				
				if(!GenericValidator.isInRange(validInt, lowerBound, maxBound))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.number");
				
				return validInt;
			}
		}
		return defaultValue;
	}
	
	public double getValidDouble(String doubleValue, double lowerBound, double maxBound, String fieldId, boolean required)
	{	
		if((!required)&&((doubleValue==null) || (doubleValue.equals(""))))
			return 0;
	
		else if((required)&&((doubleValue==null) || (doubleValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isDouble(doubleValue))
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.number");
					return 0;
				}
			else
			{
				double validDouble = Double.parseDouble(doubleValue);
				
				if(!GenericValidator.isInRange(validDouble, lowerBound, maxBound))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.number");
				
				return validDouble;
			}
		}
		return 0;
	}
	
	public double getValidDouble(String doubleValue, double lowerBound, double maxBound, double defaultValue, String fieldId, boolean required)
	{	
		if((!required)&&((doubleValue==null) || (doubleValue.equals(""))))
			return defaultValue;
	
		else if((required)&&((doubleValue==null) || (doubleValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.required");
		else
		{
			if(!GenericValidator.isDouble(doubleValue))
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.number");
					return defaultValue;
				}
			else
			{
				double validDouble = Double.parseDouble(doubleValue);
				
				if(!GenericValidator.isInRange(validDouble, lowerBound, maxBound))
					this.exceptionStack.addNewException(fieldId,"error.outOfRange.number");
				
				return validDouble;
			}
		}
		return defaultValue;
	}
	public String getValidString1(String value, String regEx, String fieldId, boolean required)
	{
		String val;
		if((required)&&((value==null)||(value.length()==0)))
		{
			this.exceptionStack.addNewException(fieldId,"error.required");
			return "";
		}
		else if((!required)&&((value==null)||(value.length()==0)))
			return "";
		else
		{
			try
			{
				val = new String(value.getBytes("ISO8859_1"),"UTF8");
				
//				if(Pattern.matches(regEx, val))
//				{
//					// replacing any wild character found * -> %
//					val = val.replace('*','%');
//					return val;
//				}
//				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
			}
			catch (PatternSyntaxException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}
			catch (UnsupportedEncodingException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}			
		}
		return val;
	}
	
	public String getValidString(String value, String regEx, String fieldId, boolean required)
	{
		String val;
		if((required)&&((value==null)||(value.length()==0)))
		{
			this.exceptionStack.addNewException(fieldId,"error.required");
			return "";
		}
		else if((!required)&&((value==null)||(value.length()==0)))
			return "";
		else
		{
			try
			{
				val = new String(value.getBytes("ISO8859_1"),"UTF8");
				
				if(Pattern.matches(regEx, val))
				{
					// replacing any wild character found * -> %
					val = val.replace('*','%');
					return val;
				}
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
			}
			catch (PatternSyntaxException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}
			catch (UnsupportedEncodingException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}			
		}
		return val;
	}
	
	public String getValidStringExact(String value, String regEx, String fieldId, boolean required)
	{   //this function is the same as getValidString but it keep the * as it is & doesn't convert it => %
		String val;
		if((required)&&((value==null)||(value.length()==0)))
		{
			this.exceptionStack.addNewException(fieldId,"error.required");
			return "";
		}
		else if((!required)&&((value==null)||(value.length()==0)))
			return "";
		else
		{
			try
			{
				val = new String(value.getBytes("ISO8859_1"),"UTF8");
				
				if(Pattern.matches(regEx, val))
				{
					return val;
				}
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
			}
			catch (PatternSyntaxException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}
			catch (UnsupportedEncodingException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}			
		}
		return val;
	}
	
	public ExceptionStackTrace getExceptionStack() {
		return exceptionStack;
	}
	
	public JSONObject getJSONExceptionStack() 
	{
		ResourceBundle res = ResourceBundle.getBundle("com.sps.core.resources.ApplicationResources_ar"); 
		JSONObject JSONObj = new JSONObject();
		
		JSONArray JSONArr = new JSONArray();
		FieldException exception;
		Iterator<Object> itr = this.getExceptionStack().getExceptionList().iterator();
		
		while(itr.hasNext())
		{
			exception = (FieldException)itr.next();
			JSONObject JSONTemp = new JSONObject();
			JSONTemp.put("field",exception.getFieldId());
			JSONTemp.put("errorMsg",res.getString(exception.getErrorMessage()));
						
			JSONArr.put(JSONTemp);
		}
		JSONObj.put("ExceptionStackTrace",JSONArr);
		return JSONObj;
	}
	
	public boolean noException()
	{
		return (this.getExceptionStack().getExceptionList().size()==0);
	}
	
	/**
	 * @author Mariam A. Moustafa
	 * Method that provides functionality to check validation and convert the given input string to  
	 * TimeInterval class (defined by: hours,minutes,seconds) 
	 * */
	
	
	//this function is called to from getValidTimeInterval method to convert string to TimeInterval
	public TimeInterval convertToTimeInterval (String string, String fieldId)
	{
		TimeInterval t = new TimeInterval(0);
		String [] str = null;
		
		int numOfColons=0;
		long hours=0, mins=0, sec=0;
		
		str = string.split("[:]");
		numOfColons = str.length;
		
		if(GenericValidator.isLong(str[0]) && GenericValidator.isLong(str[1]))
		{
			hours = Long.parseLong(str[0]);
			mins = Long.parseLong(str[1]);
			
			if(mins > 59)
			{
				this.exceptionStack.addNewException(fieldId,"error.badFormat.OutOfRange");
				return null;
			}
			else{
				// if the user enters sec
				if(numOfColons == 3)
				{
					if(GenericValidator.isLong(str[2]))
					{
						sec = Long.parseLong(str[2]);
						if (sec > 59)
						{
							this.exceptionStack.addNewException(fieldId,"error.badFormat.OutOfRange");
							return null;
						}
						else
							t.setSeconds(sec);
					}	
					else{
						this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
						return null;
					}
					
				}
				t.setHours(hours);
				t.setMinutes(mins);	
			}
				
		}
		else{
			this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
			return null;
		}	
		return t;
	}
	
	//this is the first method
	public TimeInterval getValidTimeInterval(String timeIntervalValue, TimeInterval lowerBound, TimeInterval upperBound, String fieldId, boolean required)
	{
		
		if((!required)&&((timeIntervalValue==null) || (timeIntervalValue.equals(""))))
			return null;
		
		else if((required)&&((timeIntervalValue==null) || (timeIntervalValue.equals(""))))
			this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
		else{
			//convert string to time interval
			TimeInterval t = convertToTimeInterval(timeIntervalValue,fieldId);
			//check if within the given interval
			if (lowerBound != null && upperBound !=null)
			{
				if( ( t.compare(lowerBound)== -1 ) || ( t.compare(upperBound) == 1 ) )
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
					return null;
				}
				else{
					return t;				
				}
			}
			else if (lowerBound != null)
			{
				if( t.compare(lowerBound)== -1 )
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
					return null;
				}
				else{
					return t;				
				}
			}
			else if (upperBound != null)
			{
				if( t.compare(upperBound)== 1 )
				{
					this.exceptionStack.addNewException(fieldId,"error.badFormat.timeInterval");
					return null;
				}
				else{
					return t;				
				}
			}
			else{
				return t;				
			}
				
		}
		
		return null;
		
	}
	
	public String getValidLicenseNumber(String value, String fieldId, boolean required)
	{
		String val;
		if((required)&&((value==null)||(value.length()==0)))
		{
			this.exceptionStack.addNewException(fieldId,"error.required");
			return "";
		}
		else if((!required)&&((value==null)||(value.length()==0)))
			return "";
		else
		{
			try
			{
				val = new String(value.getBytes("ISO8859_1"),"UTF8");
				
				String regEx = "^[ء-ي0-9\\s]{3,6}\\-[0-9\\s]{1,5}$";
				
				if(Pattern.matches(regEx, val))
				{
					// replacing any wild character found * -> %
					val = val.replace('*','%');
					return val;
				}
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
			}
			catch (PatternSyntaxException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}
			catch (UnsupportedEncodingException e) 
			{			
				this.exceptionStack.addNewException(fieldId,"error.badFormat.text");
				return "";
			}			
		}
		return val;
	}
}