package App.com.contractor.services;

import java.util.Date;
import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IContractorService {

	List getContractorAccount(int contractorId, int seasonId, int typeId)
			throws DataBaseException, EmptyResultSetException;

	void contractorTransaction(String name, int typeId, double amount, int fridageId, String notes, int paid,
			int ownerId, Date date, int seasonId) throws DataBaseException;

	List<String> getSuggestedContractorName(String searchString, int ownerId, int typeId);

}
