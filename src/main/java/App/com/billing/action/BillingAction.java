package App.com.billing.action;

import java.util.Map;

import App.com.billing.services.IBillingService;
import App.core.action.BaseAction;

public class BillingAction extends BaseAction {

	private IBillingService billingService;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;
	public IBillingService getBillingService() {
		return billingService;
	}
	public void setBillingService(IBillingService billingService) {
		this.billingService = billingService;
	}


    
    

}
