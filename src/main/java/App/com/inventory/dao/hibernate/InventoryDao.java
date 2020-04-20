package App.com.inventory.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.inventory.dao.IInventoryDao;
import App.core.Enum.CustomerTypeEnum;
import App.core.Enum.OutcomeTypeEnum;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class InventoryDao extends HibernateDaoSupport implements  IInventoryDao{ 
	
	
	@Override
 public List getInventoryDates( int seasonId) throws EmptyResultSetException, DataBaseException {
		
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select distinct to_char( orderDate,'MM-YYYY') from customerDate "
		+ " where seasonId :seasonId ";
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List<Object> result =	 queryList.list();
	  
	  if(result.size() == 0) {
		  throw new  EmptyResultSetException("error.emptyRS"); }
	  
	  if(result.size() > 0) 
	  {return result;}
	 } 
	  catch(DataAccessException e) { throw new
	  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
	  }
	  finally { session.close(); }
	  
	 
	return null;}


	@Override
 public double getPurchasesProfit(String month, int seasonId, int fridageId) {
	 
	 double value=0.0;
	 
	 
		
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select sum(commision) from CustomerOrder"
	  + " where periodId=-1"
	  + " and finished=1"
	  + " and buyPrice>0 "
	  + " and seasonId  ="+seasonId;
	  if(fridageId!=0)
		  query+=" and fridageId ="+fridageId;
	  if(month!=null)
		  query+=" and to_char(orderDate,'MM-YYYY') ="+month;
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List result =	 queryList.list();
	  
  	  
	  if(result.size() > 0) 
	  value= (double) result.get(0);
	 } 
	  catch(Exception e) {
  	  }
	  finally { session.close(); }
	  
	 
	
	 
	 return value;
 }
 public double getCommisionProfit(String month, int seasonId, int fridageId) {

		 
		 double value=0.0;
		 
		 
			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		 
		  String query =
		  " select sum(commision) from CustomerOrder"
		  + " where periodId=-1"
		  + " and finished=1"
		  + " and buyPrice=0 "
		  + " and seasonId  ="+seasonId;
		  if(fridageId!=0)
			  query+=" and fridageId ="+fridageId;
		  if(month!=null)
			  query+=" and to_char(orderDate,'MM-YYYY') ="+month;
		  
		  
	 	  query += " order by orderDate  desc";
		
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("seasonId", seasonId);
	 
		  List result =	 queryList.list();
		  
	  	  
		  if(result.size() > 0) 
		  value= (double) result.get(0);
		 } 
		  catch(Exception e) {
	  	  }
		  finally { session.close(); }
		  
		 
		
		 
		 return value;
	 
		 
	 }
	 @Override
 public double getKTotalOrders(String month, int seasonId, int fridageId) {

	 
	 double value=0.0;
	 
	 
		
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select sum(netPrice) from CustomerOrder"
	  + " where customer.typeId= "+CustomerTypeEnum.kareem
	  + " and finished=1"
	 
	  + " and seasonId  ="+seasonId;
	  if(fridageId!=0)
		  query+=" and fridageId ="+fridageId;
	  if(month!=null)
		  query+=" and to_char(orderDate,'MM-YYYY') ="+month;
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List result =	 queryList.list();
	  
  	  
	  if(result.size() > 0) 
	  value= (double) result.get(0);
	 } 
	  catch(Exception e) {
  	  }
	  finally { session.close(); }
	  
	 
	
	 
	 return value;
 
	 
 }
  @Override
 public double getkaremmTotalWithdrawal(String month, int seasonId, int fridageId,int contractorType) {

	 
	 double value=0.0;
 
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select sum(amount) from ContractorAccountDetail"
	  + " where seasonId= "+seasonId
	  + " and paid=1";
	 
 	  if(month!=null)
		  query+=" and to_char(detailDate,'MM-YYYY') ="+month;
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List result =	 queryList.list();
	  
  	  
	  if(result.size() > 0) 
	  value= (double) result.get(0);
	 } 
	  catch(Exception e) {
  	  }
	  finally { session.close(); }
	  
	 
	
	 
	 return value;
 
	 
 }
 @Override
 public double getTotalOutcome(String month, int seasonId, int fridageId) {

	 
	 double value=0.0;
		 
	              
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select sum(amount) from outcomeDetail"
	  + " where seasonId= "+seasonId
	  + " and paid=1"
	  + " and typeId  in ("
	  +OutcomeTypeEnum.labours+","
	  +OutcomeTypeEnum.varaity+","
	  +OutcomeTypeEnum.maintaince+","
	  +OutcomeTypeEnum.forgivness+","
	  +OutcomeTypeEnum.allah+")";
 	  if(month!=null)
		  query+=" and to_char(detailDate,'MM-YYYY') ="+month;
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List result =	 queryList.list();
	  
  	  
	  if(result.size() > 0) 
	  value= (double) result.get(0);
	 } 
	  catch(Exception e) {
  	  }
	  finally { session.close(); }
	  
	 
	
	 
	 return value;
 
	 
 }


 


      @Override
 public double getSalamiProductsProfit(String month, int seasonId, int fridageId,int productId) {
         

	 


	 
	 double value=0.0;
		 
	              
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	 
	  String query =
	  " select sum(amount) from SellerOrderWeight"
	  + " where seasonId= "+seasonId
	  + " and productId="+productId;
	  if(fridageId!=0)
		  query+=" and sellerOrder.fridageId ="+fridageId;
 	  if(month!=null)
		  query+=" and to_char(detailDate,'MM-YYYY') ="+month;
	  
	  
 	  query += " order by orderDate  desc";
	
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("seasonId", seasonId);
 
	  List result =	 queryList.list();
	  
  	  
	  if(result.size() > 0) 
	  value= (double) result.get(0);
	 } 
	  catch(Exception e) {
  	  }
	  finally { session.close(); }
	  
	 
	
	 
	 return value;
 
	 
 
	 
	 
	 
	 
	 
 

	 
	 
}
	
	
}
	
	
	
	
	
	
	

