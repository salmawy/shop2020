package App.com.billing.services;

import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IBillingService {

	List getSuggestedOrders(int customerId,int finished, int dued, int seasonId, int typeId ,int fridageId)
			throws DataBaseException, EmptyResultSetException;

	List getSuggestedCustomersOrders(int finished, int dued, int seasonId, int fridageId, int typeId) throws EmptyResultSetException, DataBaseException;

	List getCustomersOrderWeights(int orderId) throws EmptyResultSetException, DataBaseException;

 
	

}
