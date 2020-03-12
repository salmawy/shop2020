package App.com.billing.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.billing.dao.IBillingDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class BillingDao extends HibernateDaoSupport implements IBillingDao{
	
	
	
@Override
	public List getSuggestedCustomersOrders( int seasonId, int fridageId) throws EmptyResultSetException, DataBaseException {
	
	
	
	

	
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	  
		    String query=" select distinct co.customer.id,co.customer.name,co.customer.typeId,co.invoiceStatus from " + "	 CustomerOrder co " +
					  " where co.fridageId=  "+fridageId + "	and co.periodId=-1 " +
					  " and co.seasonId=  "+seasonId;
					 

 				 
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
	  
	 
	return null;
	} 
	 
@Override
	public List getSuggestedCustomersOrders(int status, int seasonId, int fridageId, int typeId) throws EmptyResultSetException, DataBaseException {
	
	
	
	

	
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	  
		    String query="" + "select distinct customer from " + "	 CustomerOrder co " +
					  " where co.fridageId=  "+fridageId + "	and co.periodId=-1 " +
					  " and co.seasonId=  "+seasonId + " and co.invoiceStatus=  "+status ;
					  
					  if(typeId!=0) {
						  query+= " and co.customer.typeId ="+typeId;
					  
					  }
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
	  
	 
	return null;
	} 
	 
@Override
public List getCustomersOrderWeights(int orderId) throws EmptyResultSetException, DataBaseException {


  Session session = null; 
  try { 
	  session =this.getSessionFactory().openSession();
  
	    String query="select "
	    		+ " sum(amount),"
	    		+ "	unitePrice,"
	    		+ " sum(netQuantity), "
	    		+ " sum(packageNumber) "
	    		+ " from  SellerOrderWeight"
	    		+ " where  customerOrderId="+orderId 
	    		+ " group by unitePrice " ;

				  
				 
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
  
 
return null;
} 

	

	
	

}
