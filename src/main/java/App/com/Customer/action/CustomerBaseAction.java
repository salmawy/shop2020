package App.com.Customer.action;

import java.util.Map;

import App.com.Customer.service.ICustomerService;
import App.core.action.BaseAction;

public class CustomerBaseAction extends BaseAction {
	
	   public  static  Map<String, Object> request;
	     public  static  Map<String, Object> response;
	  
	  private ICustomerService customerService;
	  
	  
	  public ICustomerService getCustomerService() { return customerService; }
	  
	  public void setCustomerService(ICustomerService customerService) {
	  this.customerService = customerService; } 
	  
	  public CustomerBaseAction() {
	  customerService= (ICustomerService) getSpringBeanFactory().getBean("customerService"); }
	  
	 }
