package App.com.Customer.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.Customer.dao.ICustomerDao;
import App.core.Enum.VechileTypeEnum;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class CustomerDao extends HibernateDaoSupport implements  ICustomerDao{

	public List getSeasonCustomers(int seasonId,int fridageId,int typeId) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =
		  " select distinct c  from CustomerOrder co inner join co.customer c "
		  + "	 where co.seasonId= "+seasonId;
		  query +="	and c.typeId ="+typeId;
		  
		  if(fridageId!=0)
		  query +="	and co.fridageId ="+fridageId;

		  Query queryList = session.createQuery(query);

		  List<Object> result =queryList.list();
		  
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
	
	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =
		  "from CustomerOrder where to_char(orderDate,'DD/MM/YYYY') = to_char(?,'DD/MM/YYYY')";

		  query += " order by orderDate  desc";
			Map <String, Date>parameters =new HashMap<String, Date>();
			parameters.put("orderDate",orderDate);

		  
		  Query queryList = session.createQuery(query);
		  queryList.setDate(0, orderDate);
		
		  List<Object> result =		  queryList.list();
		  
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
	
	
	
	public List getCustomerOrders(int customerId,int seasonId,int fridageId ,int finished) throws EmptyResultSetException, DataBaseException {		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =
		  "from CustomerOrder where 1=1";
		  if(customerId!=0)
				 query +=" and customerId = "+customerId;
		  if(seasonId!=0)
			query+=" and seasonId="+seasonId;
		
		  if(fridageId!=0)
				 query +=" and fridageId = "+fridageId;
		  if(finished!=0)
			  query+=" and  finished ="+finished;
		
		 
		
		
		 query += " order by orderDate  desc";
	
		 
		 
		
		
		  List<Object> result =session.createQuery(query).list() ; 
		  
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
	
	public double  getPurchasedCustomerTotalDue(int seasonId,int customerId) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =" select sum (buyPrice)"+
		  "from CustomerOrder where seasonId="+seasonId
		 +" and  periodId =-1";
		
		 if(customerId!=0)
			 query +=" and customerId = "+customerId;

		
		 query += " order by orderDate  desc";
	
		 
		 
		
		
		  List<Object> result =session.createQuery(query).list() ; 
		  
		  if(result.size() == 0) {
			  throw new  EmptyResultSetException("error.emptyRS"); }
		  
		  if(result.size() > 0) 
		  {return (Double)result.get(0);}
		 } 
		  catch(DataAccessException e) { throw new
		  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
		  }
		  finally { session.close(); }
		  
		 
		return 0.0;}
	
	public double getPurchasedCustomerTotalInst(int seasonId,int customerId) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =" select sum (amount)"+
		  "from PurchasedCustomerInst "
		  + "where seasonId="+seasonId;
		
		 if(customerId!=0)
			 query +=" and customerId = "+customerId;

		
		 query += " order by instalmentDate  desc";
	
		 
		 
		
		
		  List<Object> result =session.createQuery(query).list() ; 
		  
		  if(result.size() == 0) {
			  throw new  EmptyResultSetException("error.emptyRS"); }
		  
		  if(result.size() > 0) 
		  {return (Double)result.get(0);}
		 } 
		  catch(DataAccessException e) { throw new
		  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
		  }
		  finally { session.close(); }
		  
		 
		return 0.0;}


	
	

	
	
	
	
	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException {
		

		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =
		  "from Season where currentSeason = 1";
		  Query queryList = session.createQuery(query);
		
		  List<Object> result =		  queryList.list();
		  
		  if(result.size() == 0) {
			  throw new  EmptyResultSetException("error.emptyRS"); }
		  
		  if(result.size() > 0) 
		  {return (Season) result.get(0);}
		 } 
		  catch(DataAccessException e) { throw new
		  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
		  }
		  finally { session.close(); }
		  
		 
		return null;
		
		
	}



	public List getCustomersSummaryTransactions(int seasonId,int fridageId,int customerId) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
	


		  String q ="select"
		  		+ " sum (od.amount) ,"
		  		+ "sum(co.netPrice),"
		  		+ "sum(co.commision)"
		  		+ " from "
		  		+ " CustomerOrder co , "
		  		+ " OutcomeDetail od "
		  		+ " where co.id=od.orderId"	
		        + "	 and  co.finished= 1"
		  		+ "	 and co.seasonId= "+seasonId ;
		  if(customerId!=0)
			  q +="	and co.customerId ="+customerId;
		  if(fridageId!=0)
			  q +="	and co.fridageId ="+fridageId;
		
		  
		  
		  
		  
		  String vechilrTypesQuery =
				  " select "
				  + "    count (co.vechileTypeId), co.vechileTypeId"
				
				  + " from CustomerOrder co  "
				  + "	 where co.seasonId= "+seasonId ;
				  if(customerId!=0)
					  vechilrTypesQuery +="	and co.customerId ="+customerId;
				  if(fridageId!=0)
					  vechilrTypesQuery +="	and co.fridageId ="+fridageId;
				  
				  vechilrTypesQuery +="	group by  co.vechileTypeId order by co.vechileTypeId asc ";

		  
		  
	
		  
		  
		  
		  
		  Query queryList = session.createQuery(q);

		  List result =queryList.list();
		   queryList = session.createQuery(vechilrTypesQuery);

		  List result2 =queryList.list();
		  Map<Object,Object>m=new HashMap<Object, Object>();
		  m.put(VechileTypeEnum.van, (long) 0);
		  m.put(VechileTypeEnum.car, (long) 0);
		
		  for (int i = 0; i < result2.size(); i++) {
			  Object[] temp=(Object[]) result2.get(i);
			  m.put(temp[1], temp[0]);
			
		}
		  
		  
		  long vanCount=(long) m.get(VechileTypeEnum.van);
		  long carCount=(long) m.get(VechileTypeEnum.car);
		  List finalResult=new ArrayList<Object>(Arrays.asList( ((Object[])result.get(0))[0],
				  ((Object[])result.get(0))[1],
				  ((Object[])result.get(0))[2],
				  vanCount,carCount ));
		
		  if(finalResult.size() == 0) {
			  throw new  EmptyResultSetException("error.emptyRS"); }
		  
		  if(finalResult.size() > 0) 
		  {return finalResult;}
		 } 
		  catch(DataAccessException e) { throw new
		  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
		  }
		  finally { session.close(); }
		  
		 
		return null;}
	
	
	
	 public List<String> getSuggestedCustomerName(String searchString,int customerTypeId) {

	        logger.info("Getting customer list for autocomplete");

	        List<String> list = null;

			 Session session = null; 

			  session =this.getSessionFactory().openSession();

	        try {


	            Query query = session.createQuery("select c.name from Customer c where c.name LIKE :search and typeId = :typeP");
	             query.setParameter("typeP", customerTypeId);

	            list = query.setParameter("search", "%"+searchString+"%").setMaxResults(10).list();


	        } catch (Exception ex) {

	            logger.error("Other exception {}", ex);

	        } finally {

	        	session.close();

	        }       

	        return list;

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
