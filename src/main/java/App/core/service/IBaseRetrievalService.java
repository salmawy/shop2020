package App.core.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.json.JSONArray;

import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.exception.PrimaryKeyViolatedException;
import App.core.exception.UniquePropertyViolatedException;


/**
 * 
 * @author yehia
 *
 */

public interface IBaseRetrievalService 
{
	
	
	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException ;

	
	/**
	 * 
	 * @param nBean
	 * @param propertyMap
	 * @return
	 * @throws Exception
	 */
	public JSONArray toJSONArray(List<Object> nBean, Map<String, String> propertyMap)throws Exception;

	/**
	 * 
	 * @param beanClass
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public abstract List<Object> findAllBeans(Class<?>beanClass) throws DataBaseException, EmptyResultSetException;

	/**
	 * 
	 * @param beanClass
	 * @param params
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List <Object>findAllBeans(Class<?>beanClass,Map params,Order order) throws DataBaseException, EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeans(Class<?>beanClass,Order order) throws DataBaseException, EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param order
	 * @param fromRecord
	 * @param maxResult
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeans(Class<?>beanClass,Order order, int fromRecord , int maxResult) throws DataBaseException, EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param identifier
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public abstract Object getBean(Class<?>beanClass, Object identifier) throws DataBaseException, InvalidReferenceException;

	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws PrimaryKeyViolatedException
	 */
	public abstract void validateIdentifier(Class<?>beanClass, Object instance) throws DataBaseException,PrimaryKeyViolatedException;

	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws UniquePropertyViolatedException
	 */
	public void validateUniqueness(Class<?>beanClass, Map propertyMap) throws DataBaseException,UniquePropertyViolatedException;
	

	/**
	 * 
	 * @param beanClass
	 * @param propertyMap
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public Object findBean(Class<?>beanClass, Map propertyMap) throws DataBaseException,InvalidReferenceException;
	
	/**
	 * the given bean must have an id property
	 * @param beanClass
	 * @param nId
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansExcept(Class<?>beanClass, List nId, Order order) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param nId
	 * @param beanIdentifier
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansExcept(Class<?>beanClass, List nId, String beanIdentifier ,Order order) throws DataBaseException,EmptyResultSetException;
	
	
	/**
	 * 
	 * @param beanClass
	 * @param nId
	 * @param beanIdentifier
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeans(Class<?>beanClass, List nId, String beanIdentifier,  Order order) throws DataBaseException,EmptyResultSetException;


	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException ;









}