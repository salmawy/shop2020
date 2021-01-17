package App.com.expanses.action;

import java.util.Map;

import App.com.expanses.services.IExpansesServices;
import App.core.action.BaseAction;

public class ExpansesAction  extends BaseAction{
	
	
	
	private IExpansesServices expansesServices;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;

	public ExpansesAction() {
		
		expansesServices= (IExpansesServices) getSpringBeanFactory().getBean("expansesServices"); 

		
		
		
	}

	public IExpansesServices getExpansesServices() {
		return expansesServices;
	}

	public void setExpansesServices(IExpansesServices expansesServices) {
		this.expansesServices = expansesServices;
	}

	public static Map<String, Object> getRequest() {
		return request;
	}

	public static void setRequest(Map<String, Object> request) {
		ExpansesAction.request = request;
	}

	public static Map<String, Object> getResponse() {
		return response;
	}

	public static void setResponse(Map<String, Object> response) {
		ExpansesAction.response = response;
	}
	
	

}
