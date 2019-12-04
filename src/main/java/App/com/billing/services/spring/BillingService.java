package App.com.billing.services.spring;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import App.com.expanses.services.IExpansesServices;
import App.core.applicationContext.ApplicationContext;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class BillingService {


	IBaseRetrievalService baseRetrievalService;
	IExpansesServices expansesService ;
	IBaseService baseService;
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

	

}
