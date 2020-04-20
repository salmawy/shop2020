package App.com.inventory.spring.spring;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import App.com.expanses.services.IExpansesServices;
import App.com.inventory.dao.IInventoryDao;
import App.com.inventory.spring.IInventoryService;
import App.com.sales.dao.ISalesDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class InventoryService implements IInventoryService {
	
	private ISalesDao salesDao;
	private IBaseRetrievalService baseRetrievalService;
	private IExpansesServices expansesServices;
	private IInventoryDao  inventoryDao ;


	IBaseService baseService;
	public ISalesDao getSalesDao() {
		return salesDao;
	}
	public void setSalesDao(ISalesDao salesDao) {
		this.salesDao = salesDao;
	}
	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}
	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}
	public IExpansesServices getExpansesServices() {
		return expansesServices;
	}
	public void setExpansesServices(IExpansesServices expansesServices) {
		this.expansesServices = expansesServices;
	}
	public IInventoryDao getInventoryDao() {
		return inventoryDao;
	}
	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
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



	Logger logger = Logger.getLogger(this.getClass().getName());	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	private Map<String,Object>entityDictionary;
	public Map<String,Object> getEntityDictionary() {
		return entityDictionary;
	}
	public void setEntityDictionary(Map<String,Object> entityDictionary) {
		this.entityDictionary = entityDictionary;
	}
	public ResourceBundle getSettingsBundle() {
		return settingsBundle;
	}
	public void setSettingsBundle(ResourceBundle settingsBundle) {
		this.settingsBundle = settingsBundle;
	}
	
	@Override
	public List getInventoryDates(int seasonId) throws EmptyResultSetException, DataBaseException{
		
		return getInventoryDao().getInventoryDates(seasonId);
		
	}
	
	
	
	
	
	
	
	
	

	@Override
	public double getPurchasesProfit(String month, int seasonId, int fridageId) {
		// TODO Auto-generated method stub
		return getInventoryDao().getPurchasesProfit(month, seasonId, fridageId);
	}
	@Override
	public double getCommisionProfit(String month, int seasonId, int fridageId) {
		// TODO Auto-generated method stub
		return getInventoryDao().getCommisionProfit(month, seasonId, fridageId);
	}
	@Override
	public double getKTotalOrders(String month, int seasonId, int fridageId) {
		// TODO Auto-generated method stub
		return getInventoryDao().getKTotalOrders(month, seasonId, fridageId);
	}
	@Override
	public double getkaremmTotalWithdrawal(String month, int seasonId, int fridageId, int contractorType) {
		// TODO Auto-generated method stub
		return getInventoryDao().getkaremmTotalWithdrawal( month,  seasonId,  fridageId,  contractorType);
	}
	@Override
	public double getTotalOutcome(String month, int seasonId, int fridageId) {
		// TODO Auto-generated method stub
		return getInventoryDao().getTotalOutcome(month, seasonId, fridageId);
	}
	@Override
	public double getSalamiProductsProfit(String month, int seasonId, int fridageId, int productId) {
		// TODO Auto-generated method stub
		return getInventoryDao().getSalamiProductsProfit( month,  seasonId,  fridageId,  productId);
	}

    }
	
	
	
	
	
	
	
	
	
	 
 
