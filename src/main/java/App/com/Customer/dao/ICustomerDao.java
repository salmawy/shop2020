package App.com.Customer.dao;

import java.util.Date;
import java.util.List;

import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface ICustomerDao {
	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException;
	public List getSeasonCustomers(int seasonId,int fridageId,int typeId) throws EmptyResultSetException, DataBaseException ;
	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException ;
	public List getCustomersSummaryTransactions(int seasonId,int fridageId,int customerId) throws EmptyResultSetException, DataBaseException ;
	public List getCustomerOrders(int customerId,int seasonId,int fridageId ,int finished) throws EmptyResultSetException, DataBaseException;
	public double  getPurchasedCustomerTotalDue(int seasonId,int customerId) throws EmptyResultSetException, DataBaseException ;
	public double getPurchasedCustomerTotalInst(int seasonId,int customerId) throws EmptyResultSetException, DataBaseException ;
	 public List<String> getSuggestedCustomerName(String searchString,int customerTypeId) ;
	 public List getOutcome(Date date) throws EmptyResultSetException, DataBaseException ;
}
