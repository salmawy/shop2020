package App.com.Customer.purchases.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import App.com.Customer.action.CustomerBaseAction;
import App.com.Customer.purchases.view.beans.PurchasedCustomersDataVB;
import App.com.Customer.purchases.view.beans.PurchasedInstsViewBean;
import App.com.Customer.purchases.view.beans.PurchasedInvoicesVB;
import App.com.Customer.purchases.view.beans.PurchasedOrdersViewBean;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.UIComponents.customTable.CustomTableActions;
import App.core.beans.CustomerOrder;
import App.core.beans.Fridage;
import App.core.beans.PurchasedCustomerInst;
import App.core.beans.Season;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CustomerPurchasesPresenter extends CustomerBaseAction implements Initializable,CustomTableActions {

	


	   @FXML
	    private AnchorPane instalments_loc;

	    @FXML
	    private Tab purchasesOrders_tab;

	    @FXML
	    private AnchorPane orderDetail_loc;

	    @FXML
	    private JFXButton refresh_btn;

	    
	    @FXML
	    private AnchorPane purchasesOrders_loc;

	    @FXML
	    private AnchorPane purchasedCustomer_loc;

	    @FXML
	    private Tab instalments_tab;
	private CustomTable<PurchasedInstsViewBean>instalmentsCustomTable;
	private CustomTable<PurchasedOrdersViewBean>purchasesOrdersCustomTable;
	private CustomTable<PurchasedCustomersDataVB>purchasesCustomerDataCustomTable;
	private CustomTable<PurchasedCustomersDataVB>purchasesInvoiceCustomTable;


	
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		instalments_tab.setText(this.getMessage("customer.purchases.instalments"));
		purchasesOrders_tab.setText(this.getMessage("customer.purchases.order.prices"));
		
		refresh_btn=new JFXButton(this.getMessage("button.add"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    	    refresh_btn.setGraphic(layoutIcon);
    	    refresh_btn.getStyleClass().setAll("btn","btn-primary");  
		
		
		List <Column>instalmentsColumns=preparePurchasedInstsTabelColumns();
		List <Column>purchasesOrdersColumns=preparePurchasedOrdersTabelColumns();
		List <Column>purchasesCustomerDataColumns=preparePurchasedCustomerDataTabelColumns();
		List <Column>purchasesInvoicesColumns=prepareInvoiceTabelColumns();

		List<JFXButton>purchasesOrdersButtons=preparePurchasedOrdersButtons();
		List<JFXButton>instalmentsButtons=preparePurchasedInstsButtons();
		List customersData=loadCustomerData() ;
		instalmentsCustomTable=new CustomTable<PurchasedInstsViewBean>(instalmentsColumns, instalmentsButtons, null, null, null, CustomTable.headTableCard, PurchasedInstsViewBean.class);
		purchasesOrdersCustomTable=new CustomTable<PurchasedOrdersViewBean>(purchasesOrdersColumns, purchasesOrdersButtons, null, null, null, CustomTable.headTableCard, PurchasedOrdersViewBean.class);
		purchasesCustomerDataCustomTable=new CustomTable<PurchasedCustomersDataVB>(purchasesCustomerDataColumns, null, null, customersData, this, CustomTable.tableCard, PurchasedCustomersDataVB.class);
		purchasesInvoiceCustomTable=new CustomTable<PurchasedCustomersDataVB>(purchasesInvoicesColumns, null, null, null, null, CustomTable.tableCard, PurchasedInvoicesVB.class);

		fitToAnchorePane(instalmentsCustomTable.getCutomTableComponent());
		fitToAnchorePane(purchasesOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(purchasesCustomerDataCustomTable.getCutomTableComponent());
		fitToAnchorePane(purchasesInvoiceCustomTable.getCutomTableComponent());
		purchasesCustomerDataCustomTable.getCutomTableComponent().setPrefWidth(420);
		
		
		instalments_loc.getChildren().addAll(instalmentsCustomTable.getCutomTableComponent());
		purchasesOrders_loc.getChildren().addAll(purchasesOrdersCustomTable.getCutomTableComponent());
		purchasedCustomer_loc.getChildren().addAll(purchasesCustomerDataCustomTable.getCutomTableComponent());
		orderDetail_loc.getChildren().addAll(purchasesInvoiceCustomTable.getCutomTableComponent());

		
	}

	
	
	
	List <PurchasedCustomersDataVB> loadCustomerData() {
		List customerViewBeans=new ArrayList<>();	
	Season season=this.getSeason();
	Fridage fridage=this.getFridage();
	
	List result;
	try {
		result = this.getCustomerService().getPurchasedCustomerData(this.getSeason().getId(), 0);
	
	
			for (Object it : result) {
				List row=(List) it;
				PurchasedCustomersDataVB viewBean=new PurchasedCustomersDataVB();
				viewBean.setId((int) row.get(0));
				viewBean.setName((String) row.get(1));
				viewBean.setOrderCost((double) row.get(2));
				viewBean.setPaidAmount((double) row.get(3));
				viewBean.setDueAmount((double) row.get(4));
				customerViewBeans.add(viewBean);

				
			}
			
			
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
			
		e.printStackTrace();
	}
		
		
		
		return customerViewBeans;
	} 
 
	
	
	
	List <PurchasedCustomersDataVB> loadCustomerInsts(int customerId) {
		List customerViewBeans=new ArrayList<>();	
	Season season=this.getSeason();
	Fridage fridage=this.getFridage();
	Map <String,Object>map=new HashMap <String,Object>();
	map.put("seasonId", season.getId());
	map.put("customerId", customerId);

	List result;
	try {
		result = this.getBaseService().findAllBeans(PurchasedCustomerInst.class, map, null);
	
	
			for (Object it : result) {
				PurchasedCustomerInst row=(PurchasedCustomerInst) it;
				PurchasedInstsViewBean viewBean=new PurchasedInstsViewBean();
				viewBean.setId( row.getId());
				viewBean.setDate(PurchasedInstsViewBean.sdf.format(row.getInstalmentDate()));
				viewBean.setAmount( row.getAmount());
				viewBean.setNotes( row.getNotes());

				
				
				customerViewBeans.add(viewBean);

				
			}
			
			
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
			
		e.printStackTrace();
	}
		
		
		
		return customerViewBeans;
	} 
	
	List <PurchasedCustomersDataVB> loadCustomerOrders(int customerId) {
		List customerViewBeans=new ArrayList<>();	
	Season season=this.getSeason();
	Fridage fridage=this.getFridage();
	Map <String,Object>map=new HashMap <String,Object>();
	map.put("seasonId", season.getId());
	map.put("customerId", customerId);

	List result;
	try {
		result=	this.getCustomerService().getCustomerInvoices(season.getId(), customerId, 0);
	
	
			for (Object it : result) {
				CustomerOrder row=(CustomerOrder) it;
				PurchasedOrdersViewBean viewBean=new PurchasedOrdersViewBean();
				viewBean.setId( row.getId());
				viewBean.setDate(PurchasedInstsViewBean.sdf.format(row.getOrderDate()));
				viewBean.setGrossWeight(row.getGrossweight());
				viewBean.setNolun(row.getNolun());
				viewBean.setUnitePrice(row.getUnitePrice());
				viewBean.setBuyPrice(row.getBuyPrice());

				
				
				customerViewBeans.add(viewBean);

				
			}
			
			
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
			
		e.printStackTrace();
	}
		
		
		
		return customerViewBeans;
	} 
 
	
    
	
    
    private List<Column> preparePurchasedInstsTabelColumns(){
    

         List<Column> columns=new ArrayList<Column>();
   
         Column  c=new Column("id", "id", "int", 0, false);
         columns.add(c);
         c=new Column(this.getMessage("label.date"), "date", "date", 30, true);
           columns.add(c);
          c=new Column(this.getMessage("label.money.amount"), "amount", "String", 20, true);
           columns.add(c);
          c=new Column(this.getMessage("label.notes"), "notes", "double", 50, true);
           columns.add(c);
         
    return columns;
    
    
    
    
    
    
    }
    
	
    
    private List<Column> preparePurchasedOrdersTabelColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
        Column  c=new Column("id", "id", "int", 0, false);
        columns.add(c);
        
        c=new Column(this.getMessage("invoice.date"), "date", "String", 30, true);
        columns.add(c);
       
        c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "String", 20, true);
        columns.add(c);

        c=new Column(this.getMessage("label.nolun"), "nolun", "double", 10, true);
        columns.add(c);
       
        c=new Column(this.getMessage("label.invoice.unitePrice"), "unitePrice", "double", 10, true);
        columns.add(c);
        
        c=new Column(this.getMessage("label.invoice.price"), "buyPrice", "double", 30, true);
          columns.add(c);
       
       
          
          
          
          
   return columns;
   
   
   
   
   
   
   }
  
   
    private List<Column> preparePurchasedCustomerDataTabelColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
        Column  c=new Column("id", "id", "int", 0, false);
        columns.add(c);
        c=new Column(this.getMessage("customer.name"), "name", "String", 30, true);
        columns.add(c);
        c=new Column(this.getMessage("label.bananaPrice"), "orderCost", "String", 30, true);
        columns.add(c);
       
        c=new Column(this.getMessage("label.money.paidAmount"), "paidAmount", "String", 20, true);
        columns.add(c);

        c=new Column(this.getMessage("label.money.dueAmount"), "dueAmount", "double", 20, true);
        columns.add(c);
       
     
       
       
          
          
          
          
   return columns;
   
   
   
   
   
   
   }
   
 
    private List<JFXButton>preparePurchasedOrdersButtons(){
    	//button.purchases.confirm  button.save
    	
    	JFXButton confirmBtn=new JFXButton(this.getMessage("button.purchases.confirm"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    	    confirmBtn.setGraphic(layoutIcon);
    	    confirmBtn.getStyleClass().setAll("btn","btn-primary");                     //(2)


    	    //editBtn.getStyleClass().add("control-button");
    	    List buttons =new ArrayList<JFXButton>(Arrays.asList(new JFXButton [] {confirmBtn}))  ;

    	return buttons;
    	
    }

    private List<JFXButton>preparePurchasedInstsButtons(){
    	
    	JFXButton addBtn=new JFXButton(this.getMessage("button.add"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    	    addBtn.setGraphic(layoutIcon);
    	    addBtn.getStyleClass().setAll("btn","btn-primary");                     //(2)


    	    //editBtn.getStyleClass().add("control-button");
    	    List buttons =new ArrayList<JFXButton>(Arrays.asList(new JFXButton [] {addBtn}))  ;

    	return buttons;
    	
    }


    private void fitToAnchorePane(Node node) {
    	
    	
    	AnchorPane.setTopAnchor(node,  0.0); 
    	AnchorPane.setLeftAnchor(node,  0.0); 
    	AnchorPane.setRightAnchor(node,  0.0); 
    	AnchorPane.setBottomAnchor(node,  0.0); 
    	
    	
    	
    }  
	
    


List <PurchasedInvoicesVB> loadPurchasedInvoices(int customerId ) {
	

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
	Map<String,Object> map=new HashMap<String,Object>();
	List invoices=new ArrayList<>();
	List invoicsViewBeans=new ArrayList<>();
	try {
			
		invoices = this.getCustomerService().getCustomerInvoices(getSeason().getId(), customerId,0);
				
		

		for (Object it : invoices) {
			CustomerOrder order=(CustomerOrder) it;
			PurchasedInvoicesVB viewBean=new PurchasedInvoicesVB();
			viewBean.setId(order.getId());
			viewBean.setInvoiceDate(PurchasedInvoicesVB.sdf.format(order.getOrderDate()));
			viewBean.setBuyPrice(order.getBuyPrice());
			viewBean.setGrossWeight(order.getGrossweight());
			viewBean.setNetWeight(order.getNetWeight());
			viewBean.setNolun(order.getNolun());
			viewBean.setTotalAmount(order.getTotalPrice());
			viewBean.setTips(order.getNolun());
			viewBean.setCommision(order.getCommision());
			viewBean.setUnitePrice(order.getUnitePrice());
			viewBean.setVehicelType(order.getVehicleType().getName());

			invoicsViewBeans.add(viewBean);
		
		}
		
		
		
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return invoicsViewBeans;
} 
    
    


private List<Column> prepareInvoiceTabelColumns(){


     List<Column> columns=new ArrayList<Column>();

     Column  c=new Column("id", "id", "int", 0, false);
     columns.add(c);
     c=new Column(this.getMessage("invoice.date"), "invoiceDate", "date", 20, true);
       columns.add(c);
      c=new Column(this.getMessage("label.vehicle.type"), "vehicelType", "String", 12, true);
       columns.add(c);
      c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "double", 10, true);
       columns.add(c);
      c=new Column(this.getMessage("label.netWeight"), "netWeight", "double", 10, true);
       columns.add(c);
      c=new Column(this.getMessage("label.nolun"), "nolun", "double", 7, true);
       columns.add(c);
      c=new Column(this.getMessage("label.total.amount"), "totalAmount", "string", 10, true);
      columns.add(c);
      
      c=new Column(this.getMessage("label.invoice.unitePrice"), "unitePrice", "string", 7, true);
      columns.add(c);
      
      c=new Column(this.getMessage("label.gift"), "tips", "double", 7, true);
      columns.add(c);
      c=new Column(this.getMessage("label.commision"), "commision", "double", 7, true);
        columns.add(c);
     c=new Column(this.getMessage("label.invoice.price"), "buyPrice", "double", 10, true);
        columns.add(c);


return columns;






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
	PurchasedCustomersDataVB item= (PurchasedCustomersDataVB) this.purchasesCustomerDataCustomTable.getTable().getSelectionModel().getSelectedItem();
	int customerId=item.getId();
	List data=null;
	
	List invoices=loadPurchasedInvoices(customerId);
	List orders=loadCustomerOrders(customerId);
	List insts=loadCustomerInsts(customerId);
	this.instalmentsCustomTable.loadTableData(insts);
	this.purchasesInvoiceCustomTable.loadTableData(invoices);
	this.purchasesOrdersCustomTable.loadTableData(orders);
	
}








}
