package App.core.applicationContext;

import java.util.ResourceBundle;

import org.springframework.orm.hibernate3.HibernateTransactionManager;

import App.core.beans.Fridage;
import App.core.beans.Season;
import App.core.beans.Users;

public class ApplicationContext {
	
	
	
	public final ResourceBundle resourceBundel = ResourceBundle.getBundle("appResources.myBundel_ar");
	public static Season season;
	public static Fridage fridage;
	public static  Users currentUser=null;	
	public static HibernateTransactionManager transactionManager;
	public static float Cusotmer_Order_Ration=(float) 0.1; ;
	public static int cashSellerId=200 ;

	
	
	
	
	
	

}
