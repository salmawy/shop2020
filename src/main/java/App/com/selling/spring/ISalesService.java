package App.com.selling.spring;

import java.util.Date;
import java.util.List;
import java.util.Map;

import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ISalesService {
	
	public List getAllCustomersOrdersTags(int seasonId,int fridageId,int productId,int storeId) ;
	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException ;
	public List<String> getSuggestedSellerName(String searchString) ;
	public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;
}
