package App.com.billing.services.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import App.com.Customer.service.ICustomerService;
import App.com.billing.dao.IBillingDao;
import App.com.billing.services.IBillingService;
import App.com.expanses.services.IExpansesServices;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.CustomerOrder;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class BillingService implements IBillingService {
 
	    private ICustomerService customerService;
	    private IExpansesServices expansesService;
	    private IBaseService baseService;
	    private IBaseRetrievalService baseRetrievalService;
	    private IBillingDao billingDao;
	    
	    
	    
	    
	Logger logger = Logger.getLogger(this.getClass().getName());	

	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}
	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}
	public IExpansesServices getExpansesService() {
		return expansesService;
	}
	public void setExpansesService(IExpansesServices expansesService) {
		this.expansesService = expansesService;
	}
	public IBaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public ResourceBundle getSettingsBundle() {
		return settingsBundle;
	}
	public void setSettingsBundle(ResourceBundle settingsBundle) {
		this.settingsBundle = settingsBundle;
	}
	public PlatformTransactionManager getMyTransactionManager() {
	try {
		return ApplicationContext.transactionManager;//(HibernateTransactionManager) CacheEntriesDirectory.getEntry("transactionManager").getCacheEntry();
	} catch (Exception e) {
		return null;
	}
}
	private void closeTransaction(TransactionStatus status) {
	try {
		if (!status.isCompleted())
			this.getMyTransactionManager().rollback(status);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	public IBillingDao getBillingDao() {
		return billingDao;
	}
	public void setBillingDao(IBillingDao billingDao) {
		this.billingDao = billingDao;
	}
@Override
	public List getSuggestedOrders(int customerId,int status, int seasonId,int typeId,int fridageId) throws DataBaseException, EmptyResultSetException{
		
		Map<String,Object> map=new HashMap<String, Object>();
		if(typeId!=0)
		map.put("customer.typeId",typeId );
		if(typeId!=0)
			map.put("customer.id",customerId );
		map.put("seasonId", seasonId);
		map.put("invoiceStatus",status );

		map.put("fridageId",fridageId );
 		
	return	this.getBaseService().findAllBeansWithDepthMapping(CustomerOrder.class, map);
		
	
		
	}


@Override
public List getSuggestedCustomersOrders(int status, int seasonId, int fridageId, int typeId) throws EmptyResultSetException, DataBaseException {
	
	
	return getBillingDao().getSuggestedCustomersOrders(status, seasonId, fridageId, typeId);
	
}



@Override
public List getCustomersOrderWeights(int orderId) throws EmptyResultSetException, DataBaseException {

	return this.getBillingDao().getCustomersOrderWeights(orderId);
	}
@Override
public List getSuggestedCustomersOrders(int seasonId, int fridageId) throws EmptyResultSetException, DataBaseException {
 	return getBillingDao().getSuggestedCustomersOrders(seasonId, fridageId);
}
}
