package App.com.Customer.service.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import App.com.Customer.dao.ICustomerDao;
import App.com.Customer.service.ICustomerService;
import App.core.beans.Customer;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;

public class CustomerService implements ICustomerService {
	
	ICustomerDao customerDao;
	IBaseRetrievalService baseRetrievalService;
	
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}


	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}


	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {		
		return this.customerDao.getCustomerOrders(orderDate);
	}
	public List getCustomerInvoices(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException {
		{
		return this.getCustomerDao().getCustomerOrders(customerId, seasonId, fridageId, 1);	
		}
		
		
		
		}
	public List getSeasonCustomers(int seasonId,int fridageId,int typeId) throws EmptyResultSetException, DataBaseException {
	return this.getCustomerDao().getSeasonCustomers( seasonId ,fridageId, typeId);
	
	}



	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException {
		return this.getCustomerDao().getCurrentSeason();
	}
	public List getCustomersSummaryTransactions(int seasonId,int fridageId,int customerId) throws EmptyResultSetException, DataBaseException {
	
	return this.getCustomerDao().getCustomersSummaryTransactions(seasonId, fridageId, customerId);
	}


	@Override
	public List getPurchasedCustomerData(int seasonId, int customerId)
			throws EmptyResultSetException, DataBaseException {
		try {
			Customer customer=(Customer) this.getBaseRetrievalService().getBean(Customer.class, customerId);
			double cost=this.getCustomerDao().getPurchasedCustomerTotalDue(seasonId, customerId);
			double paidAmount=this.getCustomerDao().getPurchasedCustomerTotalInst(seasonId, customerId);
			double dueAmount=cost-paidAmount;
			return new ArrayList<>(Arrays.asList( 
					customer.getId()
					, customer.getName()
					,cost
					,paidAmount
					,dueAmount
					));
			
			
		} catch (InvalidReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}



}
