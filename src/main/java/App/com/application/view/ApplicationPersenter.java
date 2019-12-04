/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.application.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import App.com.Customer.discharge.view.InitCustomerDischargeView;
import App.com.Customer.purchases.view.CustomerPurchasesView;
import App.com.Customer.transactions.view.TransactionsView;
import App.com.expanses.view.expanses.ExpansesView;
import App.com.expanses.view.loan.LoansView;
import App.com.sales.debt.view.DebtsView;
import App.com.sales.view.DailySalesView;
import App.core.action.BaseAction;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author ahmed
 */
public class ApplicationPersenter extends BaseAction implements Initializable {



    @FXML
    private JFXButton arcSellersLoan_btn;

    @FXML
    private JFXButton dischargingBTN;

    @FXML
    private JFXButton purchasesBTN;

    @FXML
    private HBox topContainer;

    @FXML
    private AnchorPane appPage;

    @FXML
    private JFXButton transactionsBTN;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TitledPane sellerComp;

    @FXML
    private Accordion App_Components;

    @FXML
    private   StackPane mystackPane;

    @FXML
    private JFXButton dailySelling_btn;

    @FXML
    private TitledPane customersComp;

    @FXML
    private JFXButton sellerDebts_btn;
     
    @FXML

    public   JFXSpinner spinner;
    @FXML
    private AnchorPane appContainer;
    @FXML
   HBox spinnerPane;
  
    @FXML
    private TitledPane expansesComp;
	  
   @FXML
   private JFXButton Expanses_btn;

   @FXML
   private JFXButton  shopDebts_btn;
  @Override
    public void initialize(URL location, ResourceBundle resources){ 
       

        intiateApp();  
    }
    

  private void intiateApp(){
	
	//  this.getAppStage().getStylesheets().add("appCssFile.css");

	  sellerComp.setText(this.getMessage("title.selling"));
	  customersComp.setText(this.getMessage("title.customers"));
	  expansesComp.setText(this.getMessage("label.expanses"));
	  

	  
	  
	  purchasesBTN.setText(this.getMessage("button.purchases"));
	  transactionsBTN.setText(this.getMessage("button.transaction"));
	  dischargingBTN.setText(this.getMessage("button.discharge"));
	  
	  
	  sellerDebts_btn.setText(this.getMessage("button.sellersLoans"));
	  dailySelling_btn.setText(this.getMessage("button.dailySelling"));
	  arcSellersLoan_btn.setText(this.getMessage("button.sellersLoans"));
	  
	  
	  Expanses_btn.setText(this.getMessage("label.expanses"));
	  shopDebts_btn.setText(this.getMessage("label.shopDebts"));

	  
	    appContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

	  this.getAppStage().setResizable(true);
	  setCardLayout(this.mystackPane); 
	  
	  
	  


		 
		 
  }  
     
  
  

  
  
	@FXML 
	
