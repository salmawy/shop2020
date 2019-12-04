package App.com.contractor.action;

import java.util.Map;

import App.com.contractor.services.IContractorService;
import App.core.action.BaseAction;

public class ContractorAction extends BaseAction {
	private IContractorService contractorService;
	
    public  static  Map<String, Object> request;
    public  static  Map<String, Object> response;
	public IContractorService getContractorService() {
		return contractorService;
	}
	public void setContractorService(IContractorService contractorService) {
		this.contractorService = contractorService;
	}

    
    
}
