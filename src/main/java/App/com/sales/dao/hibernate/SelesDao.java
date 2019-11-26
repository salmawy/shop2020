package App.com.sales.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.sales.dao.ISalesDao;
import App.core.beans.SellerLoanBag;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class SelesDao extends HibernateDaoSupport implements  ISalesDao{
	
	
	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException {
		
		
		
		 Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		String query="select "+operation+"("+columnName+") from "+tablename+ " where 1=1";
		
	if(parameters!=null)
	     for (Map.Entry entry :	parameters.entrySet())  {

	    	 query+=" and "+String.valueOf( entry.getKey()) +String.valueOf(entry.getValue());
	    }
	     
	        List result=  session.createQuery(query).list();
	        
	        if(result.size() == 0) {
				  throw new  EmptyResultSetException("error.emptyRS"); }
			 
			  if(result.size() > 0) 
			  { return (result.get(0)==null)?null:result.get(0) ;}
	      
	        
		  }catch(DataAccessException e) {
			  throw new
			  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
			  }
			  finally { session.close();
			  }
		return null;
		  
		  
	}
	
	
	 public List<String> getSuggestedSellerName(String searchString) {

	        logger.info("Getting seller list for autocomplete");

	        List<String> list = null;

			 Session session = null; 

			  session =this.getSessionFactory().openSession();

	        try {


	            Query query = session.createQuery("select s.name from Seller s where s.name LIKE :search");

	            list = query.setParameter("search", "%"+searchString+"%").setMaxResults(10).list();


	        } catch (Exception ex) {

	            logger.error("Other exception {}", ex);

	        } finally {

	        	session.close();

	        }       

	        return list;

	    } 
	 
	 
	 
	 
	 public List getIncome(Date date) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Income "
		  		+ "where  to_char(  incomeDate ,'dd/MM/YYYY')  = "
		  		+ " to_char( ? ,'dd/MM/YYYY') ";							

		  query += " order by incomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setDate(0, date);
		//  queryList.setDate(1, upper.getTime());

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
		  
		 
		return null;
		 
		 
		 
	 } 

	 public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {
			
			  Session session = null; 
			  try { 
				  session =this.getSessionFactory().openSession();
			 
			  String query =
			  "from SellerOrder "
				+ "where  to_char(  orderDate ,'dd/MM/YYYY')  = "
		  		+ " to_char( ? ,'dd/MM/YYYY') ";
			  query += " order by orderDate  desc";
			
			  
			  Query queryList = session.createQuery(query);
			  queryList.setDate(0, orderDate);

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
		
		
	 public List getSellerDebt(int sellerId, int seasonId) throws EmptyResultSetException, DataBaseException {
			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		 
		  String query =
		  "from SellerLoanBag "
			+ " where seasonId :seasonId"
	  		+ " and sellerId= :sellerId";
		  query += " order by sellerId  desc";
		
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("seasonId", seasonId);
		  queryList.setParameter("sellerId", sellerId);

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
	
	 public List getSellersDebts( int seasonId,int active) throws EmptyResultSetException, DataBaseException {
			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		 
		  String query =
		  "from SellerLoanBag "
			+ " where seasonId = :seasonId";
		
		  query+=(active==0)?"  and currentLoan=0 ":" and currentLoan>0  ";
		  query += " order by sellerId  desc";
		
		  
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

	 
	 

	 
}
	
	
	
	
	
	
	
	
	
	

