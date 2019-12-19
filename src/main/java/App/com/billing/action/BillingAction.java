package App.com.billing.action;

import java.util.Map;

import App.App;
import App.com.Customer.service.ICustomerService;
import App.com.billing.services.IBillingService;
import App.com.expanses.services.IExpansesServices;
import App.core.action.BaseAction;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class BillingAction extends BaseAction {

	private IBillingService billingService;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;
   
      
    public BillingAction() {
    	
    	setBillingService((IBillingService) App.springBeanFactory.getBean("baseService"));
 
    	
	}
	 
	public IBillingService getBillingService() {
		return billingService;
	}
	public void setBillingService(IBillingService billingService) {
		this.billingService = billingService;
	}


    
    

}
