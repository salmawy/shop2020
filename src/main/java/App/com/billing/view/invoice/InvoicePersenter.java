package App.com.billing.view.invoice;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import App.com.application.view.ApplicationPersenter;
import App.com.billing.action.BillingAction;
import App.core.Enum.CustomerTypeEnum;
import App.core.Enum.ProductTypeEnum;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.CustomerOrder;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class InvoicePersenter extends BillingAction implements Initializable {
	
	Logger logger = Logger.getLogger(this.getClass().getName());	

	
	@FXML
    private Label gift_label;

    @FXML
    private JFXTextField gift_TF;

    @FXML
    private Label lost_label;

    @FXML
    private JFXTextField netAmount_TF;

    @FXML
    private JFXTextField invoiceDate_TF;

    @FXML
    private Label invoiceId_label;

    @FXML
    private Label nolun_label;

    @FXML
    private Label netWeight_label;

    @FXML
    private Label count_label;

    @FXML
    private Label netAmount_label;

    @FXML
    private Label commision_label;

    @FXML
    private JFXTextField productName_TF;

    @FXML
    private JFXButton generate_btn;

    @FXML
    private AnchorPane weightTable_loc;

    @FXML
    private JFXTextField totalAmount_TF;

    @FXML
    private Label weights_label;

    @FXML
    private JFXTextArea notes_TA;

    @FXML
    private JFXTextField nolun_TF;

    @FXML
    private Label grossWeight_label;

    @FXML
    private Label vehicleType_label;

    @FXML
    private Label invoiceDate_label;

    @FXML
    private JFXTextField vehicleType_TF;

    @FXML
    private JFXTextField netWeight_TF;

    @FXML
    private Label totalAmount_label;

    @FXML
    private JFXTextField invoiceId_TF;

    @FXML
    private JFXTextField count_TF;

    @FXML
    private Label productName_label;

    @FXML
    private JFXTextField commision_TF;

    @FXML
    private JFXTextField lost_TF;

    @FXML
    private JFXTextField grossWeight_TF;
    
    
    
     private CustomerOrder invoice;


	private int typeId;


	private int invoiceId;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
 
		  invoiceId=(int) this.request.get("invoiceId");
		  typeId=(int) this.request.get("typeId");
		
		
		init();
	}

 private  void init() {
 
	 
	
//======================================================labels names=========================================================================
	  
 
		invoiceId_label.setText(this.getMessage("invoice.No"));
		invoiceDate_label.setText(this.getMessage("invoice.date"));
		productName_label.setText(this.getMessage(""));
		nolun_label.setText(this.getMessage("label.nolun"));
		grossWeight_label.setText(this.getMessage("label.grossWeight"));
		netWeight_label.setText(this.getMessage("label.netWeight"));
		lost_label.setText(this.getMessage("label.empty"));
		count_label.setText(this.getMessage("label.count.sabait"));
		totalAmount_label.setText(this.getMessage("label.total.amount"));
		gift_label.setText(this.getMessage("label.gift"));
		commision_label.setText(this.getMessage("label.commision"));
		netAmount_label.setText((typeId==CustomerTypeEnum.purchases)?this.getMessage("button.purchases.price"):this.getMessage("label.netAmount"));
		vehicleType_label.setText(this.getMessage("label.vehicle.type"));
	

//===============================================================================================================================
		render();

		  	 
 }
 
 private void render() {
	 {                                                   

		 
		 
		 
		 
		 SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd"); 

		

 	      
			try{
				
				invoice=	(CustomerOrder) this.getBaseService().getBean(CustomerOrder.class, invoiceId);
			 
			} catch (DataBaseException | InvalidReferenceException e) {
				   alert(AlertType.ERROR, this.getMessage("msg.err"),
							  this.getMessage("msg.err"), 
							 this.getMessage("msg.err.general"));
				e.printStackTrace();
			}
			 
	       // List orerWeights  = this.getBillingService().getCustomersOrderWeights(invoice.getId());

	        int product_id = invoice.getProductId();
	        if (product_id == ProductTypeEnum.imported) {
	            this.netWeight_TF.setVisible(false);
	            this.netWeight_label.setVisible(false);
	            this.lost_label.setVisible(false);
	            this.count_TF.setVisible(false);
	            this.lost_TF.setVisible(false);
	            this.count_label.setVisible(false);
	            this.grossWeight_label.setText(this.getMessage("label.count"));

	       

	        } 

	        
//========================================================================================
 	        double totalAmount = getCustomerOrderTotalPrice(invoice.getId());
 	       double netWeight=getCustomerOrderNetWeight(invoice.getId());
	   	 this.invoiceId_TF.setText(String.valueOf(invoice.getId()));
		 this.invoiceDate_TF.setText(sdf.format(invoice.getOrderDate()));
		 this.productName_TF.setText(invoice.getProduct().getName());
		 this.nolun_TF.setText(String.valueOf(invoice.getNolun()));
		 this.grossWeight_TF.setText(String.valueOf(invoice.getGrossweight()));
		 this.count_TF.setText(String.valueOf(invoice.getUnits()));
 		 this.netWeight_TF.setText(String.valueOf(netWeight));
	     this.totalAmount_TF.setText(String.valueOf(totalAmount));
 		 this.vehicleType_TF.setText(String.valueOf(invoice.getVehicleType().getName()));
		 this.notes_TA.setText(invoice.getNotes());
		 

	        double commision = Math.rint(totalAmount * ApplicationContext.Cusotmer_Order_Ration);
	        this.commision_TF.setText(String.valueOf(commision));

	        double netPrice = totalAmount - (invoice.getNolun() + commision);
 	        this.netAmount_TF.setText(String.valueOf(netPrice));

	        

	        if (typeId==CustomerTypeEnum.purchases) {
	            commision = totalAmount - (invoice.getBuyPrice() + invoice.getNolun());
 	            this.commision_TF.setText(String.valueOf(commision));
	            this.netAmount_TF.setText(String.valueOf(invoice.getBuyPrice()));

	        }

  	        double ration = invoice.getGrossweight() - netWeight;
	        this.lost_TF.setText(String.valueOf(ration));
  }
 
 
 
 loadCustomerWeights(  invoice.getId());
 }
 
 
 
 
 
 
 
 private void loadCustomerWeights(int id) {
	// TODO Auto-generated method stub
     try {
		List orerWeights  = this.getBillingService().getCustomersOrderWeights(id);
		
		
		
		
		
		
		
		
		
		
	} catch (EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

private double getCustomerOrderNetWeight(int orderId) {
	 
 
 
 

		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerOrderId=", orderId);
	 
		Double netWeight=0.0;
		try {
			netWeight=(Double) this.getBaseRetrievalService().aggregate("SellerOrderWeight", "sum", "netQuantity", map);
		} catch (DataBaseException | EmptyResultSetException e) {
			// TODO Auto-generated catch block
			netWeight=0.0;
		}
		
		
	return netWeight;
		 
 
  
 }
 
 
 
 private double getCustomerOrderTotalPrice(int orderId) {
	 
   Map<String,Object> map=new HashMap<String, Object>();
		map.put("customerOrderId=", orderId);
	 
		Double netWeight=0.0;
		try {
			netWeight=(Double) this.getBaseRetrievalService().aggregate("SellerOrderWeight", "sum", "amount", map);
		} catch (DataBaseException | EmptyResultSetException e) {
			// TODO Auto-generated catch block
			netWeight=0.0;
		}
		
		
	return netWeight;
		 
 
  
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
