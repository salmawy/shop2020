/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.application.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.airhacks.afterburner.views.FXMLView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
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
    private TitledPane contractorComp;

    @FXML
    private HBox spinnerPane;

    @FXML
    private JFXButton payIInvoice_btn;

    @FXML
    private JFXButton arcSellersLoan_btn;

    @FXML
    private JFXButton purchasesBTN;

    @FXML
    private JFXButton sellerDebts_btn;

    @FXML
    private JFXButton varaities_btn;

    @FXML
    private TitledPane invoicesComp;

    @FXML
    private JFXButton suppliers_btn;

    @FXML
    private HBox topContainer;

    @FXML
    private AnchorPane appContainer;

    @FXML
    private JFXButton transactionsBTN;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TitledPane expansesComp;

    @FXML
    private JFXButton shopDebts_btn3;

    @FXML
    private Accordion App_Components;

    @FXML
    private JFXButton dailySelling_btn;

    @FXML
    private JFXButton Expanses_btn3;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private JFXButton dischargingBTN;

    @FXML
    private JFXButton notes_btn;

    @FXML
    private JFXButton shopDebts_btn;

    @FXML
    private TitledPane inventoryComp;

    @FXML
    private TitledPane sellerComp;

    @FXML
    private JFXButton generateInvoice_btn;

    @FXML
    private StackPane mystackPane;

    @FXML
    private JFXButton labour_btn;

    @FXML
    private JFXButton Expanses_btn;

    @FXML
    private TitledPane customersComp;

    @FXML
    private AnchorPane appPage;
   
   Map<String,String> panelPathes;
   
   
  @Override
    public void initialize(URL location, ResourceBundle resources){ 
	  fillPanelsMap();
        intiateApp();  
    }
    

  private void intiateApp(){
	
	//  this.getAppStage().getStylesheets().add("appCssFile.css");

	  sellerComp.setText(this.getMessage("title.selling"));
	  customersComp.setText(this.getMessage("title.customers"));
	  expansesComp.setText(this.getMessage("label.expanses"));
	  contractorComp.setText(this.getMessage("label.ownerWithdrawles"));
	  invoicesComp.setText(this.getMessage("label.invoices"));
	  inventoryComp.setText(this.getMessage("label.inventory"));
	  
	  
	  purchasesBTN.setText(this.getMessage("button.purchases"));
	  transactionsBTN.setText(this.getMessage("button.transaction"));
	  dischargingBTN.setText(this.getMessage("button.discharge"));
	  
	  
	  sellerDebts_btn.setText(this.getMessage("button.sellersLoans"));
	  dailySelling_btn.setText(this.getMessage("button.dailySelling"));
	  arcSellersLoan_btn.setText(this.getMessage("button.sellersLoans"));
	  
	  
	  Expanses_btn.setText(this.getMessage("label.expanses"));
	  shopDebts_btn.setText(this.getMessage("label.shopDebts"));

	  labour_btn.setText(this.getMessage("button.labour"));
	  suppliers_btn.setText(this.getMessage("button.suppliers"));
	  varaities_btn.setText(this.getMessage("button.varaties"));
	 
	  
	  
	  appContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

	  this.getAppStage().setResizable(true);
	  setCardLayout(this.mystackPane); 
	  
	  
	  


		 
		 
  }  
     
  

private void fitToAnchorePane(Node node) {
	
	
	AnchorPane.setTopAnchor(node,  0.0); 
	AnchorPane.setLeftAnchor(node,  0.0); 
	AnchorPane.setRightAnchor(node,  0.0); 
	AnchorPane.setBottomAnchor(node,  0.0); 
	
	
	
} 





	@FXML 
	private void LoadPanel(ActionEvent event) {
		
		 changeTop();
		 
		 
		 
		 
		Task task = new Task < Void > () {
		 @Override public void run() {
			
			
	           System.out.println("loading => " +((Control)event.getSource()).getId());
			
			  FXMLView view=null;
			
				try {
					view = loadView( ((Control)event.getSource()).getId());

					  AnchorPane anchorPane= (AnchorPane) view.getView();
					 
					  
					  Platform.runLater(new Runnable() {

					   @Override public void run() {
							  changeTop();
							  
				

								  FadeTransition ft = new FadeTransition(Duration.millis(1500));
								  ft.setNode(anchorPane); ft.setFromValue(0.1); ft.setToValue(1);
								  ft.setCycleCount(1); ft.setAutoReverse(false); ft.play();
								 
								
							  fitToAnchorePane(anchorPane);
							  appContainer.getChildren().setAll(anchorPane);

					   }
					  });




				} catch (ClassNotFoundException e) {
					  Platform.runLater(new Runnable() {

						   @Override public void run() {
							   
							   
							   
							   alert(AlertType.ERROR, ApplicationPersenter.this.getMessage("msg.err"),
									ApplicationPersenter.this.getMessage("msg.err"), 
									ApplicationPersenter.this.getMessage("msg.err.general"));

							 changeTop();}
						  });
					
				}
			
			 }


			 @Override
			 protected Void call() throws Exception {

			  return null;
			 }
			};


			Thread t = new Thread(task);
			t.setDaemon(true);
			t.start();}
    private FXMLView loadView(String id ) throws ClassNotFoundException {
		String className=panelPathes.get(id);
		
		try {
			

			Class<?> clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor();
			Object object = ctor.newInstance( );
			return (FXMLView) object;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
    private void fillPanelsMap() {
	
	
	
	  panelPathes=new HashMap<String,String>();

	  panelPathes.put("arcSellersLoan_btn", "");
	  panelPathes.put("dischargingBTN", "App.com.Customer.discharge.view.InitCustomerDischargeView");
	  panelPathes.put("purchasesBTN", "App.com.Customer.purchases.view.CustomerPurchasesView");
	  panelPathes.put("transactionsBTN", "App.com.Customer.transactions.view.TransactionsView");
	  panelPathes.put("dailySelling_btn", "App.com.sales.view.DailySalesView");
	  panelPathes.put("sellerDebts_btn", "App.com.sales.debt.view.DebtsView");
	  panelPathes.put("Expanses_btn", "App.com.expanses.view.expanses.ExpansesView");
	  panelPathes.put("shopDebts_btn", "App.com.sales.debt.view.DebtsView");
	  panelPathes.put("notes_btn", "App.com.contractor.view.notes.NotesView");
	  panelPathes.put("varaities_btn", "App.com.contractor.view.varaities.VaraityView");
	  panelPathes.put("suppliers_btn", "App.com.contractor.view.suppliers.SupplierView");
	  panelPathes.put("labour_btn", "App.com.contractor.view.labours.LabourView");
	
	  
	  
	
	
	
	
	
}
	private void changeTop() {
    ObservableList<Node> childs = this.mystackPane.getChildren();
    mystackPane.setBackground(Background.EMPTY);


    if (childs.size() > 1) {
        //
        Node topNode = childs.get(childs.size()-1);
        topNode.toBack();
    }
}
	private void alert(AlertType alertType,String title,String headerText,String message) {
	 Alert a = new Alert(alertType);
	 a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	 a.setTitle(title );
	 a.setHeaderText(headerText);
	 a.setContentText(message); 
    a.show(); 
 
}






}
