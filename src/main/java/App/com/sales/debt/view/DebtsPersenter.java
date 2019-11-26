package App.com.sales.debt.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import App.com.sales.action.SalesAction;
import App.com.sales.debt.view.beans.PrifSellerOrderVB;
import App.com.sales.debt.view.beans.SellerDebtVB;
import App.com.sales.debt.view.beans.SellerInstalmelmentVB;
import App.com.sales.view.beans.SellerOrderDetailVB;
import App.com.sales.view.beans.SellerOrderVB;
import App.core.Enum.SellerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.UIComponents.customTable.CustomTableActions;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.SellerLoanBag;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class DebtsPersenter extends SalesAction implements CustomTableActions,Initializable{
	   
	
	
	@FXML
    private AnchorPane sellersTable_Loc;

    @FXML
    private AnchorPane sellerInstallments_loc;

    @FXML
    private Label currentebtValue_label;

    @FXML
    private Label initailDebt_label;

    @FXML
    private Label collecteDebt_label;

    @FXML
    private Label intialDebtValue_label;

    @FXML
    private AnchorPane sellerOders_loc;

    @FXML
    private JFXComboBox<ComboBoxItem> sellerType_CB;

    @FXML
    private Label collectedDebtValue_label;

    @FXML
    private JFXTextField sellerName_TF;

    @FXML
    private Label currentDebt_label;

    @FXML
    private AnchorPane orderData_loc;
	    
	    CustomTable<SellerOrderDetailVB> orderDataCustomTable;
	    CustomTable<PrifSellerOrderVB> sellerOrdersCustomTable;
	    CustomTable<SellerInstalmelmentVB> sellerInstallmentsCustomTable;
	    CustomTable<SellerDebtVB> sellersCustomTable;

	    
	    
	    
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		init();
		loadSellersDebts();
		
		

	}
	
	private void init() {
		
		
		List<Column> orderDataColumn=prepareSellerOrderDetailColumns();
		List<Column> prifOrderColumn=preparePrifOrderColumns();
		List<Column> sellerInstallmentColumn=prepareSellerinstallmentColumns();
		List<Column> orderDebtColumn=prepareSellerDeptColumns();
//=========================================================================================================================================
		
		sellerOrdersCustomTable=new CustomTable<PrifSellerOrderVB>(prifOrderColumn, null, null, null, this, CustomTable.tableCard, SellerOrderVB.class);
		sellersCustomTable=new CustomTable<SellerDebtVB>(orderDebtColumn, null, null, null, null, CustomTable.tableCard, SellerOrderVB.class);
		sellerInstallmentsCustomTable=new CustomTable<SellerInstalmelmentVB>(sellerInstallmentColumn, null, null, null, null, CustomTable.tableCard, SellerOrderVB.class);
		orderDataCustomTable=new CustomTable<SellerOrderDetailVB>(orderDataColumn, null, null, null, null, CustomTable.tableCard, SellerOrderVB.class);
//=========================================================================================================================================
		fitToAnchorePane(sellerOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(sellersCustomTable.getCutomTableComponent());
		fitToAnchorePane(sellerInstallmentsCustomTable.getCutomTableComponent());
		fitToAnchorePane(orderDataCustomTable.getCutomTableComponent());
		sellerOrdersCustomTable.getCutomTableComponent().setPrefSize(200, 500);
		sellersCustomTable.getCutomTableComponent().setPrefSize(150, 500);

//=========================================================================================================================================
		orderData_loc.getChildren().addAll(orderDataCustomTable.getCutomTableComponent());
		sellerOders_loc.getChildren().addAll(sellerOrdersCustomTable.getCutomTableComponent());
		sellersTable_Loc.getChildren().addAll(sellersCustomTable.getCutomTableComponent());
		sellerInstallments_loc.getChildren().addAll(sellerInstallmentsCustomTable.getCutomTableComponent());
		
//=========================================================================================================================================
		sellerName_TF.getStyleClass().add("TextField");
		sellerName_TF.setPromptText(this.getMessage("seller.name"));
		
		sellerType_CB.getStyleClass().add("comboBox");
		sellerType_CB.setPromptText(this.getMessage("seller.type"));
		sellerType_CB.getItems().add(new ComboBoxItem(SellerTypeEnum.permenant,this.getMessage("seller.type.permenant")));
		sellerType_CB.getSelectionModel().selectFirst();
		
//=========================================================================================================================================
		
		
	
	}
	
	private void loadSellersDebts() {
		
		try {
			
			List debts=this.getSalesService().getSellersDebts(ApplicationContext.currentUser.getId(),1);
		   List data=new ArrayList();
			for (Iterator iterator = debts.iterator(); iterator.hasNext();) {
				SellerLoanBag bag = (SellerLoanBag) iterator.next();
				SellerDebtVB row=new SellerDebtVB();
				row.setId(bag.getId());
				row.setSellerName(bag.getSeller().getName());

				row.setDueAmount(bag.getCurrentLoan());
				row.setTotalOrdersCost(bag.getDueLoan()+bag.getPriorLoan());
				data.add(row);
				
			}
		this.sellersCustomTable.loadTableData(data);
		
		
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
	
	
	
	
	
    private List<Column> prepareSellerOrderDetailColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
       
     
        Column  c=new Column(this.getMessage("label.product"), "productName", "date", 20, true);
        columns.add(c);
       
      
          c=new Column(this.getMessage("label.count"), "count", "date", 10, true);
        columns.add(c);
       
          c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "date", 15, true);
        columns.add(c);
       
      
        c=new Column(this.getMessage("label.netWeight"), "netWeight", "date", 15, true);
          columns.add(c);
         
          c=new Column(this.getMessage("label.invoice.unitePrice"), "unitePrice", "String", 10, true);
          columns.add(c);
     
          c=new Column(this.getMessage("customer.name"), "customerOrderName", "double", 30, true);
          columns.add(c);
          
          
         
        
   return columns;
   
   
   
   
   
   
   }
 
    private List<Column> prepareSellerinstallmentColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
       
     
        Column  c=new Column(this.getMessage("label.money.amount"), "amount", "double", 25, true);
        columns.add(c);
       
      
          c=new Column(this.getMessage("label.date"), "instDate", "date", 30, true);
        columns.add(c);
       
          c=new Column(this.getMessage("label.notes"), "notes", "String", 45, true);
        columns.add(c);
       
      
    
          
         
        
   return columns;
   
   
   
   
   
   
   }
 
    private List<Column> prepareSellerDeptColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
       
     
        Column  c=new Column(this.getMessage("seller.name"), "sellerName", "String", 40, true);
        columns.add(c);
       
      
          c=new Column(this.getMessage("label.money.dueAmount"), "dueAmount", "double", 30, true);
        columns.add(c);
       
          c=new Column(this.getMessage("label.total.bananaPrice"), "totalOrdersCost", "date", 30, true);
        columns.add(c);
       
  
          
          
         
        
   return columns;
   
   
   
   
   
   
   }
 
    private List<Column> preparePrifOrderColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
       
     
        Column  c=new Column(this.getMessage("label.product"), "orderDate", "date", 50, true);
        columns.add(c);
       
      
          c=new Column(this.getMessage("label.fridage.num"), "fridageName", "String", 25, true);
        columns.add(c);
       
          c=new Column(this.getMessage("label.total.amount"), "totalOrderost", "double", 25, true);
        columns.add(c);
       
      
      
         
        
   return columns;
   
   
   
   
   
   
   }

	@Override
	public void update() {

		// TODO Auto-generated method stub\'\
		
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
		// TODO Auto-generated method stub
		
	}
 
	
}
