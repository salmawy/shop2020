package App.com.inventory.spring;

import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IInventoryService {

	List getInventoryDates(int seasonId) throws EmptyResultSetException, DataBaseException;

 	public double getPurchasesProfit(String month, int seasonId, int fridageId);

	public double getCommisionProfit(String month, int seasonId, int fridageId);

	public double getKTotalOrders(String month, int seasonId, int fridageId);

	public double getkaremmTotalWithdrawal(String month, int seasonId, int fridageId,int contractorType);

	public double getTotalOutcome(String month, int seasonId, int fridageId);

	public double getSalamiProductsProfit(String month, int seasonId, int fridageId, int productId); 
	

	
}
