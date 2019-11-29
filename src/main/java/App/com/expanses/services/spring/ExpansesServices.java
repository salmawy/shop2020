package App.com.expanses.services.spring;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import App.com.expanses.dao.IExpansesDao;
import App.com.expanses.services.IExpansesServices;
import App.com.sales.action.SalesAction;
import App.core.Enum.IncomeTypesEnum;
import App.core.Enum.OutcomeTypeEnum;
import App.core.Enum.SellerTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Income;
import App.core.beans.IncomeDetail;
import App.core.beans.LoanAccount;
import App.core.beans.LoanPaying;
import App.core.beans.Loaners;
import App.core.beans.Outcome;
import App.core.beans.OutcomeDetail;
import App.core.beans.Safe;
import App.core.beans.Seller;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class ExpansesServices implements IExpansesServices {

	IExpansesDao expansesDao;
	IBaseRetrievalService baseRetrievalService;
	IBaseService baseService;
	Logger logger = Logger.getLogger(this.getClass().getName());	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	
	
	
	

public IExpansesDao getExpansesDao() {
		return expansesDao;
	}

	public void setExpansesDao(IExpansesDao expansesDao) {
		this.expansesDao = expansesDao;
	}

	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}

	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
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

@Override
public List getOutcome(Date date) throws EmptyResultSetException, DataBaseException {

	return  this.getExpansesDao().getOutcome(date);
}

@Override
public List getIncome(Date date) throws EmptyResultSetException, DataBaseException {

	return  this.getExpansesDao().getIncome(date);
}

	
public List getIncomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException{
	return this.getExpansesDao().getIncomeMonthes(seasonId);
}
public List getOutcomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException {
	return this.getExpansesDao().getOutcomeMonthes(seasonId);
}

@Override
public List getOutcomeDays(String month) throws EmptyResultSetException, DataBaseException {
	// TODO Auto-generated method stub
	return this.getExpansesDao().getOutcomeDays(month);
}

@Override
public List getIncomeDays(String month) throws EmptyResultSetException, DataBaseException {
	// TODO Auto-generated method stub
	return this.getExpansesDao().getIncomeDays(month);
}

	
public void loanPayTansaction(String name,Date date,double amount,int type,String notes,int fridageId)throws DataBaseException {
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	try {
	

		Loaners loaner=findLoaner(name);
		LoanAccount account=  findLoanerAccount(loaner.getId());
	
		LoanPaying pay=new LoanPaying();
		pay.setLoanAccountId(account.getId());
		pay.setNotes(notes);
		pay.setPaidAmunt(amount);
		pay.setPayingDate(date);
		this.getBaseService().addBean(pay);
		
		if(type==OutcomeTypeEnum.OUT_PAY_LOAN) {
			
			OutcomeDetail outPayLoan=new OutcomeDetail();
			outPayLoan.setAmount(amount);
			outPayLoan.setFridageId(fridageId);
			outPayLoan.setSpenderName(ApplicationContext.currentUser.getUsername());
			outPayLoan.setCustomerId(loaner.getId());
			outPayLoan.setTypeId(OutcomeTypeEnum.OUT_PAY_LOAN);
			outPayLoan.setTypeName(String .valueOf(OutcomeTypeEnum.OUT_PAY_LOAN));
			outPayLoan.setOrderId(-1);

			Outcome outome=findOutcome(date);
			saveOutcomeDetail(outPayLoan, outome);
			
		}
		
		else if(type==IncomeTypesEnum.IN_PAY_LOAN)
		{
			IncomeDetail incomeDetail=new IncomeDetail();
			incomeDetail.setAmount(amount);
			incomeDetail.setFridageId(fridageId);
			incomeDetail.setResipeintName(ApplicationContext.currentUser.getUsername());
			incomeDetail.setSellerId(loaner.getId());
			incomeDetail.setTypeId(IncomeTypesEnum.IN_PAY_LOAN);
			incomeDetail.setTypeName(String .valueOf(IncomeTypesEnum.IN_PAY_LOAN));

			incomeDetail.setSellerOrderId(-1);

			saveIncomeDetail(incomeDetail,date);
			
			
			
			
		}
		
		
		
		this.getMyTransactionManager().commit(status);
		logger.log(Level.INFO,this.getClass().getName()+"=>tranasction completed succfully");
		
		
	}catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException(e.getMessage());

	}finally {
		closeTransaction(status);

	}
		
		
	
}	



