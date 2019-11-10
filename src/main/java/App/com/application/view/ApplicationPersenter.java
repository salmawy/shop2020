/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import App.com.Customer.discharge.view.InitCustomerDischargeView;
import App.core.action.BaseAction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ahmed
 */
public class ApplicationPersenter extends BaseAction implements Initializable {


    @FXML
    private HBox topContainer;

    @FXML
    private VBox appContainer;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Accordion App_Components;
    @FXML
    private TitledPane customersComp;
    @FXML
    private TitledPane sellerComp;

    @FXML
    private JFXButton purchasesBTN;
    @FXML
    private JFXButton dischargingBTN;
    @FXML
    private JFXButton transactionsBTN;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){ 
        intiateApp();

        
        
    }

  private void intiateApp(){
	
	  
	  sellerComp.setText(this.getMessage("title.selling"));
	  customersComp.setText(this.getMessage("title.customers"));
	  purchasesBTN.setText(this.getMessage("button.purchases"));
	  transactionsBTN.setText(this.getMessage("button.transaction"));
	  dischargingBTN.setText(this.getMessage("button.discharge"));
	  
	  this.getAppStage().setResizable(true);
	  
	  
	  
	  
		//InitCustomerDischargeView customerDischargeView=new InitCustomerDischargeView();
		
		
		/*
		 * VBox customerDischarge=(VBox) customerDischargeView.getView();
		 * 
		 * 
		 * 
		 * customerDischarge.setMaxWidth(appContainer.getMaxWidth());
		 * customerDischarge.setMaxHeight(appContainer.getMaxHeight());
		 * appContainer.getChildren().setAll(customerDischarge);
		 */


		 
		 
  }  
      
	@FXML 
	
	private void loadDischargePanel(ActionEvent event) {
	
				  
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				/*
				 * try { this.sleep(4000); } catch (InterruptedException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
	        	InitCustomerDischargeView customerDischargeView=new InitCustomerDischargeView();
			   	 VBox customerDischarge=(VBox) customerDischargeView.getView();
				 customerDischarge.setMaxWidth(appContainer.getMaxWidth());
					  customerDischarge.setMaxHeight(appContainer.getMaxHeight());
					  appContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
					  appContainer.getChildren().setAll(customerDischarge);				        				
			}
		});
				 
				      


		
		
		
	}
 private VBox replaceSceneContent(String fxml) throws Exception {
              VBox box=null;
           try {
               URL resource = ApplicationPersenter.class.getResource(fxml);
             box = FXMLLoader.load(resource);
         

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ApplicationPersenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return box;
    }


}
