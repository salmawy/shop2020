package App.core.dao.hibernate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.core.beans.Fridage;
import App.core.beans.Season;
import App.core.dao.IBaseDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.exception.PrimaryKeyViolatedException;
import App.core.exception.UniquePropertyViolatedException;

/**
 * 
 * @author YeHia
 *
 */

/**
 * 
 * this is the base for all the daos containing the basic operations
 *
 */

@SuppressWarnings("unchecked")
public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao 
{
	
	
	public Object findBean(Class<?> beanClass, Object identifier) throws DataException,InvalidReferenceException, DataBaseException
	{
		List<Object> result;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			criteria.add(Restrictions.idEq(identifier));
						
			result = criteria.list();
			
			if(result.size()==0)
			{
				String temp=beanClass.toString();
				int x=temp.lastIndexOf(".");
				
				String errorClass =temp.substring(x+1,temp.length());
				
				throw new InvalidReferenceException("error.invalidRef,"+errorClass);

			}
			
			return result.iterator().next();
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}	
		/*		
		 * Catching specific exceptions like (InvalidReferenceException, EmptyResultSetException,...) is not allowd 
		 * in the Dao we catch only Data Access Exception and throw the exceptions listed in the method definition
		 */
//		catch(InvalidReferenceException e)
//		{
//			
//			String temp=beanClass.toString();
//			int x = temp.lastIndexOf(".");
//			String errorClass =temp.substring(x+1,temp.length()); 
//			throw new InvalidReferenceException("error.dataBase.query,"+errorClass+","+e.getMessage());
//		}
		finally
		{
			session.close();
		}
	}
	
	public List<Object> executeMappedSQLStatement(String sqlQuery) throws DataBaseException
	{
		List<Object> result = null;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			//result = session.createSQLQuery(sqlQuery).addEntity(String.class).list();
			session.createSQLQuery(sqlQuery).addScalar("xx",Hibernate.INTEGER).uniqueResult();
			return result;
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.query,general");
		}		
		finally
		{
			session.close();
		}		
	}
	
	public Map<ResultSet,Session> executeSQLStatement(String sqlQuery) throws DataBaseException,EmptyResultSetException
	{
		
		Session session = null;
		
		try
		{
			
				session = this.getSessionFactory().openSession();
				Statement statement = session.connection().createStatement();
				ResultSet result = statement.executeQuery(sqlQuery);
				Map <ResultSet,Session>mapSession=new HashMap <ResultSet,Session>();
				mapSession.put(result, session);
				return mapSession;
			
			
		}catch (SQLException e) 
		{
			SQLErrorCodeSQLExceptionTranslator sqlExceptionTranslator = (SQLErrorCodeSQLExceptionTranslator)this.getHibernateTemplate().getJdbcExceptionTranslator();
			throw sqlExceptionTranslator.translate("general query", sqlQuery, e);
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.query,general");
		}
		
		finally
		{
			//session.close();
		}
	}
	
	public void validateIdentifier(Class beanClass, Object instance) throws DataBaseException,PrimaryKeyViolatedException
	{
		List result;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			criteria.add(Example.create(instance).excludeNone().excludeZeroes().excludeProperty("timestamp").excludeProperty("changerId"));
						
			result = criteria.list();
			
			if(result.size()==0)
				return;
			
			String temp=beanClass.toString();
			int x=temp.lastIndexOf(".");
			
			String errorClass =temp.substring(x+1,temp.length());
			
			throw new PrimaryKeyViolatedException("error.primaryKeyViolated,"+errorClass);
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}		
		finally
		{
			session.close();
		}
	}
	
	public void validateUniqueness(Class beanClass, Object instance) throws DataBaseException,UniquePropertyViolatedException
	{
		List result = null;
		Session session = null;
		
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			criteria.add(Example.create(instance).excludeProperty("timestamp").excludeProperty("changerId"));
			
			result = criteria.list();
			
			if(result.size()==0)
				return;
			
			String temp=beanClass.toString();
			int x=temp.lastIndexOf(".");
			
			String errorClass =temp.substring(x+1,temp.length());
			
			throw new UniquePropertyViolatedException("error.uniquePropertyViolated,"+errorClass);
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}		
		finally
		{
			session.close();
		}
	}

	
	public void validateUniqueness(Class beanClass, Map propertyMap) throws DataBaseException,UniquePropertyViolatedException
	{
		List result = null;
		Session session = null;
		
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			String key;
			for(Iterator itr = propertyMap.keySet().iterator() ; itr.hasNext() ;)
			{
				key = (String)itr.next();
				criteria.add(Restrictions.eq(key,propertyMap.get(key)));
			}
			
			result = criteria.list();

			if(result.size()==0)
				return;
			
			String temp=beanClass.toString();
			int x=temp.lastIndexOf(".");
			
			String errorClass =temp.substring(x+1,temp.length());
			
			throw new UniquePropertyViolatedException("error.uniquePropertyViolated,"+errorClass);
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}		
		finally
		{
			session.close();
		}
	}

	
	public void insertBean(Object newBean)throws DataBaseException
	{
		try 
		{
			if(newBean!=null)
			{
				getHibernateTemplate().save(newBean);
				getHibernateTemplate().flush();
			}

		}
		catch (DataAccessException e) 
		{						
			throw new DataBaseException("error.dataBase.insert,"+newBean.getClass().getSimpleName()+","+e.getMessage());		
		}
	}
	
	public void insertBeans(List beans)throws DataBaseException
	{
		try 
		{
			if(beans!=null)
			{
				getHibernateTemplate().saveOrUpdateAll(beans);
				getHibernateTemplate().flush();
			}
		}
		catch (DataAccessException e) 
		{			
			throw new DataBaseException("error.dataBase.insert,"+beans.get(0).getClass().getSimpleName()+","+e.getMessage());
		}
	}
	
	public void insertOrUpdateBean(Object newBean)throws DataBaseException
	{
		try 
		{
			if(newBean!=null)
			{
				getHibernateTemplate().saveOrUpdate(newBean);
				getHibernateTemplate().flush();
			}
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.insert,"+newBean.getClass().getSimpleName()+","+e.getMessage());			
		}
	}
	
	
	public void updateBean(Object newBean)throws DataBaseException
	{
		try 
		{
			if(newBean!=null)
			{
				getHibernateTemplate().update(newBean);
				getHibernateTemplate().flush();
			}
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.update,"+newBean.getClass().getSimpleName()+","+e.getMessage());
		}
	}
	
	
	public void deleteBean(Object bean)throws DataBaseException
	{
		try
		{
			if(bean!=null)
			{
				getHibernateTemplate().delete(bean);
				getHibernateTemplate().flush();
			}
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.delete,"+bean.getClass().getSimpleName()+","+e.getMessage());					
		}
	}

	
	public void deleteBeans(List beans)throws DataBaseException
	{
		try
		{
			if(beans!=null && beans.size()>0)
			{
				this.getHibernateTemplate().deleteAll(beans);
				this.getHibernateTemplate().flush();
			}
		}
		catch (DataAccessException e) 
		{
			throw new DataBaseException("error.dataBase.delete,"+beans.get(0).getClass().getSimpleName()+","+e.getMessage());	
		}
	}	
	
	public List <Object>findAllBeans(Class<?> beanClass, List<Object> nExpression, List<Object> nOrder , int fromRecord , int maxResult) throws DataBaseException,EmptyResultSetException
	{
		List <Object>result = new ArrayList<Object>();
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			if(nExpression!=null)
			{
				for(Iterator<Object> itr = nExpression.iterator() ; itr.hasNext();)
					criteria.add((Criterion)itr.next());
			}
			
			if(nOrder!=null)
			{
				for(Iterator<Object> itr = nOrder.iterator() ; itr.hasNext();)
					criteria.addOrder((Order)itr.next());
			}
			
			if (fromRecord != 0)
			{
				criteria.setFirstResult(fromRecord);
			}
			
			if (maxResult != 0)
			{
				criteria.setMaxResults(maxResult);
			}
		
			result = criteria.list();
			//	result = new ArrayList(new HashSet(result));
			
			if(result.size()==0)
			{
				String temp=beanClass.toString();
				int x=temp.lastIndexOf(".");
				
				String errorClass =temp.substring(x+1,temp.length()); 
				throw new EmptyResultSetException("error.emptyRS,"+errorClass);
			}
			
			return result;
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}
		finally
		{
			session.close();
		}
	}
	
	public List<Object> findAllBeansExcept(Class beanClass, List nId, String beanIdentifier,  Order order) throws DataBaseException,EmptyResultSetException
	{
		List result;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			try
			{
				if(PropertyUtils.getPropertyType(beanClass.newInstance(), beanIdentifier) == null)
				{
					System.out.println("no such property exception");
					return null;
				}
			}
			catch(Exception e)
			{
				System.out.println("no such property exception");
				return null;
			}
			
			if(nId!=null && nId.size() > 0)
				criteria.add(Restrictions.not(Restrictions.in(beanIdentifier,nId.toArray())));
			
			//reham
			if (order != null)
			  criteria.addOrder(order);
			
			result = criteria.list();
			
			if(result.size()==0 && !(nId!=null && nId.size() > 0))
			{
				String temp=beanClass.toString();
				int x=temp.lastIndexOf(".");
				
				String errorClass =temp.substring(x+1,temp.length());
				
				throw new EmptyResultSetException("error.emptyRS,"+errorClass);

			}
			
			return result;
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}
		finally
		{
			session.close();
		}
	}
	
	public List<Object> findAllBeans(Class beanClass, List nId, String beanIdentifier,  Order order) throws DataBaseException,EmptyResultSetException
	{
		List<Object> result;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);
			
			try
			{
				if(PropertyUtils.getPropertyType(beanClass.newInstance(), beanIdentifier) == null)
				{
					System.out.println("no such property exception");
					return null;
				}
			}
			catch(Exception e)
			{
				System.out.println("no such property exception");
				return null;
			}
			
			if(nId!=null && nId.size() > 0)
				criteria.add(Restrictions.in(beanIdentifier,nId.toArray()));
			if(order != null)
				criteria.addOrder(order);
			
			result = criteria.list();
			
			if(result.size()==0 && !(nId!=null && nId.size() > 0))
			{
				String temp=beanClass.toString();
				int x=temp.lastIndexOf(".");
				
				String errorClass =temp.substring(x+1,temp.length());
				
				throw new EmptyResultSetException("error.emptyRS,"+errorClass);

			}
			
			return result;
			
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}		
		finally
		{
			session.close();
		}
	}	
	
	
	public Object findBean(Class<?> beanClass, Map propertyMap) throws DataBaseException,InvalidReferenceException
	{
		List<Object> result;
		Session session = null;
		try 
		{
			session = this.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(beanClass);

			String key;
			
			for(Iterator itr = propertyMap.keySet().iterator() ; itr.hasNext() ;)
			{
				key = (String)itr.next();
				criteria.add(Restrictions.eq(key,propertyMap.get(key)));
			}
						
			result = criteria.list();
			
			if(result.size()==0)
			{
				String temp=beanClass.toString();
				int x=temp.lastIndexOf(".");
				
				String errorClass =temp.substring(x+1,temp.length());
				
				throw new InvalidReferenceException("error.emptyRef,"+errorClass);
			}
			
			return result.iterator().next();
		}
		catch (DataAccessException e) 
		{
			String temp=beanClass.toString();
			int x = temp.lastIndexOf(".");
			String errorClass =temp.substring(x+1,temp.length()); 
			throw new DataBaseException("error.dataBase.query,"+errorClass+","+e.getMessage());
		}		
		finally
		{
			session.close();
		}
	}
	
	public int findBeanCounts(String beanName)
	throws DataBaseException
	{
		Session session=null; 
		try
		{
			session= this.getSessionFactory().openSession();                                
			List<Object> nResult = session.createQuery("select count(*) from " + beanName).list();
			
			if(nResult.size() == 0)
				return 0;
			return Integer.parseInt(String.valueOf(nResult.get(0)));
		}
		catch (DataAccessException e)
		{
			throw new DataBaseException("error.dataBase.query,"+beanName+","+e.getMessage());
		}
		finally
        {
                    session.close();
        }

	}
	
	public void pkUpdate(String id,String oldId) throws DataBaseException
	{
		try
		{
			Session session = this.getSessionFactory().openSession();			

			String hqlUpdate = "update Port c set c.portId = :newPortId where c.portId = :oldPortId";
			session.createQuery( hqlUpdate )
			        .setString( "newPortId", id )
			        .setString( "oldPortId", oldId )
			        .executeUpdate();			
			session.close();			
			
		}
		catch(Exception e)
		{
			throw new DataBaseException("error.dataBase.query,VesselMove,"+e.getMessage());
		}
	}
	
	public void transAddEditDeleteObjects(List<Object> objects,List<Object> delObjects) throws DataBaseException
	{
		Session session =this.getSessionFactory().openSession();
		Transaction tx= null;
		try
		{		
			tx = session.beginTransaction();
			if(objects!=null)
			{
				for(int i=0;i<objects.size();i++)
				{
					if(objects.get(i)!=null)
					{
						session.saveOrUpdate(objects.get(i));
					}
				}
			}
			if(delObjects!=null)
			{
				for(int i=0;i<delObjects.size();i++)
				{
					if(delObjects.get(i)!=null)
					{
						session.delete(delObjects.get(i));
					}
				}
			}
			tx.commit();

		}
		catch (HibernateException e) 
		{	
			if (tx != null)
				tx.rollback();		
				throw new DataBaseException("error.dataBase.delete,"+e.getMessage());
		}	
		finally
		{
			session.close();
		}		
	}
	
	public List<Object> findAllBeansWithDepthMapping(Class<?> beanClass, Map<Object, Object> propertyMap) 
	throws DataBaseException,EmptyResultSetException
	{
		Session session=null; 
		try
		{
			String key, value;
			StringBuffer sb = new StringBuffer("");
			
			sb.append("from " + beanClass.getSimpleName() + " as bean");
			
			String andConditions = " where ";
			
			for(Iterator<Object> itr = propertyMap.keySet().iterator() ; itr.hasNext() ;)
			{
				sb.append(andConditions);
				key = (String)itr.next();
				value = String.valueOf(propertyMap.get(key));
				sb.append(" bean."+key);
				if(!value.contains("is null") && !value.contains("is not null")&&!value.contains(">")&&!value.contains("<")&&!value.contains("IN("))
					sb.append("=");
				sb.append(" " + value + " ");
				if(!value.contains(" or"))
				andConditions = " and ";
				else
				andConditions = "  ";
					
			}
			
			//System.out.println(sb.toString());
			session= this.getSessionFactory().openSession();                                
			List<Object> result = session.createQuery(sb.toString()).list();

			if(result.size()==0)
				throw new EmptyResultSetException("error.emptyRS," + beanClass.getSimpleName());
			
			return result;
		}
		catch(DataAccessException e)
		{
			throw new DataBaseException("error.dataBase.query," + beanClass.getSimpleName() + "," + e.getMessage());
		}
		finally
        {
                    session.close();
        }

	}
	
	public List<Object> findAllBeansWithDepthMapping(Class beanClass, Map propertyMap,List<Object> nOrder) 
	throws DataBaseException,EmptyResultSetException
	{
		Session session=null; 
		try
		{
			String key, value;
			StringBuffer sb = new StringBuffer("");
			
			sb.append("from " + beanClass.getSimpleName() + " as bean");
			
			boolean andFlag=false;
			
			for(Iterator<Object> itr = propertyMap.keySet().iterator() ; itr.hasNext() ;)
			{
				if(andFlag)
					sb.append(" and");
				else
					sb.append(" where");
				key = (String)itr.next();
				value = String.valueOf(propertyMap.get(key));
				sb.append(" bean."+key);
				if(!value.equals("is null") && !value.equals("is not null")&&!value.contains(">")&&!value.contains("<")&& !value.contains("<> "))
					sb.append("=");
				sb.append(" " + value + " ");
				
				andFlag=true;
			}
			
			if(nOrder!=null)
			{
				sb.append(" ORDER BY  ");
				Order order =null;
				Iterator itr = nOrder.iterator(); 
				while(itr.hasNext())
				{
					order = (Order)itr.next();
					sb.append(" bean."+order.toString());
					if(itr.hasNext())
						sb.append(" , ");
				}
			}
			System.out.println(sb.toString());
			session= this.getSessionFactory().openSession();                                
			List<Object> result = session.createQuery(sb.toString()).list();

			if(result.size()==0)
				throw new EmptyResultSetException("error.emptyRS," + beanClass.getSimpleName());
			
			return result;
		}
		catch(DataAccessException e)
		{
			throw new DataBaseException("error.dataBase.query," + beanClass.getSimpleName() + "," + e.getMessage());
		}
		finally
        {
                    session.close();
        }
	}

	public Long getMax(Class<?> beanClass, String columnName) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxReading(String readerIp) throws DataBaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMAXNewTagId(String readerIp, Integer antenna) throws DataBaseException, InvalidReferenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTruckFromTag(String tagId) throws DataBaseException {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
	
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




}

