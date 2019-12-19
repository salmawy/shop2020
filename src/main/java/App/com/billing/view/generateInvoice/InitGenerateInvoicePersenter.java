package App.com.billing.view.generateInvoice;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import App.com.Customer.transactions.view.beans.CustomerNameViewBean;
import App.com.Customer.transactions.view.beans.InvoiceViewbean;
import App.com.billing.action.BillingAction;
import App.core.Enum.CustomerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.UIComponents.customTable.CustomTableActions;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Fridage;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class InitGenerateInvoicePersenter extends BillingAction implements Initializable, CustomTableActions {

	 @FXML
	    private JFXButton suggestedInvoices_btn;

	    @FXML
	    private AnchorPane invoicesTable_loc;

	    @FXML
	    private JFXComboBox<ComboBoxItem> customerType_combo;

	    @FXML
	    private AnchorPane customersTable_loc;
	    
	    
	    
	    
	    
	    
	    private CustomTable<CustomerNameViewBean> customersCustomeTable;
	    private CustomTable<InvoiceViewbean> invoiceCustomeTable;
	    
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		init();
	}

	
	
	private void init() {
		
		
		
		
		customerType_combo.setPromptText(this.getMessage("label.customer.Type"));
		customerType_combo.getStyleClass().add("comboBox");
		
		customerType_combo.getItems().add(new ComboBoxItem(CustomerTypeEnum.kareem,this.getMessage("customer.type.karrem")));
		customerType_combo.getItems().add(new ComboBoxItem(CustomerTypeEnum.mahmed,this.getMessage("customer.type.mahmed")));
		customerType_combo.getItems().add(new ComboBoxItem(CustomerTypeEnum.normal,this.getMessage("customer.type.normal")));
		customerType_combo.getItems().add(new ComboBoxItem(CustomerTypeEnum.purchases,this.getMessage("customer.type.purchaes")));
		customerType_combo.getSelectionModel().select(0);
//=============================================================================================================================================
		  List<Column>customersColumns=prepareCustomerTabelColumns(); 
		  List<Column>invoiceColumns=prepareInvoiceTabelColumns(); 
		  List invoiceTableControl=prepareInvoiceControles();
		  invoiceCustomeTable=new CustomTable<InvoiceViewbean>(invoiceColumns,invoiceTableControl,null,null,null,CustomTable.headTableCard,InvoiceViewbean.class); 
		  customersCustomeTable=new CustomTable<CustomerNameViewBean>(customersColumns,null,null,null,this,CustomTable.tableCard,CustomerNameViewBean.class); 
			AnchorPane customersTable=customersCustomeTable.getCutomTableComponent();
			AnchorPane invoiceTable=invoiceCustomeTable.getCutomTableComponent();
			fitToAnchorePane(customersTable);
			fitToAnchorePane(invoiceTable);
			customersTable.getChildren().addAll(customersTable);
			invoicesTable_loc.getChildren().addAll(invoiceTable);
//=============================================================================================================================================
 			suggestedInvoices_btn.setText(getMessage("button.invoice.suggestedInvoices"));
 		     Text  layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.ARROW_RIGHT);
		       layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
		       suggestedInvoices_btn.setGraphic(layoutIcon);
		       suggestedInvoices_btn.getStyleClass().setAll("btn","btn-sm","btn-info");  
		            		
		       suggestedInvoices_btn.setOnAction(e -> {
		       	
		        
		         
		           	
		           });
		
  //=============================================================================================================================================
		       loadSuggestedInvoices();
		       loadsuggestedCustomers(customerType_combo.getSelectionModel().getSelectedItem().getValue());
	}
	
	
	   @SuppressWarnings("unchecked")
	private void loadsuggestedCustomers(int typeId) {

			List customerViewBeans=new ArrayList<>();	
		Season season=this.getSeason();
		Fridage fridage=this.getFridage();
		
		List result=null;
		try {
			result = this.getBillingService().getSuggestedCustomersOrders(0, 0, ApplicationContext.season.getId(), ApplicationContext.fridage.getId(), typeId);
		
		
				for (Object it : result) {
					Customer customer=(Customer) it;
					CustomerNameViewBean viewBean=new CustomerNameViewBean(customer.getId(),customer.getName(),customer.getName());
					customerViewBeans.add(viewBean);

					
				}
				
				
				customersCustomeTable.loadTableData(customerViewBeans);

				
		}
		catch (EmptyResultSetException e) {
			// TODO Auto-generated catch block
				
			alert(AlertType.WARNING, "", "", this.getMessage("msg.warning.noData"));
			
			customersCustomeTable.getTable().getItems().clear();
			}
		 
	  catch ( DataBaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
	    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));
				customersCustomeTable.getTable().getItems().clear();
	
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

	private void loadSuggestedInvoices() {
		

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");

		List invoices=new ArrayList<>();
		List invoicsViewBeans=new ArrayList();
		try {
				
			invoices = this.getBillingService().getSuggestedOrders(0, 0, ApplicationContext.season.getId(), 0, ApplicationContext.fridage.getId());
					
			

			for (Object it : invoices) {
				CustomerOrder order=(CustomerOrder) it;
				InvoiceViewbean viewBean=new InvoiceViewbean();
				viewBean.setId(order.getId());
				viewBean.setInvoiceDate(sdf.format(order.getDueDate()));
				viewBean.setProductName(order.getProduct().getName());
				viewBean.setGrossWeight(order.getGrossweight());
				viewBean.setNetWeight(order.getNetWeight());
				viewBean.setNolun(order.getNolun());
				viewBean.setTotalAmount(order.getTotalPrice());
				viewBean.setTips(order.getNolun());
				viewBean.setCommision(order.getCommision());
				viewBean.setNetAmount(order.getNetPrice());
				viewBean.setOrderTag(order.getOrderTag());
				invoicsViewBeans.add(viewBean);

				
			}
			
			
			
		} catch ( EmptyResultSetException e) {
			// TODO Auto-generated catch block
		alert(AlertType.WARNING, "", "", this.getMessage("msg.warning.noData"));
			
			invoiceCustomeTable.getTable().getItems().clear()	;	}
		catch (DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));
			  invoiceCustomeTable.getTable().getItems().clear();
	
		}
		
		
		
}



	private List prepareInvoiceControles(){
	    	
	    	
	    	JFXButton addBtn=new JFXButton(this.getMessage("button.invoice.generate"));
	    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.PLUS);
	    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
	    	    addBtn.setGraphic(layoutIcon);
	    	    addBtn.getStyleClass().setAll("btn","btn-info","btn-sm");                     //(2)
	    	    addBtn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
	 	    	   public void handle(MouseEvent event) { 
	 	    		      System.out.println("add has been clicked"); 
	 	    		    // addOrderDetail(); 
	 	    		   } 
	 	    		}));
	    	    
	    


	    	    List buttons =new ArrayList<JFXButton>(Arrays.asList(addBtn))  ;

	    	return buttons;
	    	
	    }


	private List<Column> prepareCustomerTabelColumns(){
             
         List<Column> columns=new ArrayList<Column>();
   
        Column c1=new Column("id", "id", "int", 0, false);
         columns.add(c1);
        Column c2=new Column(this.getMessage("customer.name"), "name", "string", 100, true);
           columns.add(c2);
          
    
    
    return columns;
    
    
    
    
    
    
    }


    
	
  
	
	
	
	
@FXML 
private void customerTypeSelected(ActionEvent event) {
	ComboBoxItem item= customerType_combo.getSelectionModel().getSelectedItem();
	
	int typeId= item.getValue();
	loadsuggestedCustomers(typeId);
	
	
	
	
	
}



	
private void fitToAnchorePane(Node node) {
	
	
	AnchorPane.setTopAnchor(node,  0.0); 
	AnchorPane.setLeftAnchor(node,  0.0); 
	AnchorPane.setRightAnchor(node,  0.0); 
	AnchorPane.setBottomAnchor(node,  0.0); 
	
	
	
} 
    private List<Column> prepareInvoiceTabelColumns(){
    

         List<Column> columns=new ArrayList<Column>();
   
         Column  c=new Column("id", "id", "int", 10, true);
         columns.add(c);
        
         c=new Column(this.getMessage("label.invoice.tag"), "orderTag", "double", 75, true);
         columns.add(c);
         
         c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "double", 15, true);
           columns.add(c);
         
    
    
    return columns;
    
    
    
    
    
    
    }



	@Override
	public void rowSelected() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void rowSelected(Object o) {
		// TODO Auto-generated method stub
		
	}
    
  
    
}
