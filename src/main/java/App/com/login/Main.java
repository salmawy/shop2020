/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.login;

import App.com.login.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ahmed
 */
@SuppressWarnings("restriction")
public class Main extends Application {
        private Stage stage;
    @Override
    public void start(Stage primaryStage) {
        
           stage = primaryStage;
            stage.setTitle("FXML Login Sample");
           primaryStage.setResizable(false);
           primaryStage.initStyle(StageStyle.TRANSPARENT);

           primaryStage.setMinWidth(300);
           	System.out.println(primaryStage.getMaxHeight()); 
  
            LoginView login = new LoginView();
            
            Scene scene = new Scene(login.getView(), 400, 250);
            stage.setScene(scene);
            stage.sizeToScene();
  
            
                    primaryStage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
}
