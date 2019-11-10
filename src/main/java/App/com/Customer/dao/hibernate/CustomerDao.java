package App.com.Customer.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.Customer.dao.ICustomerDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class CustomerDao extends HibernateDaoSupport implements  ICustomerDao{

	
	
	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {
		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
		  String query =
		  "from CustomerOrder where orderDate = ?";

		  query += " order by orderDate  desc";
			Map <String, Date>parameters =new HashMap<String, Date>();
			parameters.put("orderDate",orderDate);

		  
		  Query queryList = session.createQuery(query);
		  queryList.setDate(0, orderDate);
			/*
			 * Iterator<String> iter = parameters.keySet().iterator(); while
			 * (iter.hasNext()) { String name = (String) iter.next(); Object value =
			 * parameters.get(name); queryList.setParameter(name,value); }
			 */
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
	
	
	
	
}
