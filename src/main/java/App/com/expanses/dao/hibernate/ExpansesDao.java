package App.com.expanses.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import App.com.expanses.dao.IExpansesDao;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;

public class ExpansesDao extends HibernateDaoSupport implements  IExpansesDao{
   
	public List getIncome(Date date) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Income "
		  		+ "where  to_char(  incomeDate ,'dd/MM/YYYY')  = "
		  		+ " to_char( ? ,'dd/MM/YYYY') ";							

		  query += " order by incomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setDate(0, date);
		//  queryList.setDate(1, upper.getTime());

		  List<Object> result =	 queryList.list();
		  
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

	
	 
	 public List getIncomeDays(String month) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Income "
		  		+ "where  to_char(  incomeDate ,'YYYY-MM')  = :month";

		  query += " order by incomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("month", month);

		  List<Object> result =	 queryList.list();
		  
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

	

	 
	 
	 public List getIncomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
			String sql=" select distinct to_char(income0_.INCOME_DATE, 'YYYY-MM') as mon from INCOME income0_ where income0_.SEASON_ID="+seasonId+" order by mon desc";
				
		  Query queryList = session.createSQLQuery(sql);

		  
		  List<Object> result =	 queryList.list();
		  
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

	
	 
	 public List getOutcomeMonthes(int seasonId) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
				String sql=" select distinct to_char(OUTCOME_DATE, 'YYYY-MM') as mon from OUTCOME  where SEASON_ID="+seasonId+" order by mon desc";

			
		  
		  Query queryList = session.createSQLQuery(sql);

		  
		  List<Object> result =	 queryList.list();
		  
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

	 
	 public List getOutcomeDays(String month) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Outcome "
		  		+ "where  to_char(  outcomeDate ,'YYYY-MM')  = :month";

		  query += " order by outcomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setParameter("month", month);

		  List<Object> result =	 queryList.list();
		  
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

	

	
	
	 
	 
	 public List getOutcome(Date date) throws EmptyResultSetException, DataBaseException {
		 

			
		  Session session = null; 
		  try { 
			  session =this.getSessionFactory().openSession();
			

		  
		  String query = "from Outcome "
		  		+ "where  to_char(  outcomeDate ,'dd/MM/YYYY')  = "
		  		+ " to_char( ? ,'dd/MM/YYYY') ";							

		  query += " order by outcomeDate  desc";
			
		  
		  Query queryList = session.createQuery(query);
		  queryList.setDate(0, date);
		//  queryList.setDate(1, upper.getTime());

		  List<Object> result =	 queryList.list();
		  
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

	
	
	
	

public List getLoanerDebts(int loanerId, String type) throws EmptyResultSetException, DataBaseException {
	 

	
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
		

	  
	  String query = "from IncLoan il "
	  		+ "where il.loanAccount.loaner.id= :loanerId  "
	  		+ " and  il.loanAccount.type= :type "
	  		+ " and  il.loanAccount.finished= 0 ";							


	  query += " order by loanDate  desc";
		
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("loanerId", loanerId);
	  queryList.setParameter("type", type);

	  List<Object> result =	 queryList.list();
	  
	  if(result.size() == 0) {
		  throw new  EmptyResultSetException("error.emptyRS"); }
	  
	  if(result.size() > 0) 
	  {return result;}
	 } 
	  catch(DataAccessException e) { throw new
	  DataBaseException("error.dataBase.query,LoanerDebts,"+e.getMessage()  );
	  }
	  finally { session.close(); }
	  
	 
	return null;
	 
	 
	 
}



public List getLoanerInstalments(int loanerId, String type) throws EmptyResultSetException, DataBaseException {
	 

	
	  Session session = null; 
	  try { 
		  session =this.getSessionFactory().openSession();
		

	  
	  String query = "from LoanPaying lp "
	  		+ "where lp.loanAccount.loaner.id= :loanerId  "
	  		+ " and  lp.loanAccount.type= :type "
	  		+ " and  lp.loanAccount.finished= 0 ";							


	  query += " order by payingDate  desc";
		
	  
	  Query queryList = session.createQuery(query);
	  queryList.setParameter("loanerId", loanerId);
	  queryList.setParameter("type", type);

	  List<Object> result =	 queryList.list();
	  
	  if(result.size() == 0) {
		  throw new  EmptyResultSetException("error.emptyRS"); }
	  
	  if(result.size() > 0) 
	  {return result;}
	 } 
	  catch(DataAccessException e) { throw new
	  DataBaseException("error.dataBase.query,LoanerDebts,"+e.getMessage()  );
	  }
	  finally { session.close(); }
	  
	 
	return null;
	 
	 
	 
}



/*


    public Vector<Object> getLoanerInst(String name, String type) {
        CallableStatement cs = null;
        Vector<Object> data = new Vector<Object>();
        DateFormat df = new SimpleDateFormat("EEEEE dd-MMMMMM-yyyy hh:mm aaaa", new Locale("ar", "AE", "Arabic"));// DateFormat.getDateTimeInstance(DateFormat.DEFAULT,DateFormat.FULL, new Locale("ar","AE","Arabic"));

        try {
            cs = con.prepareCall("SELECT\n"
                    + "     LOANERS.LOANER_NAME AS LOANER_NAME,\n"
                    + "     LOAN_ACCOUNT.LOAN_TYPE AS LOAN_TYPE,\n"
                    + "     LOAN_PAYING.PAID_AMOUNT AS LOAN_PAYING_AMOUNT,\n"
                    + "    to_char( LOAN_PAYING.PAYING_DATE,'DD-MM-YYYY HH24:MI' )AS LOAN_PAYING_DATE,\n"
                    + "     LOAN_PAYING.NOTES AS LOAN_PAYING_NOTES\n"
                    + "FROM\n"
                    + "     LOANERS INNER JOIN LOAN_ACCOUNT ON LOANERS.LOANER_ID = LOAN_ACCOUNT.LOANER_ID\n"
                    + "     INNER JOIN LOAN_PAYING ON LOAN_ACCOUNT.LOAN_ACCOUNT_ID = LOAN_PAYING.LOAN_ACCOUNT_ID\n"
                    + "WHERE\n"
                    + "     LOAN_ACCOUNT.FINISHED = 0\n"
                    + "     and LOAN_ACCOUNT.LOAN_TYPE LIKE ? and LOANER_NAME like ?  ");

            cs.setString(1, type);
            cs.setString(2, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                Vector<Object> temp = new Vector<Object>();
                java.util.Date date = StringToDate(rs.getString("LOAN_PAYING_DATE"), "dd-MM-yyyy HH:mm");
                String sdate = df.format(date);
                temp.add(rs.getString("LOAN_PAYING_NOTES"));
                temp.add((rs.getString("LOAN_PAYING_AMOUNT")));
                temp.add((sdate));

                data.add(temp);

            }
            rs.close();
            return data;

        } catch (SQLException ex) {
            Logger.getLogger(DataSourc.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataSourc.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

  
 * */

	
	
	
}
