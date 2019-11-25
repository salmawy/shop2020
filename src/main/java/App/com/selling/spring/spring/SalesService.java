package App.com.selling.spring.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import App.com.selling.action.SellingAction;
import App.com.selling.dao.ISalesDao;
import App.com.selling.spring.ISalesService;
import App.core.Enum.IncomeTypesEnum;
import App.core.Enum.SellerTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.CustomerOrder;
import App.core.beans.Income;
import App.core.beans.IncomeDetail;
import App.core.beans.Installment;
import App.core.beans.Safe;
import App.core.beans.Seller;
import App.core.beans.SellerLoanBag;
import App.core.beans.SellerOrder;
import App.core.beans.SellerOrderWeight;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class SalesService implements ISalesService {
	
	ISalesDao salesDao;
	IBaseRetrievalService baseRetrievalService;
	IBaseService baseService;
	Logger logger = Logger.getLogger(this.getClass().getName());	
	private ResourceBundle settingsBundle = ResourceBundle.getBundle("ApplicationSettings_ar");
	
	
	public ISalesDao getSalesDao() {
		return salesDao;
	}
	public void setSalesDao(ISalesDao salesDao) {
		this.salesDao = salesDao;
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

	/*
	 * getAllCustomersOrdersTags
	 * */
@SuppressWarnings("unchecked")
public List getAllCustomersOrdersTags(int seasonId,int fridageId,int productId,int storeId) {
	
	
	List result=new ArrayList();
	Map map=new HashMap();
	map.put("productId", productId);
	map.put("fridageId", fridageId);
	map.put("storeId", storeId);
	map.put("finished",0 );
	
	
	try {
		result=this.getBaseService().findAllBeans(CustomerOrder.class,map,null);
	} catch (DataBaseException | EmptyResultSetException e) {
		e.printStackTrace();
	}
return result;
	
	
	
	
	
	
	
}

public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException {
	
return	this.getSalesDao().aggregate(tablename, operation, columnName, parameters);
	
	
}





public List<String> getSuggestedSellerName(String searchString) {

return this.getSalesDao().getSuggestedSellerName(searchString);
}

public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {

return this.getSalesDao(). getSellersOrders(orderDate);
}





public void saveSellerOrder(Seller seller, SellerOrder sellerOrder,double paidAmount) throws Exception {
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	
	
	try {
		seller=saveSeller(seller);

		switch(seller.getTypeId()) {
		
		case SellerTypeEnum.cash:
			sellerOrder.setSellerLoanBagId(0);
			sellerOrder.setSellerId(seller.getId());

			this.getBaseService().addBean(sellerOrder);

			IncomeDetail incomeDetail=new IncomeDetail();
			incomeDetail.setAmount(paidAmount);
			incomeDetail.setFridageId(sellerOrder.getFridageId());
			incomeDetail.setResipeintName(ApplicationContext.currentUser.getUsername());
			incomeDetail.setSellerId(seller.getId());
			incomeDetail.setTypeId(IncomeTypesEnum.CASH);
			incomeDetail.setType(String .valueOf(IncomeTypesEnum.CASH));

			incomeDetail.setSellerOrderId(sellerOrder.getId());

			saveIncomeDetail(incomeDetail,sellerOrder.getOrderDate());
			
			
			break;
		case SellerTypeEnum.permenant:
			int  bagId=saveAndUpdateSellerLoanBag(seller, sellerOrder.getSeasonId(), sellerOrder.getTotalCost(), paidAmount);
			sellerOrder.setSellerLoanBagId(bagId);
			sellerOrder.setSellerId(seller.getId());
			this.getBaseService().addBean(sellerOrder);

			if(paidAmount>0)
				saveSellerInstalment(seller.getId(), sellerOrder.getId(),bagId, sellerOrder.getFridageId(), paidAmount, sellerOrder.getOrderDate(), "");
		break;
		}
		
		
		
		
		List orderdetail=new ArrayList();
		for (Iterator iterator = sellerOrder.getOrderWeights().iterator(); iterator.hasNext();) {
			SellerOrderWeight temp = (SellerOrderWeight) iterator.next();
			temp.setSellerOrderId(sellerOrder.getId());
			orderdetail.add(temp);
			
		}
		// to update seller order detail by new after saving into database and take new 
		//its new id from database
		this.getBaseService().addEditBeans(orderdetail);
		this.getMyTransactionManager().commit(status);
		logger.log(Level.INFO,"tranasction completed succfully");
		
		
	} catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new Exception();

	}finally {
		//this.getMyTransactionManager().rollback(status);
		closeTransaction(status);

	}
	
	
	
}




public void editSellerOrder(Seller seller, SellerOrder sellerOrder,double paidAmount,SellerOrder oldOrder) throws Exception {
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);
	
	try {
		deleteOldSellerOrder(oldOrder);
	    saveSellerOrder(seller, sellerOrder, paidAmount);
	    
		this.getMyTransactionManager().commit(status);

	    
	} catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new Exception();

	}finally {
		//this.getMyTransactionManager().rollback(status);
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
		totalIncome=(Double) aggregate("IncomeDetail incomeDetail", "sum", "amount", map);
		totalIncome=(totalIncome==null)?0.0:totalIncome;
		totaloutcome=(Double) aggregate("OutcomeDetail outcomeDetail", "sum", "amount", map2);
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
public int saveAndUpdateSellerLoanBag(Seller seller,int seasonId,double orderCost,double paidAmount) throws DataBaseException {
	double currentloan=0;
	SellerLoanBag bag=findSellerLoanBag(seller.getId(), seasonId);
	
	bag.setDueLoan(bag.getDueLoan()+orderCost);;
	bag.setPaidAmount(bag.getPaidAmount()+paidAmount);
	currentloan=(bag.getPriorLoan()+bag.getDueLoan())-paidAmount;
	bag.setCurrentLoan(currentloan);
		
	
	
		this.getBaseService().addEditBean(bag);
	
	
	return bag.getId();
	
	
}

public Seller  saveSeller(Seller seller) throws DataBaseException, InvalidReferenceException {
	
	

	
	
	if (seller.getTypeId()==SellerTypeEnum.permenant) {
	try {
		Map <String,Object>m=new HashMap<String,Object>();
		m.put("name", seller.getName());
		seller=(Seller) this.getBaseService().findAllBeans(Seller.class, m, null).get(0);
		
		
		return seller;
	} catch (DataBaseException | EmptyResultSetException e) {
		this.getBaseService().addBean(seller);

		
	}
	}
	
	else if (seller.getTypeId()==SellerTypeEnum.cash) {
		
		seller=(Seller) this.getBaseService().getBean(Seller.class, SellingAction.CashId);

		
		
	}
	

	
	return seller;
	
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
			
			income=	(Income) this.getSalesDao().getIncome(date).get(0);
			
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


public SellerLoanBag findSellerLoanBag(int sellerId,int seasonId) throws DataBaseException {
	Map<String,Object> m=new HashMap<String,Object>();
	m.put("sellerId", sellerId);
	m.put("seasonId", seasonId);
	try {
		return 		(SellerLoanBag) this.getBaseService().findAllBeans(SellerLoanBag.class,m,null ).get(0);

	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	SellerLoanBag bag=new SellerLoanBag();
	bag.setPriorLoan(0.0);
	bag.setSellerId(sellerId);

	this.baseService.addBean(bag);
 
	
	return bag;
	
	
	
	
	
	
}

public void saveSellerInstalment(int sellerId,int sellerOrderId,int sellerLoanBagId ,int fridageId,double amount,Date date,String notes) throws DataBaseException {
	
	Installment installment=new Installment();
	installment.setInstalmentDate(date);
	installment.setAmount(amount);
	installment.setSellerLoanBagId(sellerLoanBagId);
	installment.setNotes(notes);
	if(sellerOrderId!=0)
	{
		installment.setSellerOrderId(sellerOrderId);
		
	}
		this.getBaseService().addBean(installment);
		IncomeDetail incomeDetail=new IncomeDetail();
		incomeDetail.setAmount(amount);
		incomeDetail.setFridageId(fridageId);
		incomeDetail.setResipeintName("");
		incomeDetail.setNotes(notes);
		incomeDetail.setSellerId(sellerId);
		incomeDetail.setTypeId(IncomeTypesEnum.INTST_PAY);
		incomeDetail.setType(String.valueOf(IncomeTypesEnum.INTST_PAY));

		if(sellerOrderId!=0)
		{
			incomeDetail.setSellerOrderId(sellerOrderId);
			
		}
		saveIncomeDetail(incomeDetail, date);
	
}

private void deleteOldSellerOrder(SellerOrder order) throws Exception {
	
	TransactionStatus status = null;

	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
	def.setTimeout(Integer.parseInt(this.getSettingsBundle().getString("transactionTimeOut.base.highTimeOut")));
	status = this.getMyTransactionManager().getTransaction(def);

	
	
	try {
		Seller seller=order.getSeller();
		switch(seller.getTypeId()) {
		
		case SellerTypeEnum.cash:
			
			Map<String,Object> map=new HashMap<String, Object>();

			map.put("sellerOrderId", order.getId());
			try {
			IncomeDetail incomeDetail= (IncomeDetail)this.getBaseRetrievalService().findBean(IncomeDetail.class, map);
			this.getBaseService().deleteBean(incomeDetail);
			recalculateSafeBalance(order.getSeasonId());
			
			}catch (InvalidReferenceException e) {
				// TODO: handle exception
			}
			
		
			this.getBaseService().deleteBean(order);

			break;
		case SellerTypeEnum.permenant:
		     map=new HashMap<String, Object>();

			map.put("sellerOrderId", order.getId());
			try {
			IncomeDetail incomeDetail= (IncomeDetail)this.getBaseRetrievalService().findBean(IncomeDetail.class, map);
			
			
			this.getBaseService().deleteBean(incomeDetail);
			recalculateSafeBalance(order.getSeasonId());
			
			}catch (InvalidReferenceException e) {
				// TODO: handle exception
			}
			
			
			//=========================delete installment============================
			try {
				Installment installment= (Installment)this.getBaseRetrievalService().findBean(Installment.class, map);
						
				this.getBaseService().deleteBean(installment);
				
				}catch (InvalidReferenceException e) {
					// TODO: handle exception
				}
			
			//================================recalculate loan bag after deleting order======================
			int sellerId=order.getSellerId();
			int seasonId=order.getSeasonId();
			this.getBaseService().deleteBean(order);
			recalculeSellerLoanBag(seasonId, sellerId);
			
		
			
			break;
		}
		

		this.getMyTransactionManager().commit(status);
		logger.log(Level.INFO,"tranasction completed succfully");
		
		
	} catch (DataBaseException e) {
		this.getMyTransactionManager().rollback(status);
		logger.log(Level.SEVERE,e.getMessage());
		throw new DataBaseException("err.deleting.sellerOrder");

	}finally {
		//this.getMyTransactionManager().rollback(status);
		closeTransaction(status);

	}
	
	
	
	
}




public void recalculeSellerLoanBag(int seasonId,int sellerId) {
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("seasonId=", seasonId);
	map.put("sellerId=", sellerId);
	
	
	
	Double ordersCost=0.0;
	Double totalPaidAmount=0.0;

	try {
		
		SellerLoanBag bag= findSellerLoanBag(sellerId, seasonId);
		Map<String,Object> map2=new HashMap<String, Object>();
		map2.put("sellerLoanBagId=", bag.getId());
		
		
		ordersCost=(Double) aggregate("SellerOrder", "sum", "totalCost", map);
		ordersCost=(ordersCost==null)?0.0:ordersCost;
		
		
		
		
		totalPaidAmount=(Double) aggregate("Installment", "sum", "amount", map2);
		totalPaidAmount=(totalPaidAmount==null)?0.0:totalPaidAmount;
		
	
		double temp=(bag.getPriorLoan()+ordersCost)-totalPaidAmount;
		bag.setPaidAmount(totalPaidAmount);
		bag.setDueLoan(ordersCost);
		bag.setCurrentLoan(temp);
		
		this.getBaseService().addEditBean(bag);
		
		
		

	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
	
}

















public ResourceBundle getSettingsBundle() {
	return settingsBundle;
}
}
