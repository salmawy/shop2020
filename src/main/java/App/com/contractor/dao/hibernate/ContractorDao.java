package App.com.contractor.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.contractor.dao.IContractorDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class ContractorDao extends HibernateDaoSupport implements IContractorDao{

	
	@Override
	public List getContractorAccount(int contractorId,int seasonId,int typeId) throws DataBaseException, EmptyResultSetException {
		
		

		
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
		  
	


		  String q ="select "
		  		+ " cad.contractorAccount.contractor.id,cad.contractorAccount.contractor.name ,"
		  		+ " sum (cad.amount) "
		  		
		  		+ " from "
		  		+ " ContractorAccountDetail cad  "
		  		+ " where  cad.seasonId= "+seasonId ;
		  if(contractorId!=0)
			  q +="	and cad.contractorAccount.contractorId ="+contractorId;
		  if(typeId!=0)
			  q +="	and cad.contractorAccount.contractor.typeId ="+typeId;
		
		  
		  q +="	group by cad.contractorAccount.contractor.name ,cad.contractorAccount.contractor.id";

		  Query queryList = session.createQuery(q);

		  List result =queryList.list();

		
		  if(result.size() == 0) {
			  throw new  EmptyResultSetException("error.emptyRS"); }
		  
		  if(result.size() > 0) 
		  {return result;}
		 } 
		  catch(DataAccessException e) { throw new
		  DataBaseException("error.dataBase.query,AgentFinancialStatus,"+e.getMessage()  );
		  }
		  finally { session.close(); }
		  
		 
		return null;
		
	}
	
 
	
	
@Override
	 public List<String> getSuggestedContractorName(String searchString,int ownerId,int typeId) {

	        logger.info("Getting contractor list for autocomplete");

	        List<String> list = null;

			 Session session = null; 

			  session =this.getSessionFactory().openSession();

	        try {


	            Query query = session.createQuery("select c.name from Contractor c where c.name LIKE :search "
	            		+ "	and c.typeId=:typeId"
	            		+ " and c.ownerId=:ownerId"
	            		+ "");
	            query.setParameter("typeId", typeId);
	            query.setParameter("ownerId", ownerId);
	            list = query.setParameter("search", "%"+searchString+"%").setMaxResults(10).list();


	        } catch (Exception ex) {

	            logger.error("Other exception {}", ex);

	        } finally {

	        	session.close();

	        }       

	        return list;

	    }




@Override
public List getContractorAccount(String name, int seasonId, int typeId, Date fromDate, Date toDate, int paid,int ownerId) throws EmptyResultSetException, DataBaseException {

	
	

	
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
	  



	  String q ="select "
	  		+ " cad "
 	  		
	  		+ " from "
	  		+ " ContractorAccountDetail cad  "
	  		+ " where  cad.seasonId= "+seasonId ;
	  if(paid!=-1)
		  q +="	and cad.paid="+paid;
	  if( typeId!=0 )
		  q +="	and cad.contractorAccount.contractor.typeId ="+typeId;
    if( ownerId!=0 )
			  q +="	and cad.contractorAccount.contractor.ownerId ="+ownerId;
		  
	  if(toDate!=null&&fromDate!=null) {
		  q +="	and cad.detailDate >= :fromDate";
		  q +="	and cad.detailDate <= :toDate";
 
	  }
	  if(name!=null&&name!="")
		  q +="	and cad.contractorAccount.contractor.name like  '%"+typeId+"%'";
	  
 
	  Query queryList = session.createQuery(q);
	  if(toDate!=null&&fromDate!=null) {
		  queryList.setParameter("fromDate", fromDate);
		  queryList.setParameter("toDate", toDate);

		  
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  List result =queryList.list();

	
	  if(result.size() == 0) {
		  throw new  EmptyResultSetException("error.emptyRS"); }
	  
	  if(result.size() > 0) 
	  {return result;}
	 } 
	  catch(DataAccessException e) { throw new
	  DataBaseException("error.dataBase.query,ContractorAccountDetail,"+e.getMessage()  );
	  }
	  finally { session.close(); }
	  
	 
	return null;
	

} 
	 
	 
	
	
	
	
	
	
	
	
	
	
	
	
}
