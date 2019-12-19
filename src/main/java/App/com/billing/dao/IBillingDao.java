package App.com.billing.dao;

import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IBillingDao {

	List getSuggestedCustomersOrders(int finished, int dued, int seasonId, int fridageId, int typeId) throws EmptyResultSetException, DataBaseException;

	List getCustomersOrderWeights(int orderId) throws EmptyResultSetException, DataBaseException;

}
