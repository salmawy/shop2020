package App.com.selling.action;

import java.util.HashMap;
import java.util.Map;

import App.com.selling.spring.ISalesService;
import App.core.action.BaseAction;

public class SellingAction extends BaseAction {
	
	
	private ISalesService sellingService;
	
     public  static  Map<String, Object> orderDataMap;
	public SellingAction() {
		orderDataMap =new  HashMap();
		
		sellingService= (ISalesService) getSpringBeanFactory().getBean("salesService"); 

		
		
		
	}
	public ISalesService getSalesService() {
		return sellingService;
	}
	public void setSellingService(ISalesService sellingService) {
		this.sellingService = sellingService;
	}
	public Map<String, Object> getOrderDataMap() {
		return orderDataMap;
	}
	public void setOrderDataMap(Map<String, Object> orderDataMap) {
		this.orderDataMap = orderDataMap;
	}
	public ISalesService getSellingService() {
		return sellingService;
	}

	

}
