package App.com.selling.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ISalesDao {
	
	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException ;
	 public List<String> getSuggestedSellerName(String searchString) ;
		public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;
}
