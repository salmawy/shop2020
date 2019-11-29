package App.com.Customer.service.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import App.com.Customer.dao.ICustomerDao;
import App.com.Customer.service.ICustomerService;
import App.core.Enum.CustomerTypeEnum;
import App.core.Enum.OutcomeTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Income;
import App.core.beans.Outcome;
import App.core.beans.OutcomeDetail;
import App.core.beans.Safe;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class CustomerService implements ICustomerService {
	
	ICustomerDao customerDao;
	IBaseRetrievalService baseRetrievalService;
	IBaseService baseService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	
	public ICustomerDao getCustomerDao() {
		return customerDao;
	}


	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}


	public IBaseService getBaseService() {
		return baseService;
	}


	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}


	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}


	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {		
		return this.customerDao.getCustomerOrders(orderDate);
	}
	public List getCustomerInvoices(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException 
		{
		return this.getCustomerDao().getCustomerOrders(customerId, seasonId, fridageId, 1);	
		}
		
		
	public List getCustomerOrders(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException 
	{
	return this.getCustomerDao().getCustomerOrders(customerId, seasonId, fridageId, 0);	
	}
	
	public List getSeasonCustomers(int seasonId,int fridageId,int typeId) throws EmptyResultSetException, DataBaseException {
	return this.getCustomerDao().getSeasonCustomers( seasonId ,fridageId, typeId);
	
	}



	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException {
		return this.getCustomerDao().getCurrentSeason();
	}
	public List getCustomersSummaryTransactions(int seasonId,int fridageId,int customerId) throws EmptyResultSetException, DataBaseException {
	
	return this.getCustomerDao().getCustomersSummaryTransactions(seasonId, fridageId, customerId);
	}


	@Override
	public List getPurchasedCustomerData(int seasonId, int fridageId)
			throws EmptyResultSetException, DataBaseException {
		
		Map <String,Object>m=new HashMap<String,Object>();
		m.put("customer.typeId", CustomerTypeEnum.purchases);
		m.put("seasonId", seasonId);
		m.put("periodId", -1);
		List data=new ArrayList(); 
	
		try {
			List<Object>result= this.getSeasonCustomers(seasonId, 0, CustomerTypeEnum.purchases);
			for (Object it : result) {
				Customer customer=(Customer) it;
				double cost=this.getCustomerDao().getPurchasedCustomerTotalDue(seasonId, customer.getId());
				double paidAmount=this.getCustomerDao().getPurchasedCustomerTotalInst(seasonId, customer.getId());
				double dueAmount=cost-paidAmount;
				
				List temp= new ArrayList<>(Arrays.asList( 
						customer.getId()
						, customer.getName()
						,cost
						,paidAmount
						,dueAmount
						));
				
				data.add(temp);
				
			}
			
			
		
			
			
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return data;
	}


	 public List<String> getSuggestedCustomerName(String searchString,int customerTypeId) {
	 
	 return this.getCustomerDao().getSuggestedCustomerName(searchString,customerTypeId);
	 }
	 
	 
	 public void saveCustomerOrder(CustomerOrder customerOrder) throws DataBaseException {

			TransactionStatus status = null;

			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
			def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
			status = this.getMyTransactionManager().getTransaction(def);

			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD");
			try {
		//===================save customer into Database ========================================================	 
		 Customer customer=saveCustomer(customerOrder.getCustomer());
			
		 
//========================save customer Order into Database ========================================================	
		 customerOrder.setCustomerId(customer.getId());
		 this.getBaseService().addBean(customerOrder);
		 String orderTag=(customerOrder.getCustomer().getName()+"_"+sdf.format(customerOrder.getOrderDate())+"_"+customerOrder.getOrderTag()+"_" +customerOrder.getId());
		customerOrder.setOrderTag(orderTag);
		this.getBaseService().addEditBean(customerOrder);
		
//=============================== save outcome transactions=====================================

		OutcomeDetail tips=new OutcomeDetail();
		tips.setAmount(customerOrder.getTips());
		tips.setFridageId(customerOrder.getFridageId());
		tips.setSpenderName(ApplicationContext.currentUser.getUsername());
		tips.setCustomerId(customerOrder.getCustomer().getId());
		tips.setTypeId(OutcomeTypeEnum.TIPS);
		tips.setTypeName(String .valueOf(OutcomeTypeEnum.TIPS));
		tips.setOrderId(customerOrder.getId());
		
		
		OutcomeDetail nolun=new OutcomeDetail();
		nolun.setAmount(customerOrder.getNolun());
		nolun.setFridageId(customerOrder.getFridageId());
		nolun.setSpenderName(ApplicationContext.currentUser.getUsername());
		nolun.setCustomerId(customerOrder.getCustomer().getId());
		nolun.setTypeId(OutcomeTypeEnum.NOLOUN);
		nolun.setTypeName(String .valueOf(OutcomeTypeEnum.NOLOUN));
		nolun.setOrderId(customerOrder.getId());

		Outcome outome=findOutcome(customerOrder.getOrderDate());
		saveOutcomeDetail(tips, outome);
		saveOutcomeDetail(nolun, outome);

		
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

	 
	 public Customer  saveCustomer(Customer customer) throws DataBaseException {
			

			try {
				Map <String,Object>m=new HashMap<String,Object>();
				m.put("name", customer.getName());
				customer=(Customer) this.getBaseService().findAllBeans(Customer.class, m, null).get(0);
				
				
				return customer;
			} catch (DataBaseException | EmptyResultSetException e) {
				
				
				this.getBaseService().addBean(customer);

				
			}
			
			return customer;
			
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
	 
		public ResourceBundle getSettingsBundle() {
			return settingsBundle;
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
			
			return	(Outcome) this.getCustomerDao().getOutcome(date).get(0);
			
		
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

	
	
	public Income updateincome(int id ,double amount) throws DataBaseException, InvalidReferenceException {
	
	
	Income income=(Income) this.getBaseService().getBean(Income.class, id);
	income.setTotalAmount(income.getTotalAmount()+amount);
	
	return income;
	
	
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










public void editCustomerOrder(CustomerOrder newValue,CustomerOrder oldValue) throws DataBaseException {
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);
	
	try {
		deleteOldCustomerOrder(oldValue);
	    saveCustomerOrder(newValue);
	    
		this.getMyTransactionManager().commit(status);

	    
	} catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException(e.getMessage());

	}finally {
		//this.getMyTransactionManager().rollback(status);
		closeTransaction(status);

	}
	
	
	
}


private void deleteOldCustomerOrder(CustomerOrder order) throws DataBaseException {

	
	Map<String, Object> map=new HashMap<String, Object>();

				map.put("orderId", order.getId());
				map.put("typeId", OutcomeTypeEnum.NOLOUN);


				try {
				this.getBaseRetrievalService().findAllBeans(OutcomeDetail.class, map,null).get(0);

					OutcomeDetail nolun= (OutcomeDetail)this.getBaseRetrievalService().findAllBeans(OutcomeDetail.class, map,null).get(0);
					this.getBaseService().deleteBean(nolun);
					
					map=new HashMap<String, Object>();
					map.put("orderId", order.getId());
					map.put("typeId", OutcomeTypeEnum.TIPS);
					
					OutcomeDetail tips= (OutcomeDetail)this.getBaseRetrievalService().findAllBeans(OutcomeDetail.class, map,null).get(0);
					this.getBaseService().deleteBean(tips);

				
				recalculateSafeBalance(order.getSeasonId());
				
				}catch (EmptyResultSetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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







}
