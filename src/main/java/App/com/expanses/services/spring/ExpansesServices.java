package App.com.expanses.services.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import App.core.Enum.IncomeTypesEnum;
import App.core.Enum.LoanTypeEnum;
import App.core.Enum.OutcomeTypeEnum;
import App.core.Enum.SafeTransactionTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.IncLoan;
import App.core.beans.Income;
import App.core.beans.IncomeDetail;
import App.core.beans.LoanAccount;
import App.core.beans.LoanPaying;
import App.core.beans.Loaners;
import App.core.beans.Outcome;
import App.core.beans.OutcomeDetail;
import App.core.beans.Safe;
import App.core.beans.SafeTracing;
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
	private Map <String,Object> entitDictionary;
	
	
	@Override
public void initEntitDictionary() {
	
	try {
		entitDictionary=new HashMap();
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	
}






public Map <String,Object> getEntitDictionary() {
	
	
	
	return entitDictionary;
}
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

public void outcomeTransaction(Date date,double amount, String notes, int typeId, int customerId, int orderId, int fridageId,int seasonId) throws DataBaseException 
	{
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	try {
	
		
		OutcomeDetail outcomeDetail=new OutcomeDetail();
		outcomeDetail.setAmount(amount);
		outcomeDetail.setFridageId(fridageId);
		outcomeDetail.setSpenderName(ApplicationContext.currentUser.getUsername());
		outcomeDetail.setCustomerId(customerId);
		outcomeDetail.setTypeId(typeId);
		outcomeDetail.setTypeName(String .valueOf(typeId));

		outcomeDetail.setOrderId(orderId);
		Outcome outcome=findOutcome(date);
		saveOutcomeDetail(outcomeDetail,outcome);
		recalculateSafeBalance(seasonId);

		
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
	map.put("name", name);
	LoanAccount account=null;;
	try {
		account =(LoanAccount) this.getBaseService().findAllBeansWithDepthMapping(Loaners.class, map).get(0);
		
	
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return account;
}

@Override
public LoanAccount getLoanerAccount(int loanerId) {

	Map<String,Object> map=new HashMap<String, Object>();
	map.put("id", loanerId);
	LoanAccount account=null;;
	try {
		account =(LoanAccount) this.getBaseService().findAllBeansWithDepthMapping(Loaners.class, map).get(0);
		
	
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return account;
}


public void incomeTransaction(Date date,double amount, String notes, int typeId, int sellerId, int orderId, int fridageId,int seasonId) throws DataBaseException 
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
		incomeDetail.setSellerId(sellerId);
		incomeDetail.setTypeId(typeId);
		incomeDetail.setTypeName(String .valueOf(typeId));

		incomeDetail.setSellerOrderId(orderId);

		saveIncomeDetail(incomeDetail,date);
		recalculateSafeBalance(seasonId);
		
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


public void recalculateSafeBalance(int seasonId) {
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("incomeDetail.income.seasonId=", seasonId);
	
	Map<String,Object> map2=new HashMap<String, Object>();
	map2.put("outcomeDetail.outcome.seasonId=", seasonId);
	
	Double totalIncome=0.0;
	Double totaloutcome=0.0;

	try {
		totalIncome=(Double) this.getBaseRetrievalService().aggregate("IncomeDetail incomeDetail", "sum", "amount", map);
		totalIncome=(totalIncome==null)?0.0:totalIncome;
		totaloutcome=(Double) this.getBaseRetrievalService().aggregate("OutcomeDetail outcomeDetail", "sum", "amount", map2);
		totaloutcome=(totaloutcome==null)?0.0:totaloutcome;
		
		
		map2=new HashMap<String, Object>();
		map2.put("seasonId", seasonId);
		
		Safe safe=(Safe)this.getBaseService().findAllBeans(Safe.class,map2,null).get(0);
		double temp=totalIncome-totaloutcome;
		safe.setBalance(safe.getBaseAmount()+temp);
		this.getBaseService().addEditBean(safe);
		
		
		

	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
	
}



public Loaners  saveLoaner(Loaners Loaner) throws DataBaseException {
	
	

	
	
	try {
		Map <String,Object>m=new HashMap<String,Object>();
		m.put("name", Loaner.getName());
		Loaner=(Loaners) this.getBaseService().findAllBeans(Loaners.class, m, null).get(0);
		
		
		return Loaner;
	} catch (DataBaseException | EmptyResultSetException e) {
		this.getBaseService().addBean(Loaner);

		
	 
	}
	
	

	
	return Loaner;
	
}

public LoanAccount  saveLoanerAccount(LoanAccount account) throws DataBaseException {
	
	

	
	
	try {
		Map <String,Object>m=new HashMap<String,Object>();
		m.put("loanerId", account.getLoanerId());
		m.put("type", account.getType());
		m.put("finished", 0);

		
		account=(LoanAccount) this.getBaseService().findAllBeans(LoanAccount.class, m, null).get(0);
		
		
		return account;
	} catch (DataBaseException | EmptyResultSetException e) {
		
		
		this.getBaseService().addBean(account);

		
	 
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

public List getLoanerDebts(int loanerId, String type) throws EmptyResultSetException, DataBaseException {
	
	return this.getExpansesDao().getLoanerDebts(loanerId, type);
}

@Override
public List getLoanerInstalments(int loanerId, String type) throws EmptyResultSetException, DataBaseException {
	return this.getExpansesDao().getLoanerInstalments(loanerId, type);
}

@Override
public void loanIncTansaction(String name, Date date, double amount, String type, String notes, int fridageId,
		int seasonId) throws DataBaseException {

	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	try {	
		//======================================================================
		Loaners loaner=new Loaners();
		loaner.setName(name);
		saveLoaner(loaner);		
		//======================================================================
		LoanAccount account=new LoanAccount();
		account.setType(type);
		account.setLoanerId(loaner.getId());
		saveLoanerAccount(account);
		//======================================================================
		IncLoan loan=new IncLoan();
		loan.setAmount(amount);
		loan.setLoanAccountId(account.getId());
		loan.setLoanDate(date);
		loan.setNotes(notes);
		this.getBaseService().addBean(loan);
		//======================================================================

		if(type.equals(LoanTypeEnum.IN_LOAN)) {
			incomeTransaction(date, amount, notes, IncomeTypesEnum.IN_LOAN, loaner.getId(), -1, fridageId, seasonId);
			
			
		}
		
		//======================================================================

		else if(type.equals(LoanTypeEnum.OUT_LOAN)) {
				outcomeTransaction(date, amount, notes, OutcomeTypeEnum.OUT_LOAN, loaner.getId(), -1, fridageId, seasonId);;

			}

	
		//======================================================================

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
public void changeSafeBalance(int safeId,double amount,int transactionType,String transactionName,int transactionId  ) throws DataBaseException, InvalidReferenceException {
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);
	Safe safe=(Safe)getSynchronizeBean(Safe.class, safeId);




	try {
		 double newBalance=safe.getBalance();
		if(transactionType==SafeTransactionTypeEnum.add)
			newBalance+=amount;
		else if(transactionType==SafeTransactionTypeEnum.subtract)
			newBalance-=amount;
 		
		SafeTracing tracing=new SafeTracing();
		tracing.setSafeId(safeId);
		tracing.setAmount(amount);
		tracing.setAfterAmount(newBalance);
		tracing.setBefaorAmount(safe.getBalance());
		tracing.setTransactionType(transactionType);
		tracing.setTransactionId(transactionId);

		tracing.setTransactionName(transactionName);

		safe.setBalance(newBalance);
		this.getBaseService().addBean(tracing);
		this.getBaseService().addEditBean(safe);
		entitDictionary.put(safe.getClass().getName(), safe);

		
		
	}catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException(e.getMessage());

	}finally {
		closeTransaction(status);

	}
	
	
}

@Override
public void changeOutcomeDetailAmount(int outcomeDetailId,double amount,int transactionTypeId  ) throws DataBaseException, InvalidReferenceException {


 

	//change outcome detail 
	OutcomeDetail detail=	 (OutcomeDetail) this.getBaseService().getBean(OutcomeDetail.class, outcomeDetailId);
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

 	try {
	
	
	
	double newAmount=detail.getAmount();
	
	
	if(transactionTypeId==SafeTransactionTypeEnum.add)
		newAmount+=amount;
	else if(transactionTypeId==SafeTransactionTypeEnum.subtract)
		newAmount-=amount;
		
  	detail.setAmount(newAmount);
	//=========================================================================================================================

	//change outcome  
	Outcome outcome= (Outcome) getSynchronizeBean(Outcome.class, detail.getOutcomeId());
	newAmount=outcome.getTotalOutcome();
	if(transactionTypeId==SafeTransactionTypeEnum.add)
		newAmount+=amount;
	else if(transactionTypeId==SafeTransactionTypeEnum.subtract)
		newAmount-=amount;
	
	outcome.setTotalOutcome(newAmount);
	//=========================================================================================================================

	this.getBaseService().addEditBean(detail);
	this.getBaseService().addEditBean(outcome);
	entitDictionary.put(outcome.getClass().getName(), outcome);

	 changeSafeBalance(outcome.getSafeId(), amount, SafeTransactionTypeEnum.add, detail.getType().getName(),detail.getId());
	
	
	


	}catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException(e.getMessage());

	}finally {
		closeTransaction(status);

	}





}
 
 private  Object getSynchronizeBean(Class<?>beanClass, Integer identifier) throws  InvalidReferenceException {
 	try{
		
		Object bean=entitDictionary.get(beanClass.getName());
		if(bean!=null)
			{
			Integer id=(Integer) invokeMethode(bean, "getId");
			 if(id.equals(identifier)) 			 
			return bean;
			 }
			
	 
			
			bean =this.getBaseService().getBean(beanClass, identifier);
			return bean;
			
			
		 
			 
	 
		
	}
 	catch (InvalidReferenceException e) {
 
 	throw new InvalidReferenceException(beanClass.getName() +" not found ");
 	}
 	catch (Exception e) {
e.printStackTrace();	}
	return null;
}







private Object invokeMethode(Object instance,String methodeName) {
	Object returnObj=null;
	try {
		
		
	Method methode= instance.getClass().getMethod(methodeName);
	returnObj=	methode.invoke(instance);
	
	
	
	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	return returnObj;

	
	
	
}


}
