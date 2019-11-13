/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.login.view;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import App.com.application.view.ApplicationView;
import App.core.action.BaseAction;
import App.core.beans.Users;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

/**
 *
 * @author ahmed
 */

public class LoginPersenter extends BaseAction  implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private JFXPasswordField passwordPass;

    @FXML
    void makeLogin(ActionEvent event) {
     Map<String,Object> map=new  HashMap <String,Object>();
     map.put("username", usernameTF.getText());
     map.put("password", passwordPass.getText());
 	System.out.println( passwordPass.getText());
 	System.out.println( usernameTF.getText());
try {
	List users= this.getBaseService().findAllBeans(Users.class, map,null);
	System.out.print("U R logged in ");
	
	
	
    ApplicationView appView=new ApplicationView();
    Scene scene=new Scene(appView.getView());
        
        this.getAppStage().setTitle("FXML App Demo");
        this.getAppStage().setScene(scene);
     //   this.getAppStage().initStyle(StageStyle.UNIFIED);

    
      //  this.getAppStage().setMaximized(true);
      //  this.getAppStage().show();

} catch (DataBaseException | EmptyResultSetException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    	
    	
    }

  


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	usernameTF.setPromptText(this.getMessage("login.username"));
    	passwordPass.setPromptText(this.getMessage("login.password"));
    	
    	

  }
}