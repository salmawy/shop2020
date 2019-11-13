/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.application.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import App.com.Customer.discharge.view.InitCustomerDischargeView;
import App.com.Customer.purchases.view.CustomerPurchasesPresenter;
import App.com.Customer.purchases.view.CustomerPurchasesView;
import App.com.Customer.purchases.view.beans.PurchasedInstsViewBean;
import App.com.Customer.transactions.view.TransactionsView;
import App.core.action.BaseAction;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
	    appContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

	  this.getAppStage().setResizable(true);
	  
	  
	  
	  


		 
		 
  }  
     
  
  

  
  
	@FXML 
	
	private void loadDischargePanel(ActionEvent event) {
		
		
		Task task = new Task < Void > () {
		 @Override public void run() {



			  InitCustomerDischargeView customerDischargeView = new InitCustomerDischargeView();
			  VBox customerDischarge = (VBox) customerDischargeView.getView();
			  customerDischarge.setMaxWidth(appContainer.getMaxWidth());
			  customerDischarge.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				    FadeTransition ft = new FadeTransition(Duration.millis(1500));
			        ft.setNode(customerDischarge);
			        ft.setFromValue(0.1);
			        ft.setToValue(1);
			        ft.setCycleCount(1);
			        ft.setAutoReverse(false);
			        ft.play();
			    appContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

			   }
			  });




			 }


			 @Override
			 protected Void call() throws Exception {

			  return null;
			 }
			};


			Thread t = new Thread(task);
			t.setDaemon(true);
			t.start();}

	
	@FXML 
	
	private void loadTrasnactionsePanel(ActionEvent event) {
	
		
		Task task = new Task < Void > () {
		 @Override public void run() {



			  TransactionsView transactionsView = new TransactionsView();
			  AnchorPane customersTransactions = (AnchorPane) transactionsView.getView();
			  customersTransactions.setMaxWidth(appContainer.getMaxWidth());
			  customersTransactions.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				    FadeTransition ft = new FadeTransition(Duration.millis(1500));
			        ft.setNode(customersTransactions);
			        ft.setFromValue(0.1);
			        ft.setToValue(1);
			        ft.setCycleCount(1);
			        ft.setAutoReverse(false);
			        ft.play();
			    appContainer.getChildren().setAll(customersTransactions);

			   }
			  });




			 }


			 @Override
			 protected Void call() throws Exception {

			  return null;
			 }
			};


			Thread t = new Thread(task);
			t.setDaemon(true);
			t.start();}

@FXML 
	
	private void loadPurchasedPanel(ActionEvent event) {
	
		
		Task task = new Task < Void > () {
		 @Override public void run() {



			  CustomerPurchasesView purchasesView = new CustomerPurchasesView();
			  AnchorPane purchases = (AnchorPane) purchasesView.getView();
			  purchases.setMaxWidth(appContainer.getMaxWidth());
			  purchases.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				    FadeTransition ft = new FadeTransition(Duration.millis(1500));
			        ft.setNode(purchases);
			        ft.setFromValue(0.1);
			        ft.setToValue(1);
			        ft.setCycleCount(1);
			        ft.setAutoReverse(false);
			        ft.play();
			    appContainer.getChildren().setAll(purchases);

			   }
			  });




			 }


			 @Override
			 protected Void call() throws Exception {

			  return null;
			 }
			};


			Thread t = new Thread(task);
			t.setDaemon(true);
			t.start();}

	
	
	
	
	


}
