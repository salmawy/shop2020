package App.com.selling.view;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;

import App.com.Customer.purchases.view.beans.PurchasedInstsViewBean;
import App.com.Customer.purchases.view.beans.PurchasedOrdersViewBean;
import App.com.selling.action.SellingAction;
import App.com.selling.view.beans.SellerOrderDetailVB;
import App.com.selling.view.beans.SellerOrderVB;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class DailySalesPersenter extends SellingAction implements Initializable {

	
	
	
	@FXML
    private AnchorPane detailPane;

    @FXML
    private AnchorPane root_Pan;

    @FXML
    private HBox headerPane;

    @FXML
    private AnchorPane masterPane;

    @FXML
    private StackPane stackPane;
	

   
    private JFXButton add_btn;
    private JFXButton edit_btn;
    private JFXButton detail_btn;
    private JFXButton prif_btn;

	private CustomTable<SellerOrderVB> sellerOrdersCustomTable;
	private CustomTable<SellerOrderDetailVB> sellerOrderDetailsCustomTable;
    private JFXDatePicker datePicker;

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		

		add_btn=new JFXButton(this.getMessage("button.add"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    	    add_btn.setGraphic(layoutIcon);
    	    add_btn.getStyleClass().setAll("btn","btn-primary");  
		
    	edit_btn=new JFXButton(this.getMessage("button.add"));
         layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
        layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
        edit_btn.setGraphic(layoutIcon);
        edit_btn.getStyleClass().setAll("btn","btn-primary");  
    		
        detail_btn=new JFXButton(this.getMessage("button.add"));
        layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
        layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
        detail_btn.setGraphic(layoutIcon);
        detail_btn.getStyleClass().setAll("btn","btn-primary");  
        		
        prif_btn=new JFXButton(this.getMessage("button.add"));
       layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
       layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
       prif_btn.setGraphic(layoutIcon);
       prif_btn.getStyleClass().setAll("btn","btn-primary");  
            		
		
		
   	datePicker=new JFXDatePicker();

   	datePicker.getEditor().setAlignment(Pos.CENTER);
   	datePicker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
   	datePicker.setConverter(new StringConverter<LocalDate>()
   	{
   	    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

   	    @Override
   	    public String toString(LocalDate localDate)
   	    {
   	        if(localDate==null)
   	            return "";
   	        return dateTimeFormatter.format(localDate);
   	    }

   	    @Override
   	    public LocalDate fromString(String dateString)
   	    {
   	        if(dateString==null || dateString.trim().isEmpty())
   	        {
   	            return null;
   	        }
   	        return LocalDate.parse(dateString,dateTimeFormatter);
   	    }
   	});    
   	

		
		
		
		List <Column>sellerOrderColumns=prepareSellerOrdersColumns();
		List <Column>SellerOrderDetailColumns=prepareSellerOrderDetailColumns();
		sellerOrdersCustomTable=new CustomTable<SellerOrderVB>(sellerOrderColumns, null, null, null, null, CustomTable.tableCard, SellerOrderVB.class);
		sellerOrderDetailsCustomTable=new CustomTable<SellerOrderDetailVB>(SellerOrderDetailColumns, null, null, null, null, CustomTable.tableCard, SellerOrderDetailVB.class);
		fitToAnchorePane(sellerOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(sellerOrderDetailsCustomTable.getCutomTableComponent());
		
		masterPane.getChildren().addAll(sellerOrdersCustomTable.getCutomTableComponent());
	//	detailPane.getChildren().addAll(sellerOrderDetailsCustomTable.getCutomTableComponent());
		
		fillHeaderButtons(1);
		
	}
	
	private void fillHeaderButtons(int mode) {
		
		headerPane.getChildren().clear();
		if(mode==1)
		headerPane.getChildren().addAll(new ArrayList(Arrays.asList(add_btn,edit_btn,detail_btn,datePicker)));
		else {
			headerPane.getChildren().addAll(new ArrayList(Arrays.asList(prif_btn)));

			
		}
		
		
	}
	
    private List<Column> prepareSellerOrdersColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
        Column  c=new Column("chk", "chk", "ck", 5, true);
        columns.add(c);
        
          c=new Column("id", "id", "int", 0, false);
          columns.add(c);
        
        c=new Column(this.getMessage("seller.name"), "sellerName", "date", 30, true);
          columns.add(c);
         
          c=new Column(this.getMessage("seller.type"), "sellerType", "String", 20, true);
          columns.add(c);
     
          c=new Column(this.getMessage("label.total.amount"), "totalAmount", "double", 50, true);
          columns.add(c);
          
          c=new Column(this.getMessage("label.money.paidAmount"), "paidAmount", "double", 50, true);
          columns.add(c);
        
   return columns;
   
   
   
   
   
   
   }
	
	
    private List<Column> prepareSellerOrderDetailColumns(){
        

        List<Column> columns=new ArrayList<Column>();
  
       
     
        Column  c=new Column(this.getMessage("label.product"), "productName", "date", 30, true);
        columns.add(c);
       
      
          c=new Column(this.getMessage("label.count"), "count", "date", 10, true);
        columns.add(c);
       
          c=new Column(this.getMessage("label.grossWeight"), "grossWeight", "date", 10, true);
        columns.add(c);
       
      
        c=new Column(this.getMessage("label.netWeight"), "netWeight", "date", 10, true);
          columns.add(c);
         
          c=new Column(this.getMessage("label.invoice.unitePrice"), "unitePrice", "String", 10, true);
          columns.add(c);
     
          c=new Column(this.getMessage("customer.name"), "customerOrderName", "double", 30, true);
          columns.add(c);
          
         
        
   return columns;
   
   
   
   
   
   
   }
	
	
    private void fitToAnchorePane(Node node) {
    	
    	
    	AnchorPane.setTopAnchor(node,  0.0); 
    	AnchorPane.setLeftAnchor(node,  0.0); 
    	AnchorPane.setRightAnchor(node,  0.0); 
    	AnchorPane.setBottomAnchor(node,  0.0); 
    	
    	
    	
    }  
	
	
	
	
	

}
