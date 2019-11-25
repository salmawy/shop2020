package App.core.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import App.core.beans.Season;
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
public interface IBaseDao 
{
	/**
	 * 
	 * @param beanClass
	 * @param identifier
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public Object findBean(Class<?> beanClass, Object identifier) throws DataBaseException,InvalidReferenceException;
	
	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public abstract void insertBean(Object newBean)throws DataBaseException;

	
	/**
	 * 
	 * @param beans
	 * @throws DataBaseException
	 */
	public abstract void insertBeans(List<Object> beans)throws DataBaseException;

	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public abstract void insertOrUpdateBean(Object newBean)throws DataBaseException;

	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public abstract void updateBean(Object newBean)throws DataBaseException;

	/**
	 * 
	 * @param bean
	 * @throws DataBaseException
	 */
	public abstract void deleteBean(Object bean)throws DataBaseException;

	/**
	 * 
	 * @param beans
	 * @throws DataBaseException
	 */
	public abstract void deleteBeans(List<Object> beans)throws DataBaseException;

	/**
	 * 
	 * @param beanClass
	 * @param nExpression
	 * @param nOrder
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List <Object>findAllBeans(Class<?> beanClass, List<Object> nExpression, List<Object> nOrder , int fromRecord , int maxResult) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * 
	 * @param entityName
	 * @param nExpression
	 * @param nOrder
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	//public List <Object>findAllBeans(String entityName, List<Object> nExpression, List<Object> nOrder , int fromRecord , int maxResult) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws UniquePropertyViolatedException
	 */
	public void validateUniqueness(Class<?> beanClass, Map propertyMap) throws DataBaseException,UniquePropertyViolatedException;
	
	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws UniquePropertyViolatedException
	 */
	public void validateUniqueness(Class<?> beanClass, Object instance) throws DataBaseException,UniquePropertyViolatedException;
	
	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws PrimaryKeyViolatedException
	 */
	public void validateIdentifier(Class<?> beanClass, Object instance) throws DataBaseException,PrimaryKeyViolatedException;
	

	/**
	 * 
	 * @param beanClass
	 * @param propertyMap
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public Object findBean(Class<?> beanClass, Map propertyMap) throws DataBaseException,InvalidReferenceException;
	
	/**
	 * the given bean must have an id property
	 * @param beanClass
	 * @param nId
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansExcept(Class<?> beanClass, List<Object> nId,String beanIdentifier, Order order) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws DataBaseException
	 */
	public  Map<ResultSet,Session> executeSQLStatement(String sqlQuery) throws DataBaseException ,EmptyResultSetException;
	
	/**
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws DataBaseException
	 */
	public List<Object> executeMappedSQLStatement(String sqlQuery) throws DataBaseException;
	
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
	public List<Object> findAllBeans(Class<?> beanClass, List<Object> nId, String beanIdentifier,  Order order) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * 
	 * @param beanName
	 * @return size of data
	 * @throws DataBaseException
	 */
	public int findBeanCounts(String beanName)throws DataBaseException;	
	
	/**
	 * 
	 * @param id
	 * @param oldId
	 * @return void
	 * @throws DataBaseException
	 */
	public void pkUpdate(String id,String oldId) throws DataBaseException;
	
	/**
	 * @param objects
	 * @param delObjects
	 * @throws DataBaseException
	 */
	public void transAddEditDeleteObjects(List<Object> objects,List<Object> delObjects) throws DataBaseException;
	
	/**
	 * 
	 * @param beanClass
	 * @param propertyMap
	 * @return List
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansWithDepthMapping(Class<?> beanClass, Map<Object, Object> propertyMap) throws DataBaseException,EmptyResultSetException;
	
	
	/**
	 * 
	 * @param beanClass
	 * @param propertyMap
	 * @return List
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansWithDepthMapping(Class beanClass, Map propertyMap,List<Object> nOrder) throws DataBaseException,EmptyResultSetException;
	
	/**
	 * @author fangelo
	 * @since 08-01-2008
	 * @param beanClass
	 * @param columnName
	 * @return Long
	 * @throws DataBaseException
	 */
	public Long getMax(Class<?> beanClass, String columnName) throws DataBaseException;

	public int getMaxReading(String readerIp)
			throws DataBaseException;

	public int getMAXNewTagId(String readerIp, Integer antenna) throws DataBaseException,
	InvalidReferenceException;

	
	public int getTruckFromTag(String tagId) throws DataBaseException;

	
	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException ;
	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException ;
}