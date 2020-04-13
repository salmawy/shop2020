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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import App.com.application.view.ApplicationView;
import App.core.Enum.SellerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.action.BaseAction;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Fridage;
import App.core.beans.Users;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ahmed
 */

public class LoginPersenter extends BaseAction  implements Initializable {
	Logger logger = Logger.getLogger(this.getClass().getName());	

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
    private JFXButton close_btn;
    
    @FXML
    private JFXComboBox<ComboBoxItem> fridage_CB;

    //=======================================================================================================
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
	ApplicationContext.currentUser=(Users) users.get(0);
	this.setCurrentUser(ApplicationContext.currentUser);
	URL u=	 getClass().getClassLoader().getResource("appResources/custom.css");
	 String css =u.toExternalForm();
	 
	
    ApplicationView appView=new ApplicationView();
      
         
        
        
        Scene scene= new Scene(appView.getView(), 1000, 600);
		Stage application=new Stage();
		application.setScene(scene);
		 
 //		popupwindow.initStyle(StageStyle.TRANSPARENT);
		application.setTitle("A&D Accountant and Management System");
 		scene.getStylesheets().addAll(css); 
 		application.initModality(Modality.WINDOW_MODAL);
 		application.show();

 	     this.getAppStage().hide();


} catch (DataBaseException | EmptyResultSetException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    	
    	
    }

  @FXML
  void close(ActionEvent event){
	  Stage stage = (Stage) close_btn.getScene().getWindow();
      // do what you have to do
      stage.close();
	  
  }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	
    	
    	
	  	  logger.log(Level.INFO,"============================================================================================================");

   

    	
    	
    	usernameTF.setPromptText(this.getMessage("login.username"));
    	passwordPass.setPromptText(this.getMessage("login.password"));
//========================================================================================================================================================
    	List fridages;
		try {
			fridages = this.getBaseService().findAllBeans(Fridage.class);
			for (int i = 0; i < fridages.size(); i++) {
	    		Fridage fridage = (Fridage) fridages.get(i);
	        	fridage_CB.getItems().add(new ComboBoxItem(fridage.getId(),fridage.getName()));

				
			}
			fridage_CB.getSelectionModel().selectFirst();
		} catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	
//========================================================================================================================================================

		  Glyph glyph = new FontAwesome().create( FontAwesome.Glyph.SIGN_IN);
		   glyph.setColor(Color.WHITESMOKE);
		loginBtn.setGraphic(glyph);
		loginBtn.setText(getMessage("label.login"));
		loginBtn.getStyleClass().setAll("btn","btn-info","btn-xs"); 

		//------------------------------------------------------------------------
		    glyph = new FontAwesome().create( FontAwesome.Glyph.CLOSE);
		   glyph.setColor(Color.WHITESMOKE);
		close_btn.setGraphic(glyph);
		close_btn.setText(getMessage("label.close"));
		close_btn.getStyleClass().setAll("btn","btn-info","btn-xs"); 


  }
}