public void saveIncomeDetail(IncomeDetail incomeDetail,Date date) throws DataBaseException {
	
	
	Income income=findIncome(date);
	incomeDetail.setIncomeId(income.getId());
	incomeDetail.setResipeintName(ApplicationContext.currentUser.getUsername());

	income.setTotalAmount(income.getTotalAmount()+incomeDetail.getAmount());
	this.getBaseService().addEditBean(incomeDetail);

	this.getBaseService().addEditBean(income);

	
	
	
	
}
	public  Income findIncome(Date date) {
		
		
		Income income=new Income();
		income.setIncomeDate(date);
		income.setTotalAmount(0.0);
	 
		
		try {
			
			income=	(Income) this.getExpansesDao().getIncome(date).get(0);
			
			return income;
		} catch (DataBaseException | EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(logger.getLevel().INFO, "error.emptyRS incomeDate of date "+date.toString());
		}
		
		
		
		try {
			this.getBaseService().addBean(income);
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return income;
		
		
		
		
		
		
		
		
		
	}
	public Income updateincome(int id ,double amount) throws DataBaseException, InvalidReferenceException {
	
	
	Income income=(Income) this.getBaseService().getBean(Income.class, id);
	income.setTotalAmount(income.getTotalAmount()+amount);
	
	return income;
	
	
}




public void saveOutcomeDetail(OutcomeDetail outcomeDetail,Outcome outcome) throws DataBaseException {


outcomeDetail.setOutcomeId(outcome.getId());
outcomeDetail.setSpenderName(ApplicationContext.currentUser.getUsername());

outcome.setTotalOutcome(outcome.getTotalOutcome()+outcomeDetail.getAmount());
this.getBaseService().addBean(outcomeDetail);

this.getBaseService().editBean(outcome);





}






public  Outcome findOutcome(Date date) {

	
	 
	
	try {
		
		return	(Outcome) this.getExpansesDao().getOutcome(date).get(0);
		
	
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.log(logger.getLevel().INFO, "error.emptyRS incomeDate of date "+date.toString());
	}
	
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	
	try {
		
		Outcome outcome=new Outcome();
		outcome.setOutcomeDate(date);
		outcome.setTotalOutcome(0.0);
		
		this.getBaseService().addBean(outcome);
		this.getMyTransactionManager().commit(status);
		return outcome;
	} catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		e.printStackTrace();
	}finally {
		this.closeTransaction(status);

	}
	
	
	
	
	

	
	
	return null;
	
	
	
	
	
	
}
public Loaners  findLoaner(String name ) throws DataBaseException {
	

	Map<String,Object> map=new HashMap<String, Object>();
	map.put("name", name);
	
	
	Loaners loaner=null;
	try {
		loaner = (Loaners) this.getBaseService().findBean(Loaners.class, map);
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return loaner;
	
}
public LoanAccount  findLoanerAccount(int loanerId  ) throws DataBaseException {
	

	Map<String,Object> map=new HashMap<String, Object>();
	map.put("loanerId", loanerId);
	
	
	LoanAccount account=null;
	try {
		account = (LoanAccount) this.getBaseService().findBean(Loaners.class, map);
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return account;
	
}

public void outcomeTransaction(Date date,double amount, String notes, int typeId, int customerId, int orderId, int fridageId) throws DataBaseException 
	{
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	try {
	
		IncomeDetail incomeDetail=new IncomeDetail();
		incomeDetail.setAmount(amount);
		incomeDetail.setFridageId(fridageId);
		incomeDetail.setResipeintName(ApplicationContext.currentUser.getUsername());
		incomeDetail.setSellerId(customerId);
		incomeDetail.setTypeId(typeId);
		incomeDetail.setTypeName(String .valueOf(typeId));

		incomeDetail.setSellerOrderId(-1);

		saveIncomeDetail(incomeDetail,date);
		
		
		this.getMyTransactionManager().commit(status);
		logger.log(Level.INFO,this.getClass().getName()+"=>tranasction completed succfully");
		
		
	}catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException(e.getMessage());

	}finally {
		closeTransaction(status);

	}
		
		
	

		
	
	
	}

@Override
public LoanAccount getLoanerAccount(String name) {

	Map<String,Object> map=new HashMap<String, Object>();
	map.put("loaner.name", name);
	LoanAccount account=null;;
	try {
		account =(LoanAccount) this.getBaseService().findAllBeansWithDepthMapping(Loaners.class, map).get(0);
		
	
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return account;
}


public Double getSafeBalance(int seasonId) {
		
		Double balance=0.0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("seasonId", seasonId);
		
		
		try {
		Safe safe=	(Safe) this.getBaseService().getBean(Safe.class, map);
		return safe.getBalance();
		} catch (DataBaseException | InvalidReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
		
		
}



}
