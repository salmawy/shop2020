package App.com.Customer.transactions.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import App.com.Customer.action.CustomerBaseAction;
import App.com.Customer.discharge.view.CustomerViewBean;
import App.com.Customer.transactions.view.beans.CustomerNameViewBean;
import App.com.Customer.transactions.view.beans.CustomerTotalViewBean;
import App.com.Customer.transactions.view.beans.InvoiceViewbean;
import App.core.Enum.CustomerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.UIComponents.customTable.CustomTableActions;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Fridage;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TransactionsPresenter extends CustomerBaseAction  implements Initializable ,CustomTableActions{
	
	
	 @FXML private AnchorPane CustomerTableLoc;



	    @FXML
	    private Label car_label;

	    @FXML
	    private Label totalAmount_value;

	    @FXML
	    private Label customerType_label;

	    @FXML
	    private Label totalVan_value;

	    @FXML
	    private Label van_value;

	    @FXML
	    private Label smokes_label;

	    @FXML
	    private Label totalCar_label;

	    @FXML
	    private Label totalCommision_value;

	    @FXML
	    private Label totalSmokes_value;

	    @FXML
	    private Label amount_label;

	    @FXML
	    private Label totalCar_value;

	    @FXML
	    private Label commision_label;

	    @FXML
	    private Label totalVan_label;

	    @FXML
	    private Label van_label;

	    @FXML
	    private Label car_value;

	    @FXML
	    private Label amount_value;

	    @FXML
	    private Label totoalCommision_label;

	    @FXML
	    private Label commision_value;

	
	    @FXML
	    private Label totalAmount_label;

	    @FXML
	    private Label totoalSmokes_label;

	

	    @FXML
	    private Label smokes_value;

	   

	
	
	  
	  @FXML private AnchorPane head1;
	  
	  @FXML private AnchorPane head2;
	  
	  
	  @FXML private AnchorPane detail;
	 
	    
	    @FXML
	    private JFXComboBox<ComboBoxItem> customerTypes_CB;


	  
	    
	    private CustomTable<CustomerNameViewBean> customersCustomeTable;
	    private CustomTable<InvoiceViewbean> invoiceCustomeTable;
	    

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			
        System.out.println(customerType_label.getText());
			customerType_label.setText(this.getMessage("label.customer.Type"));
			customerTypes_CB.getStyleClass().add("comboBox");
			
			customerTypes_CB.getItems().add(new ComboBoxItem(CustomerTypeEnum.kareem,this.getMessage("customer.type.karrem")));
			customerTypes_CB.getItems().add(new ComboBoxItem(CustomerTypeEnum.mahmed,this.getMessage("customer.type.mahmed")));
			customerTypes_CB.getItems().add(new ComboBoxItem(CustomerTypeEnum.normal,this.getMessage("customer.type.normal")));
			customerTypes_CB.getItems().add(new ComboBoxItem(CustomerTypeEnum.purchases,this.getMessage("customer.type.purchaes")));
			customerTypes_CB.getSelectionModel().select(0);
			
			
			  List<Column>customersColumns=prepareCustomerTabelColumns(); 
			  List<Column>invoiceColumns=prepareInvoiceTabelColumns(); 

			  
			  invoiceCustomeTable=new CustomTable<InvoiceViewbean>(invoiceColumns,null,null,null,null,CustomTable.tableCard,InvoiceViewbean.class); 

			  List<CustomerNameViewBean>customersNames=loadCustomerNames(3);
			  customersCustomeTable=new CustomTable<CustomerNameViewBean>(customersColumns,null,null,customersNames,this,CustomTable.tableCard,CustomerNameViewBean.class); 

			  
			 // customers.getCutomTableComponent().setMaxSize(CustomerTableLoc.getPrefWidth(), CustomerTableLoc.getPrefHeight());
			AnchorPane customersTable=customersCustomeTable.getCutomTableComponent();
			AnchorPane invoiceTable=invoiceCustomeTable.getCutomTableComponent();

			
		fitToAnchorePane(customersTable);
		fitToAnchorePane(invoiceTable);
		
			
			CustomerTableLoc.getChildren().addAll(customersCustomeTable.getCutomTableComponent());
			  detail.getChildren().addAll(invoiceCustomeTable.getCutomTableComponent());
			  initWidgetsLabel();
			  
			
		}

	
	
	
	
	
	
	
	
		List <CustomerNameViewBean> loadCustomerNames(int typeId) {
			List customerViewBeans=new ArrayList<>();	
		Season season=this.getSeason();
		Fridage fridage=this.getFridage();
		
		List result;
		try {
			result = this.getCustomerService().getSeasonCustomers(season.getId(), fridage.getId(), typeId);
		
		
				for (Object it : result) {
					Customer customer=(Customer) it;
					CustomerNameViewBean viewBean=new CustomerNameViewBean(customer.getId(),customer.getName(),customer.getName());
					customerViewBeans.add(viewBean);

					
				}
				
				
		} catch (EmptyResultSetException | DataBaseException e) {
			// TODO Auto-generated catch block
				
			e.printStackTrace();
		}
			
			
			
			return customerViewBeans;
		} 
	    private List<Column> prepareCustomerTabelColumns(){
	             
	         List<Column> columns=new ArrayList<Column>();
	   
	        Column c1=new Column("id", "id", "int", 0, false);
	         columns.add(c1);
	        Column c2=new Column(this.getMessage("customer.name"), "name", "string", 100, true);
	           columns.add(c2);
	           Column      c3=new Column(this.getMessage("label.customer.Type"), "type", "string", 0, false);
	           columns.add(c3);
	    
	    
	    return columns;
	    
	    
	    
	    
	    
	    
	    }
	
	
	    
	    private List<Column> prepareInvoiceTabelColumns(){
	    

	         List<Column> columns=new ArrayList<Column>();
	   
	         Column  c=new Column("id", "id", "int", 0, false);
	         columns.add(c);
	         c=new Column(this.getMessage("invoice.date"), "invoiceDate", "date", 20, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.product"), "productName", "String", 10, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "double", 10, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.netWeight"), "netWeight", "double", 10, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.nolun"), "nolun", "double", 10, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.total.amount"), "totalAmount", "string", 10, true);
	          columns.add(c);
	          c=new Column(this.getMessage("label.gift"), "tips", "double", 10, true);
		      columns.add(c);
		      c=new Column(this.getMessage("label.commision"), "commision", "double", 10, true);
		        columns.add(c);
		     c=new Column(this.getMessage("label.netAmount"), "netAmount", "double", 10, true);
		        columns.add(c);
	    
	    
	    return columns;
	    
	    
	    
	    
	    
	    
	    }
	    
	  
	    
	    private void  initWidgetsLabel(){
	    

	         List<Column> columns=new ArrayList<Column>();
	   
	     
	         Column  c=new Column(this.getMessage("label.vehicle.car"), "carType", "date", 20, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.vehicle.van"), "vanType", "String", 20, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.total.bananaPrice"), "totalAmount", "double", 20, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.commision"), "totalCommision", "double", 20, true);
	           columns.add(c);
	          c=new Column(this.getMessage("label.total.smokes"), "totalSmokes", "double", 20, true);
	          columns.add(c); 
	    

		    	totoalSmokes_label.setText(this.getMessage("label.total.smokes"));
		    	totoalCommision_label.setText(this.getMessage("label.commision"));
		    	totalAmount_label.setText(this.getMessage("label.total.bananaPrice"));
		    	totalCar_label.setText(this.getMessage("label.vehicle.car"));
		    	totalVan_label.setText(this.getMessage("label.vehicle.van"));
		    	
		    	smokes_label.setText(this.getMessage("label.total.smokes"));
		    	commision_label.setText(this.getMessage("label.commision"));
		    	amount_label.setText(this.getMessage("label.total.bananaPrice"));
		    	car_label.setText(this.getMessage("label.vehicle.car"));
		    	van_label.setText(this.getMessage("label.vehicle.van"));
		    	
		    	smokes_value.setText(String.valueOf(0.0));
		    	commision_value.setText(String.valueOf(0.0));
		    	amount_value.setText(String.valueOf(0.0));
		    	car_value.setText(String.valueOf(0.0));
		    	van_value.setText(String.valueOf(0.0));

		  try {
			List data=  this.getCustomerService().getCustomersSummaryTransactions(this.getSeason().getId(), 0, 0);
			totalSmokes_value.setText(String.valueOf(data.get(0)));
	    	totalCommision_value.setText(String.valueOf(data.get(2)));
	    	totalAmount_value.setText(String.valueOf(data.get(1)));
	    	totalCar_value.setText(String.valueOf(data.get(4)));
	    	totalVan_value.setText(String.valueOf(data.get(3)));
	    
	    

	    
		  
		  } catch (EmptyResultSetException | DataBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		    
		 
	    
	    
	    
	    
	    
	    }
	    
	  

	
private void fitToAnchorePane(Node node) {
	
	
	AnchorPane.setTopAnchor(node,  0.0); 
	AnchorPane.setLeftAnchor(node,  0.0); 
	AnchorPane.setRightAnchor(node,  0.0); 
	AnchorPane.setBottomAnchor(node,  0.0); 
	
	
	
}
	
@FXML 
private void customerTypeSelected(ActionEvent event) {
	ComboBoxItem item= customerTypes_CB.getSelectionModel().getSelectedItem();
	
	int typeId= item.getValue();
	List <CustomerNameViewBean>data=loadCustomerNames(typeId);
	
	customersCustomeTable.loadTableData(data);
	
	
	
	
	
}









@Override
public void update() {
	// TODO Auto-generated method stub
	
}









@Override
public void save() {
	// TODO Auto-generated method stub
	
}









@Override
public void add() {
	// TODO Auto-generated method stub
	
}









@Override
public void cancel() {
	// TODO Auto-generated method stub
	
}









@Override
public void rowSelected() {
	
	CustomerNameViewBean item= (CustomerNameViewBean) this.customersCustomeTable.getTable().getSelectionModel().getSelectedItem();
	item.getId();
	List data=null;
			
	try {
		
				
				
		 data=  this.getCustomerService().getCustomersSummaryTransactions(this.getSeason().getId(), 0, item.getId());
		smokes_value.setText(String.valueOf(data.get(0)));
    	commision_value.setText(String.valueOf(data.get(2)));
    	amount_value.setText(String.valueOf(data.get(1)));
    	car_value.setText(String.valueOf(data.get(4)));
    	van_value.setText(String.valueOf(data.get(3)));
    
    

    	data=loadInvoices(item.getId());
    	this.invoiceCustomeTable.loadTableData(data);
    	
	
	
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}





List <InvoiceViewbean> loadInvoices(int customerId ) {
	

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");

	List invoices=new ArrayList<>();
	List invoicsViewBeans=new ArrayList<>();
	try {
			
		invoices = this.getCustomerService().getCustomerInvoices(getSeason().getId(), customerId,0);
				
		

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
			
			invoicsViewBeans.add(viewBean);

			
		}
		
		
		
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return invoicsViewBeans;
} 
    
    















}
