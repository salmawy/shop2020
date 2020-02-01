package App.com.Customer.service;

import java.util.Date;
import java.util.List;

import App.core.beans.CustomerOrder;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;

public interface ICustomerService {
	
	public List getCustomerInvoices(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException ;
	public List getCustomerOrders(int seasonId,int customerId,int fridageId) throws EmptyResultSetException, DataBaseException ;

	public List getCustomerOrders(Date orderDate) throws EmptyResultSetException, DataBaseException ;
	public List getSeasonCustomers(int seasonId,int fridageId,int typeId) throws EmptyResultSetException, DataBaseException ;
	public Season getCurrentSeason() throws DataBaseException, EmptyResultSetException ;
	public List getCustomersSummaryTransactions(int seasonId,int fridageId,int customerId) throws EmptyResultSetException, DataBaseException ;
	public List  getPurchasedCustomerData(int seasonId,int fridageId) throws EmptyResultSetException, DataBaseException ;
	 public List<String> getSuggestedCustomerName(String searchString,int customerTypeId) ;
	 public Double getSafeBalance(int seasonId) ;
	 public void saveCustomerOrder(CustomerOrder customerOrder) throws DataBaseException ;
	 public void editCustomerOrder(CustomerOrder newValue,CustomerOrder oldValue) throws DataBaseException, InvalidReferenceException ;
	void payPurchasedOrder(int customerId, double amount, Date date, String notes, int seasonId, int fridageId)
			throws DataBaseException;
	void editPurchasedOrder(int intallementId,int customerId, double amount, Date date, String notes, int seasonId, int fridageId)
			throws DataBaseException, InvalidReferenceException;

}
