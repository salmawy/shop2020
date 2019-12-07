package App.com.contractor.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import App.com.contractor.services.IContractorService;
import App.com.expanses.services.IExpansesServices;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.action.BaseAction;

public class ContractorAction extends BaseAction {
	private IContractorService contractorService;

	private IExpansesServices expansesServices;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;
    public  final  List owners;

    
    
    
    
	public IContractorService getContractorService() {
		return contractorService;
	}
	public void setContractorService(IContractorService contractorService) {
		this.contractorService = contractorService;
	}

    public ContractorAction() {
    	owners=new ArrayList (Arrays.asList(new ComboBoxItem(1,getMessage("label.owner.name.kareem")), new ComboBoxItem(2,getMessage("label.owner.name.mahmed"))));
    	setContractorService( (IContractorService) getSpringBeanFactory().getBean("contractorService")); 
		setExpansesServices((IExpansesServices) getSpringBeanFactory().getBean("expansesService")); 

	}
	public IExpansesServices getExpansesServices() {
		return expansesServices;
	}
	public void setExpansesServices(IExpansesServices expansesServices) {
		this.expansesServices = expansesServices;
	}
    
}
