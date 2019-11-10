package App.com.Customer.service;

import java.util.Date;
import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ICustomerService {
	
	
	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;

}
