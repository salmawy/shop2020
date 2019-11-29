package App.com.expanses.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.expanses.dao.IExpansesDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class ExpansesDao extends HibernateDaoSupport implements  IExpansesDao{

	
	
	
	
	
	
	 
	 
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

	
	 
	 public List getIncomeDays(String month) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Income "
		  		+ "where  to_char(  incomeDate ,'YYYY-MM')  = :month";

		  query += " order by incomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("month", month);

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

	

	 
	 
	 public List getIncomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
			String sql=" select distinct to_char(income0_.INCOME_DATE, 'YYYY-MM') as mon from INCOME income0_ where income0_.SEASON_ID="+seasonId+" order by mon desc";
				
		  Query queryList = session.createSQLQuery(sql);

		  
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

	
	 
	 public List getOutcomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
				String sql=" select distinct to_char(OUTCOME_DATE, 'YYYY-MM') as mon from OUTCOME  where SEASON_ID="+seasonId+" order by mon desc";

			
		  
		  Query queryList = session.createSQLQuery(sql);

		  
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

	 
	 public List getOutcomeDays(String month) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Outcome "
		  		+ "where  to_char(  outcomeDate ,'YYYY-MM')  = :month";

		  query += " order by outcomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("month", month);

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

	

	
	
	 
	 
	 public List getOutcome(Date date) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Outcome "
		  		+ "where  to_char(  outcomeDate ,'dd/MM/YYYY')  = "
		  		+ " to_char( ? ,'dd/MM/YYYY') ";							

		  query += " order by outcomeDate  desc";
			
		  
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

	
	
	
	
	
	
	
	
}
