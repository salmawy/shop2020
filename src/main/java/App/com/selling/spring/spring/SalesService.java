package App.com.selling.spring.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import App.com.selling.dao.ISalesDao;
import App.com.selling.spring.ISalesService;
import App.core.beans.CustomerOrder;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.service.IBaseRetrievalService;
import App.core.service.IBaseService;

public class SalesService implements ISalesService {
	
	ISalesDao salesDao;
	IBaseRetrievalService baseRetrievalService;
	IBaseService baseService;

	
	
	
	
	
	
	
	
	
	public ISalesDao getSalesDao() {
		return salesDao;
	}
	public void setSalesDao(ISalesDao salesDao) {
		this.salesDao = salesDao;
	}
	public IBaseRetrievalService getBaseRetrievalService() {
		return baseRetrievalService;
	}
	public void setBaseRetrievalService(IBaseRetrievalService baseRetrievalService) {
		this.baseRetrievalService = baseRetrievalService;
	}
	public IBaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	/*
	 * getAllCustomersOrdersTags
	 * */
@SuppressWarnings("unchecked")
public List getAllCustomersOrdersTags(int seasonId,int fridageId,int productId,int storeId) {
	
	
	List result=new ArrayList();
	Map map=new HashMap();
	map.put("productId", productId);
	map.put("fridageId", fridageId);
	map.put("storeId", storeId);
	map.put("finished",0 );
	
	
	try {
		result=this.getBaseService().findAllBeans(CustomerOrder.class,map,null);
	} catch (DataBaseException | EmptyResultSetException e) {
		e.printStackTrace();
	}
return result;
	
	
	
	
	
	
	
}

public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException {
	
return	this.getSalesDao().aggregate(tablename, operation, columnName, parameters);
	
	
}





public List<String> getSuggestedSellerName(String searchString) {

return this.getSalesDao().getSuggestedSellerName(searchString);
}

public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException {

return this.getSalesDao(). getSellersOrders(orderDate);
}

}
