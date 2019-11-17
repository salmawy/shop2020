
package App;
import java.net.URL;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sun.javafx.css.StyleManager;

import App.com.login.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
public static BeanFactory springBeanFactory;

public static Stage AppStage;


	public static void main(String[] args) {
		
		 Resource r=new ClassPathResource("appResources/Spring-All-Module.xml"); 
		 
		 springBeanFactory=new XmlBeanFactory(r);  

		 springBeanFactory.getBean("mysessionFactory");  
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
