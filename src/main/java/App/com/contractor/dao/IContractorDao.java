package App.com.contractor.dao;

import java.util.Date;
import java.util.List;

import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public interface IContractorDao {

	List getContractorAccount(int contractorId, int seasonId, int typeId)
			throws DataBaseException, EmptyResultSetException;

	List<String> getSuggestedContractorName(String searchString, int ownerId, int typeId);

	List getContractorAccount(String name, int seasonId, int typeId, Date fromDate, Date toDate, int paid,int ownerId)
			throws DataBaseException, EmptyResultSetException;
}
