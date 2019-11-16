package App.com.selling.spring.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.com.Customer.dao.ICustomerDao;
import App.com.Customer.service.ICustomerService;
import App.core.Enum.CustomerTypeEnum;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class SellingService implements ICustomerService {
	
	ICustomerDao customerDao;
	IBaseRetrievalService baseRetrievalService;
	IBaseService baseService;

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}


	public IBaseService getBaseService() {
		return baseService;
	}


	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}


	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}


	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {		
		return this.customerDao.getCustomerOrders(orderDate);
	}
	public List getCustomerInvoices(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException 
		{
		return this.getCustomerDao().getCustomerOrders(customerId, seasonId, fridageId, 1);	
		}
		
		
	public List getCustomerOrders(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException 
	{
	return this.getCustomerDao().getCustomerOrders(customerId, seasonId, fridageId, 0);	
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
	public List getPurchasedCustomerData(int seasonId, int fridageId)
			throws EmptyResultSetException, DataBaseException {
		
		Map <String,Object>m=new HashMap<String,Object>();
		m.put("customer.typeId", CustomerTypeEnum.purchases);
		m.put("seasonId", seasonId);
		m.put("periodId", -1);
		List data=new ArrayList(); 
	
		try {
			List<Object>result= this.getSeasonCustomers(seasonId, 0, CustomerTypeEnum.purchases);
			for (Object it : result) {
				Customer customer=(Customer) it;
				double cost=this.getCustomerDao().getPurchasedCustomerTotalDue(seasonId, customer.getId());
				double paidAmount=this.getCustomerDao().getPurchasedCustomerTotalInst(seasonId, customer.getId());
				double dueAmount=cost-paidAmount;
				
				List temp= new ArrayList<>(Arrays.asList( 
						customer.getId()
						, customer.getName()
						,cost
						,paidAmount
						,dueAmount
						));
				
				data.add(temp);
				
			}
			
			
		
			
			
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return data;
	}



}
