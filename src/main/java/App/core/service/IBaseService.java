
package App.core.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.json.JSONArray;


import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.exception.PrimaryKeyViolatedException;
import App.core.exception.UniquePropertyViolatedException;




public interface IBaseService 
{
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
	public abstract List<Object> findAllBeans(Class<?> beanClass) throws DataBaseException, EmptyResultSetException;

	/**
	 * 
	 * @param beanClass
	 * @param params
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeans(Class<?> beanClass,Map params,Order order) throws DataBaseException, EmptyResultSetException;
	
	/**
	 * 
	 * @param beanClass
	 * @param order
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeans(Class<?> beanClass,Order order) throws DataBaseException, EmptyResultSetException;
	
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
	public List<Object> findAllBeans(Class<?> beanClass,Order order, int fromRecord , int maxResult) throws DataBaseException, EmptyResultSetException;
	
	
//	/**
//	 * 
//	 * @param entityName  the entity name of the mapped bean specified in the property named "entity-name" <br>e.g  &lt; class name="Apple" table="APPLE" entity-name="MyApple">
//	 * @return
//	 * @throws DataBaseException
//	 * @throws EmptyResultSetException
//	 */
//	public abstract List<Object> findAllBeans(String entityName) throws DataBaseException, EmptyResultSetException;
//
//	/**
//	 * 
//	 * @param entityName  the entity name of the mapped bean specified in the property named "entity-name" <br>e.g  &lt; class name="Apple" table="APPLE" entity-name="MyApple">
//	 * @param params
//	 * @param order
//	 * @return
//	 * @throws DataBaseException
//	 * @throws EmptyResultSetException
//	 */
//	public List<Object> findAllBeans(String entityName,Map params,Order order) throws DataBaseException, EmptyResultSetException;
//	
//	/**
//	 * 
//	 * @param entityName  the entity name of the mapped bean specified in the property named "entity-name" <br>e.g  &lt; class name="Apple" table="APPLE" entity-name="MyApple">
//	 * @param order
//	 * @return
//	 * @throws DataBaseException
//	 * @throws EmptyResultSetException
//	 */
//	public List<Object> findAllBeans(String entityName,Order order) throws DataBaseException, EmptyResultSetException;
//	
//	/**
//	 * @param entityName the entity name of the mapped bean specified in the property named "entity-name" <br>e.g  &lt; class name="Apple" table="APPLE" entity-name="MyApple">
//	 * @param order
//	 * @param fromRecord
//	 * @param maxResult
//	 * @return
//	 * @throws DataBaseException
//	 * @throws EmptyResultSetException
//	 */
//	public List<Object> findAllBeans(String entityName,Order order, int fromRecord , int maxResult) throws DataBaseException, EmptyResultSetException;
//	
	/**
	 * 
	 * @param beanClass
	 * @param identifier
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public abstract Object getBean(Class<?> beanClass, Object identifier) throws DataBaseException, InvalidReferenceException;

	/**
	 * 
	 * @param invoiceMissmatch
	 * @param subAuthorityId 
	 * @param financialYearId
	 * @return String
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 * @throws EmptyResultSetException 
	 */

	/**
	 * 
	 * @param beanClass
	 * @param identifier
	 * @return
	 * @throws DataBaseException
	 * @throws InvalidReferenceException
	 */
	public Object getBean(Class<?> beanClass, Map identifier) throws DataBaseException, InvalidReferenceException;

	/**
	 * 
	 * @param beanClass
	 * @param instance
	 * @throws DataBaseException
	 * @throws PrimaryKeyViolatedException
	 */
	public abstract void validateIdentifier(Class<?> beanClass, Object instance) throws DataBaseException,PrimaryKeyViolatedException;

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
	 * @param bean
	 * @throws DataBaseException
	 */
	public void deleteBean(Object bean)throws DataBaseException;
	
	/**
	 * 
	 * @param beans
	 * @throws DataBaseException
	 */
	public void deleteBeans(List beans)throws DataBaseException;
	
	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public void addEditBean(Object newBean) throws DataBaseException;
	
	
	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public void addBean(Object newBean) throws DataBaseException;
	
	
	/**
	 * 
	 * @param newBean
	 * @throws DataBaseException
	 */
	public void editBean(Object newBean) throws DataBaseException;
	

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
	public List<Object> findAllBeansExcept(Class<?> beanClass, List nId, Order order) throws DataBaseException,EmptyResultSetException;
	
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
	public List<Object> findAllBeansExcept(Class<?> beanClass, List nId, String beanIdentifier ,Order order) throws DataBaseException,EmptyResultSetException;
	/**
	 * 
	 * @param nBean
	 * @throws DataBaseException
	 */
	public void addEditBeans(List nBean) throws DataBaseException;
	
	
	/**
	 * 
	 * @param sqlQuery
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException 
	 */
	public  Map<ResultSet,Session> executeSQLStatement(String sqlQuery) throws DataBaseException, EmptyResultSetException;
	
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
	public List<Object> findAllBeans(Class beanClass, List nId, String beanIdentifier,  Order order) throws DataBaseException,EmptyResultSetException;
	
	
	/**
	 * 
	 * @param beanName
	 * @return size of data
	 * @throws DataBaseException
	 */
	public int findBeanCounts(String beanName)throws DataBaseException;
	
	/**
	 * @param objects
	 * @param delObjects
	 * @throws DataBaseException
	 */
	public void transAddEditDeleteObjects(List objects,List delObjects) throws DataBaseException;
	
	/**
	 * 
	 * @param beanClass
	 * @param propertyMap
	 * @return List
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List<Object> findAllBeansWithDepthMapping(Class beanClass, Map propertyMap) throws DataBaseException,EmptyResultSetException;
		
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
	
	/**
	 * 
	 * @param beanClass
	 * @param params
	 * @param order
	 * @param fromRecord
	 * @param maxResult
	 * @return
	 * @throws DataBaseException
	 * @throws EmptyResultSetException
	 */
	public List findAllBeansWithParams(Class<?> beanClass, Map params,Order order, int fromRecord, int maxResult)
	throws DataBaseException, EmptyResultSetException;


	
}