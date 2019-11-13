package App.com.Customer.purchases.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import App.com.Customer.action.CustomerBaseAction;
import App.com.Customer.purchases.view.beans.PurchasedCustomersDataVB;
import App.com.Customer.purchases.view.beans.PurchasedInstsViewBean;
import App.com.Customer.purchases.view.beans.PurchasedOrdesViewBean;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CustomerPurchasesPresenter extends CustomerBaseAction implements Initializable {

	


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
	private CustomTable<PurchasedOrdesViewBean>purchasesOrdersCustomTable;
	private CustomTable<PurchasedCustomersDataVB>purchasesCustomerDataCustomTable;


	
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
		
		List<JFXButton>purchasesOrdersButtons=preparePurchasedOrdersButtons();
		List<JFXButton>instalmentsButtons=preparePurchasedInstsButtons();

		instalmentsCustomTable=new CustomTable<PurchasedInstsViewBean>(instalmentsColumns, instalmentsButtons, null, null, null, CustomTable.headTableCard, PurchasedInstsViewBean.class);
		purchasesOrdersCustomTable=new CustomTable<PurchasedOrdesViewBean>(purchasesOrdersColumns, purchasesOrdersButtons, null, null, null, CustomTable.headTableCard, PurchasedOrdesViewBean.class);
		purchasesCustomerDataCustomTable=new CustomTable<PurchasedCustomersDataVB>(purchasesCustomerDataColumns, null, null, null, null, CustomTable.tableCard, PurchasedCustomersDataVB.class);

		fitToAnchorePane(instalmentsCustomTable.getCutomTableComponent());
		fitToAnchorePane(purchasesOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(purchasesCustomerDataCustomTable.getCutomTableComponent());

		instalments_loc.getChildren().addAll(instalmentsCustomTable.getCutomTableComponent());
		purchasesOrders_loc.getChildren().addAll(purchasesOrdersCustomTable.getCutomTableComponent());
		purchasedCustomer_loc.getChildren().addAll(purchasesCustomerDataCustomTable.getCutomTableComponent());


		
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
       
        c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "String", 15, true);
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

        c=new Column(this.getMessage("label.money.dueAmount"), "nolun", "dueAmount", 20, true);
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
	
}
