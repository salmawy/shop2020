package App.com.sales.action;

import java.util.HashMap;
import java.util.Map;

import App.com.sales.spring.ISalesService;
import App.core.action.BaseAction;

public class SalesAction extends BaseAction {
	
	
	private ISalesService salesService;
	
     public  static  Map<String, Object> orderDataMap;
     public  static  Map<String, Object> request;
     public  static  Map<String, Object> response;
     public static final int CashId=200;

	public SalesAction() {
		orderDataMap =new  HashMap();
		
		salesService= (ISalesService) getSpringBeanFactory().getBean("salesService"); 

		
		
		
	}
	public ISalesService getSalesService() {
		return salesService;
	}
	public void setSalesService(ISalesService salesService) {
		this.salesService = salesService;
	}
	public Map<String, Object> getOrderDataMap() {
		return orderDataMap;
	}
	public void setOrderDataMap(Map<String, Object> orderDataMap) {
		this.orderDataMap = orderDataMap;
	}
	

	

}
