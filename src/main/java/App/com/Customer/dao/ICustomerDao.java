package App.com.Customer.dao;

import java.util.Date;
import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ICustomerDao {
	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;

}
