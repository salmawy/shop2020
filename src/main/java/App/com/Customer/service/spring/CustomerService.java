package App.com.Customer.service.spring;

import java.util.Date;
import java.util.List;

import App.com.Customer.dao.ICustomerDao;
import App.com.Customer.service.ICustomerService;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class CustomerService implements ICustomerService {
	
	ICustomerDao customerDao;
	
	
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {
		
		return this.customerDao.getCustomerOrders(orderDate);
	}

}
