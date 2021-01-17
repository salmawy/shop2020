package App.com.Customer.action;

import java.util.Map;

import App.com.Customer.service.ICustomerService;
import App.com.expanses.services.IExpansesServices;
import App.core.action.BaseAction;

public class CustomerBaseAction extends BaseAction {
	
	   public  static  Map<String, Object> request;
	     public  static  Map<String, Object> response;
	  
	  private ICustomerService customerService;
	  
		private IExpansesServices expansesService;

	  public ICustomerService getCustomerService() { return customerService; }
	  
	  public void setCustomerService(ICustomerService customerService) {
	  this.customerService = customerService; } 
	  
	  public CustomerBaseAction() {
	  customerService= (ICustomerService) getSpringBeanFactory().getBean("customerService"); 
		expansesService= (IExpansesServices) getSpringBeanFactory().getBean("expansesServices"); 

	  }

	public IExpansesServices getExpansesService() {
		return expansesService;
	}

	 }
