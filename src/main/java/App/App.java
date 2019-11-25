
package App;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

import App.com.login.view.LoginView;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Fridage;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.service.IBaseRetrievalService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
public static BeanFactory springBeanFactory;

public static Stage AppStage;
static Logger logger = Logger.getLogger(App.class.getName());
	public static void main(String[] args) {
		
		 Resource r=new ClassPathResource("appResources/Spring-All-Module.xml"); 
		 springBeanFactory=new XmlBeanFactory(r);  
		 logger.log(Level.FINE, "Spring is up");
		 ApplicationContext.transactionManager=new HibernateTransactionManager(((SessionFactory) springBeanFactory.getBean("mysessionFactory"))) ;
		 ApplicationContext.transactionManager.setNestedTransactionAllowed(true);
		 IBaseRetrievalService  baseRetrievalService=	(IBaseRetrievalService) App.springBeanFactory.getBean("baseService");
		 
		 try {
				 ApplicationContext.season= baseRetrievalService.getCurrentSeason();
				ApplicationContext.fridage= (Fridage) baseRetrievalService.getBean(Fridage.class, 1);
			
		     } catch (DataBaseException | EmptyResultSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidReferenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 System.out.println("..................connected successfully.............");
			System.out.println(".................. baseService is UP.............");
	
			launch(args);
	}
	
	

@Override
public void start(Stage primaryStage) {
    
	AppStage = primaryStage;



	AppStage.setTitle("FXML Login Sample");
	AppStage.setResizable(false);
//	AppStage.initStyle(StageStyle.TRANSPARENT);
	AppStage.setMinWidth(300);
    
	
	System.out.println(primaryStage.getMaxHeight()); 

        LoginView login = new LoginView();
        
        Scene scene = new Scene(login.getView(), 400, 250);

		 
	
		
	

     //   scene.getStylesheets().add(css); //(3)
        
        AppStage.setScene(scene);
        AppStage.sizeToScene();
        AppStage.show();

}











}
