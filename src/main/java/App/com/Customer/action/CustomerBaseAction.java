package App.com.Customer.action;

import App.com.Customer.service.ICustomerService;
import App.core.action.BaseAction;

public class CustomerBaseAction extends BaseAction {
	
	  
	  
	  private ICustomerService customerService;
	  
	  
	  public ICustomerService getCustomerService() { return customerService; }
	  
	  public void setCustomerService(ICustomerService customerService) {
	  this.customerService = customerService; } 
	  
	  public CustomerBaseAction() {
	  customerService= (ICustomerService) getSpringBeanFactory().getBean("customerService"); }
	  
	 }
