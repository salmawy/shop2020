package App.com.sales.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ISalesDao {
	
	public Object  aggregate(String tablename,String operation,String columnName,Map <String,Object>parameters) throws DataBaseException, EmptyResultSetException ;
	 public List<String> getSuggestedSellerName(String searchString) ;
		public List getSellersOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;
		 public List getSellersDebts( int seasonId,int active) throws EmptyResultSetException, DataBaseException;
		 public List getIncome(Date date) throws EmptyResultSetException, DataBaseException ;
		List getSellersLoanSummary(Date fromDate, Date toDate, int seasonId)
				throws EmptyResultSetException, DataBaseException;
}
