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
	private IExpansesServices expansesService;
	private ICustomerService customerService;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;
   
      
    public BillingAction() {
    	
    	setBillingService((IBillingService) App.springBeanFactory.getBean("billingService"));
    	setExpansesService((IExpansesServices) App.springBeanFactory.getBean("expansesService"));
    	setCustomerService((ICustomerService) App.springBeanFactory.getBean("customerService"));

    
    }
	 
	public IBillingService getBillingService() {
		return billingService;
	}
	public void setBillingService(IBillingService billingService) {
		this.billingService = billingService;
	}

	public IExpansesServices getExpansesService() {
		return expansesService;
	}

	public void setExpansesService(IExpansesServices expansesService) {
		this.expansesService = expansesService;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

 


    
    

}
