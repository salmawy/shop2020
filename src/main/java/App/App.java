
package App;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import App.com.login.view.LoginView;
import App.core.action.BaseAction;
import App.core.beans.Users;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css"); //(3)

        AppStage.setScene(scene);
        AppStage.sizeToScene();
        AppStage.show();

}





}
