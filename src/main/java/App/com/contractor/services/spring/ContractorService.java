package App.com.contractor.services.spring;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import App.com.contractor.dao.IContractorDao;
import App.com.contractor.services.IContractorService;
import App.com.expanses.services.IExpansesServices;
import App.core.Enum.OutcomeTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Contractor;
import App.core.beans.ContractorAccount;
import App.core.beans.ContractorAccountDetail;
import App.core.beans.Customer;
import App.core.beans.Outcome;
import App.core.beans.OutcomeDetail;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class ContractorService implements IContractorService {

	IBaseRetrievalService baseRetrievalService;
	IExpansesServices expansesService ;
	IBaseService baseService;
	IContractorDao contractorDao;
	
	
	
	Logger logger = Logger.getLogger(this.getClass().getName());	

	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}
	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}
	public IExpansesServices getExpansesService() {
		return expansesService;
	}
	public void setExpansesService(IExpansesServices expansesService) {
		this.expansesService = expansesService;
	}
	public IBaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public ResourceBundle getSettingsBundle() {
		return settingsBundle;
	}
	public void setSettingsBundle(ResourceBundle settingsBundle) {
		this.settingsBundle = settingsBundle;
	}
	public PlatformTransactionManager getMyTransactionManager() {
	try {
		return ApplicationContext.transactionManager;//(HibernateTransactionManager) CacheEntriesDirectory.getEntry("transactionManager").getCacheEntry();
	} catch (Exception e) {
		return null;
	}
}
	private void closeTransaction(TransactionStatus status) {
	try {
		if (!status.isCompleted())
			this.getMyTransactionManager().rollback(status);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public IContractorDao getContractorDao() {
		return contractorDao;
	}
	public void setContractorDao(IContractorDao contractorDao) {
		this.contractorDao = contractorDao;
	}
	@Override
	public List getContractorAccount(int contractorId, int seasonId, int typeId)
			throws DataBaseException, EmptyResultSetException {
		return this.getContractorDao().getContractorAccount(contractorId, seasonId, typeId);
	}

	@Override
	
	public void contractorTransaction(String name,int typeId,double amount,int fridageId,String notes,int paid,int ownerId,Date date,int seasonId) throws DataBaseException {
		


		TransactionStatus status = null;

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
		def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
		status = this.getMyTransactionManager().getTransaction(def);

		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD");
		try {
	//===================save contractor into Database ========================================================	 
	 Contractor contractor=saveContractor(name, typeId, ownerId);
	 ContractorAccount contractorAccount=findContractorAccount(contractor.getId());
	 
//========================save contractor detail into Database ========================================================	
	 ContractorAccountDetail accountDetail=new ContractorAccountDetail();
	 accountDetail.setAmount(amount);
	 accountDetail.setContractorAccountId(contractorAccount.getId());
	 accountDetail.setDetailDate(date);
	 accountDetail.setPaid(paid);
	 accountDetail.setReport(notes);
	 accountDetail.setSpenderName(ApplicationContext.currentUser.getUsername());
	 accountDetail.setSeasonId(seasonId);
	this.getBaseService().addBean(accountDetail); 
	contractorAccount.setTotalAmount(contractorAccount.getTotalAmount()+amount);
	this.getBaseService().addEditBean(contractorAccount); 

//=============================== insert outcomeTransaction  total value=====================================
this.getExpansesService().outcomeTransaction(date, amount, notes, OutcomeTypeEnum.K_L, contractor.getId(), -1, fridageId, seasonId);


	
	this.getMyTransactionManager().commit(status);
           }
		catch(DataBaseException dbEx) {
			this.getMyTransactionManager().rollback(status);
			throw new DataBaseException(dbEx.getMessage());
			
		}
	 finally {
		 this.closeTransaction(status);
	    }
	 
 
		
		
		
		
		
		
	}
	
	
	
	 public Contractor  saveContractor(String name,int typeId,int ownerId) throws DataBaseException {
			
		Contractor contractor=new Contractor();
		contractor.setName(name);
		contractor.setTypeId(typeId);
		contractor.setTypeName(String.valueOf(typeId));
		contractor.setOwnerId(ownerId);

			try {
				Map <String,Object>m=new HashMap<String,Object>();
				m.put("name", name);
				m.put("typeId",typeId);
				m.put("ownerId", ownerId);
				contractor=(Contractor) this.getBaseService().findAllBeans(Contractor.class, m, null).get(0);
				return contractor;
			} catch (DataBaseException | EmptyResultSetException e) {
				this.getBaseService().addBean(contractor);
			
			}
			
			return contractor;
			
		}
	 public ContractorAccount  findContractorAccount(int contractorId) throws DataBaseException {
			
		ContractorAccount contractorAccount=new ContractorAccount();
		contractorAccount.setId(contractorId);
		contractorAccount.setTotalAmount(0.0);

			try {
				Map <String,Object>m=new HashMap<String,Object>();
				m.put("contractorId", contractorId);
			
				contractorAccount=(ContractorAccount) this.getBaseService().findAllBeans(ContractorAccount.class, m, null).get(0);
				return contractorAccount;
			} catch (DataBaseException | EmptyResultSetException e) {
				this.getBaseService().addBean(contractorAccount);
			
			}
			
			return contractorAccount;
			
		}
@Override
	 public List<String> getSuggestedContractorName(String searchString,int ownerId,int typeId) {
		 return this.contractorDao.getSuggestedContractorName(searchString, ownerId, typeId);
	 }
@Override
public List getContractorAccount(String name, int seasonId, int typeId, Date fromDate, Date toDate, int paid,
		int ownerId) throws DataBaseException, EmptyResultSetException {
	// TODO Auto-generated method stub
	return this.getContractorDao().getContractorAccount(name, seasonId, typeId, fromDate, toDate, paid, ownerId);
}

	
}
