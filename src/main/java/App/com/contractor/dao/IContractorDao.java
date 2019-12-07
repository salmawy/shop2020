package App.com.contractor.dao;

import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IContractorDao {

	List getContractorAccount(int contractorId, int seasonId, int typeId)
			throws DataBaseException, EmptyResultSetException;

	List<String> getSuggestedContractorName(String searchString, int ownerId, int typeId);

}
