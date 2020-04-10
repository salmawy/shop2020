package App.com.billing.services;

import java.util.List;

import App.core.beans.CustomerOrder;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IBillingService {

	List getSuggestedOrders(int customerId,int status, int seasonId, int typeId ,int fridageId)
			throws DataBaseException, EmptyResultSetException;

	List getSuggestedCustomersOrders(int status, int seasonId, int fridageId, int typeId) throws EmptyResultSetException, DataBaseException;

	List getCustomersOrderWeights(int orderId) throws EmptyResultSetException, DataBaseException;
	List getSuggestedCustomersOrders(int seasonId, int fridageId) throws EmptyResultSetException, DataBaseException;

	void generateInvoice(CustomerOrder invoice) throws DataBaseException;

 
	public void payInvoice(CustomerOrder invoice) throws DataBaseException ;

}