	private void loadDischargePanel(ActionEvent event) {
		
		 changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {

			


			  InitCustomerDischargeView customerDischargeView = new InitCustomerDischargeView();
			  AnchorPane customerDischarge = (AnchorPane) customerDischargeView.getView();
			 
			  
			  Platform.runLater(new Runnable() {

			   @Override public void run() {
					  changeTop();

						
						/*
						 * FadeTransition ft = new FadeTransition(Duration.millis(1500));
						 * ft.setNode(customerDischarge); ft.setFromValue(0.1); ft.setToValue(1);
						 * ft.setCycleCount(1); ft.setAutoReverse(false); ft.play();
						 */
					  fitToAnchorePane(customerDischarge);
					  appContainer.getChildren().setAll(customerDischarge);

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
	
		changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {



			  TransactionsView transactionsView = new TransactionsView();
			  AnchorPane customersTransactions = (AnchorPane) transactionsView.getView();
			  customersTransactions.setMaxWidth(appContainer.getMaxWidth());
			  customersTransactions.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				   
				   changeTop();
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
	changeTop();
		
		Task task = new Task < Void > () {
		 @Override public void run() {



			  CustomerPurchasesView purchasesView = new CustomerPurchasesView();
			  AnchorPane purchases = (AnchorPane) purchasesView.getView();
			  purchases.setMaxWidth(appContainer.getMaxWidth());
			  purchases.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				   changeTop();
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

	
	
	
	

@FXML 
	
	private void loadDailySalesPanel(ActionEvent event) {
	
	changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {


			  DailySalesView salesView = new DailySalesView();
			  AnchorPane salesPane = (AnchorPane) salesView.getView();
			  salesPane.setMaxWidth(appContainer.getMaxWidth());
			  salesPane.setMaxHeight(appContainer.getMaxHeight());


			  Platform.runLater(new Runnable() {

			   @Override public void run() {
				   changeTop();
				    FadeTransition ft = new FadeTransition(Duration.millis(1500));
			        ft.setNode(salesPane);
			        ft.setFromValue(0.1);
			        ft.setToValue(1);
			        ft.setCycleCount(1);
			        ft.setAutoReverse(false);
			        ft.play();
			    appContainer.getChildren().setAll(salesPane);

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

	
	
	
	
private void fitToAnchorePane(Node node) {
	
	
	AnchorPane.setTopAnchor(node,  0.0); 
	AnchorPane.setLeftAnchor(node,  0.0); 
	AnchorPane.setRightAnchor(node,  0.0); 
	AnchorPane.setBottomAnchor(node,  0.0); 
	
	
	
} 




	@FXML 
	
	private void loadSellersDebts(ActionEvent event) {
		
		 changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {

			


			  DebtsView debtsView = new DebtsView();
			  AnchorPane sellersDebts = (AnchorPane) debtsView.getView();
			 
			  
			  Platform.runLater(new Runnable() {

			   @Override public void run() {
					  changeTop();

						
						
						  FadeTransition ft = new FadeTransition(Duration.millis(1500));
						  ft.setNode(sellersDebts); ft.setFromValue(0.1); ft.setToValue(1);
						  ft.setCycleCount(1); ft.setAutoReverse(false); ft.play();
						 
					  fitToAnchorePane(sellersDebts);
					  appContainer.getChildren().setAll(sellersDebts);

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
	
	private void loadExpanses(ActionEvent event) {
		
		 changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {

			


			  ExpansesView expansesView = new ExpansesView();
			  AnchorPane expansesPane= (AnchorPane) expansesView.getView();
			 
			  
			  Platform.runLater(new Runnable() {

			   @Override public void run() {
					  changeTop();

						  FadeTransition ft = new FadeTransition(Duration.millis(1500));
						  ft.setNode(expansesPane); ft.setFromValue(0.1); ft.setToValue(1);
						  ft.setCycleCount(1); ft.setAutoReverse(false); ft.play();
						 
						
					  fitToAnchorePane(expansesPane);
					  appContainer.getChildren().setAll(expansesPane);

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
	
	private void loadShopDebts(ActionEvent event) {
		
		 changeTop();
		Task task = new Task < Void > () {
		 @Override public void run() {

			


			  LoansView loansView = new LoansView();
			  AnchorPane loansPane= (AnchorPane) loansView.getView();
			 
			  
			  Platform.runLater(new Runnable() {

			   @Override public void run() {
					  changeTop();

						  FadeTransition ft = new FadeTransition(Duration.millis(1500));
						  ft.setNode(loansPane); ft.setFromValue(0.1); ft.setToValue(1);
						  ft.setCycleCount(1); ft.setAutoReverse(false); ft.play();
						 
						
					  fitToAnchorePane(loansPane);
					  appContainer.getChildren().setAll(loansPane);

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



private void changeTop() {
    ObservableList<Node> childs = this.mystackPane.getChildren();
    mystackPane.setBackground(Background.EMPTY);


    if (childs.size() > 1) {
        //
        Node topNode = childs.get(childs.size()-1);
        topNode.toBack();
    }
}



}
