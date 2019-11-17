package App.com.selling.view;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import App.com.selling.action.SellingAction;
import App.com.selling.view.beans.SellerOrderDetailVB;
import App.com.selling.view.beans.SellerOrderVB;
import App.com.selling.view.dialog.AddSellerOrderDetailView;
import App.core.Enum.SellerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.beans.SellerOrder;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DailySalesPersenter extends SellingAction implements Initializable {

	
	    @FXML
	    private AnchorPane gridPane_loc;
	
	    private AnchorPane bookDetailPane;

	    @FXML
	    private AnchorPane root_Pan;

	    @FXML
	    private AnchorPane orderDetail_loc;

	    @FXML
	    private HBox headerPane;


	    private AnchorPane bookMasterPane;

	    @FXML
	    private StackPane stackPane;

	    @FXML
	    private HBox buttonPane;
	    
	    @FXML
	    private   JFXButton saveBtn;
	    
	    
	    
	    private GridPane gridPane;

   
    private JFXButton edit_btn;
    private JFXButton detail_btn;
    private JFXButton prif_btn;

	private CustomTable<SellerOrderVB> sellerOrdersCustomTable;
	private CustomTable<SellerOrderDetailVB> sellerOrderDetailsCustomTable;
    private JFXDatePicker datePicker;

    private   JFXTextField name;
    private   JFXTextField phone;
    private   JFXTextField address;
    private   JFXTextField totalAmount;
    private   JFXTextField paidAmount;
    private   JFXTextField restAmount;

    private  JFXComboBox<ComboBoxItem> sellerType_CB;
    private CustomTable <SellerOrderDetailVB> orderDetail_CT;

    private Label sellerTypeLabel=new Label(this.getMessage("seller.type"));

    private Label nameLabel=new Label(this.getMessage("seller.name"));
	
    private Label phoneLabel=new Label(this.getMessage("customer.phone"));
	
    private Label addressLabel=new Label(this.getMessage("customer.address"));
	
    private  Label totalLabel=new Label(this.getMessage("label.total"));
	
    private  Label paidAmountLabel=new Label(this.getMessage("label.money.paidAmount"));
	
    private  Label restLabel=new Label(this.getMessage("label.money.rest"));
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		gridPane=new GridPane();
	
		
		
		headerPane.setSpacing(10);
		bookDetailPane=new AnchorPane();
		bookMasterPane=new AnchorPane();
		
		
	
    	    
    	    
    	edit_btn=new JFXButton(this.getMessage("button.edit"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.EDIT);
        layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
        edit_btn.setGraphic(layoutIcon);
        edit_btn.getStyleClass().setAll("btn","btn-primary","btn-sm");  
    		
        detail_btn=new JFXButton(this.getMessage("button.detail"));
        layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
        layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
        detail_btn.setGraphic(layoutIcon);
        detail_btn.getStyleClass().setAll("btn","btn-info","btn-sm");  
        		
        prif_btn=new JFXButton(this.getMessage("button.prif"));
       layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TABLE);
       layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
       prif_btn.setGraphic(layoutIcon);
       prif_btn.getStyleClass().setAll("btn","btn-sm","btn-info");  
            		
		
		
   	datePicker=new JFXDatePicker();
   	datePicker.setPadding(new Insets(0, 0, 0, 100));

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

   	datePicker.setOnAction(e -> {
       
       
   	
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

   		Date date=new Date();
   		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
   		try {
   			date = sdf.parse(datePicker.getValue().toString());
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
List data=new ArrayList();
   		try {
			List result=this.getSalesService().getSellersOrders(date);
			
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				SellerOrder order = (SellerOrder) iterator.next();
				SellerOrderVB viewBean=new SellerOrderVB();
				viewBean.setId(order.getId());
				viewBean.setSellerName(order.getSeller().getName());
				viewBean.setPaidAmount(0.0);
				viewBean.setTotalAmount(order.getTotalCost());
				data.add(viewBean);
			}
			
			this.sellerOrdersCustomTable.loadTableData(data);
			
		} catch (EmptyResultSetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataBaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

   		
   		
    });
		
		
		
		List <Column>sellerOrderColumns=prepareSellerOrdersColumns();
		List <Column>SellerOrderDetailColumns=prepareSellerOrderDetailColumns();
		
		List orderDetailControles=prepareOrderDetailcontrolles();
		sellerOrdersCustomTable=new CustomTable<SellerOrderVB>(sellerOrderColumns, null, null, null, null, CustomTable.tableCard, SellerOrderVB.class);
		sellerOrderDetailsCustomTable=new CustomTable<SellerOrderDetailVB>(SellerOrderDetailColumns, null, null, null, null, CustomTable.tableCard, SellerOrderDetailVB.class);
		orderDetail_CT=new CustomTable<SellerOrderDetailVB>(SellerOrderDetailColumns, orderDetailControles, null, null, null, CustomTable.headTableCard, SellerOrderDetailVB.class);

		
		fitToAnchorePane(sellerOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(sellerOrderDetailsCustomTable.getCutomTableComponent());
		fitToAnchorePane(orderDetail_CT.getCutomTableComponent());

		bookMasterPane.getChildren().addAll(sellerOrdersCustomTable.getCutomTableComponent());
		stackPane.getChildren().addAll(bookMasterPane);
		orderDetail_loc.getChildren().addAll(orderDetail_CT.getCutomTableComponent());
		orderDetail_CT.getCutomTableComponent().setPrefSize(700, 270);
		sellerOrdersCustomTable.getCutomTableComponent().setPrefHeight(300);
		fillHeaderButtons(1);
		
		//================================

		// TODO Auto-generated method stub
		
		sellerType_CB=new JFXComboBox();
		sellerType_CB.getStyleClass().add("comboBox");
		sellerType_CB.getItems().add(new ComboBoxItem(SellerTypeEnum.cash,this.getMessage("seller.type.cash")));
		sellerType_CB.getItems().add(new ComboBoxItem(SellerTypeEnum.permenant,this.getMessage("seller.type.permenant")));
		 name=new JFXTextField();
		 name.getStyleClass().add("TextField");
		 
		 TextFields.bindAutoCompletion(name, t-> {

	            return this.getSalesService().getSuggestedSellerName( t.getUserText());

	        });
		 
		 
		 phone=new JFXTextField();
		 phone.getStyleClass().add("TextField");
		
		 
		 address=new JFXTextField();
		 address.getStyleClass().add("TextField");
		 
		 totalAmount=new JFXTextField();
		 totalAmount.getStyleClass().add("TextField");
		 
		 paidAmount=new JFXTextField();
		 paidAmount.getStyleClass().add("TextField");
		 
		 
		 restAmount=new JFXTextField();
		 restAmount.getStyleClass().add("TextField");
		 
		 saveBtn.setText(this.getMessage("button.save"));
			 layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.SAVE);
		    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
		    saveBtn.setGraphic(layoutIcon);
		    saveBtn.getStyleClass().setAll("btn","btn-primary");  
		   // buttonPane.getChildren().addAll(saveBtn);
		    
		    
 //====================================================================================================
		    gridPane.setCenterShape(true);
		    gridPane.setAlignment(gridPane.getAlignment().CENTER);
		    
		    gridPane.add(sellerTypeLabel, 0, 0);
		    gridPane.add(sellerType_CB, 1, 0);
		    
		    
		    gridPane.add(nameLabel, 0, 1);
		    gridPane.add(name, 1, 1);
		    
		    gridPane.add(phoneLabel, 0, 2);
		    gridPane.add(phone, 1, 2);
		    
		    
		    gridPane.add(addressLabel, 0, 3);
		    gridPane.add(address, 1, 3);
		    
		    gridPane.add(totalLabel, 0, 4);
		    gridPane.add(totalAmount, 1, 4);
		    
		    gridPane.add(paidAmountLabel, 0, 5);
		    gridPane.add(paidAmount, 1, 5);
		    
		    
		    gridPane.add(restLabel, 0, 6);
		    gridPane.add(restAmount, 1, 6);
		    
		    
		  //  gridPane.add(sellerTypeLabel, 0, 7);
		    
			gridPane.setVgap(5);
			gridPane_loc.getChildren().clear();
			fitToAnchorePane(gridPane);
			gridPane_loc.getChildren().setAll(gridPane);
		    
		    
		  	


		
	
		
	}
	
	private void fillHeaderButtons(int mode) {
		
		headerPane.getChildren().clear();
		if(mode==1)
		headerPane.getChildren().addAll(new ArrayList(Arrays.asList(edit_btn,detail_btn,datePicker)));
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
     
          c=new Column(this.getMessage("label.total.amount"), "totalAmount", "double", 25, true);
          columns.add(c);
          
          c=new Column(this.getMessage("label.money.paidAmount"), "paidAmount", "double", 25, true);
          columns.add(c);
        
   return columns;
   
   
   
   
   
   
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
 
	
    private void fitToAnchorePane(Node node) {
    	
    	
    	AnchorPane.setTopAnchor(node,  0.0); 
    	AnchorPane.setLeftAnchor(node,  0.0); 
    	AnchorPane.setRightAnchor(node,  0.0); 
    	AnchorPane.setBottomAnchor(node,  0.0); 
    	
    	
    	
    }  
	
	
   private List<JFXButton>prepareOrderDetailcontrolles(){
    	
    	
    	JFXButton addBtn=new JFXButton(this.getMessage("button.add"));
    	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.PLUS);
    	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    	    addBtn.setGraphic(layoutIcon);
    	    addBtn.getStyleClass().setAll("btn","btn-info","btn-sm");                     //(2)
    	    addBtn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
 	    	   public void handle(MouseEvent event) { 
 	    		      System.out.println("add has been clicked"); 
 	    		      addOrderDetail(); 
 	    		   } 
 	    		}));
    	    
    	    JFXButton deleteBtn=new JFXButton(this.getMessage("button.delete"));
        	 layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.TRASH);
        	    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
        	    deleteBtn.setGraphic(layoutIcon);
        	    deleteBtn.getStyleClass().setAll("btn","btn-danger","btn-sm");                     //(2)
        	    deleteBtn.setOnAction(e -> {
        	        SellerOrderDetailVB selectedItem =(SellerOrderDetailVB) orderDetail_CT.getTable().getSelectionModel().getSelectedItem();
        	        orderDetail_CT.getTable().getItems().remove(selectedItem);
        	    });


    	    List buttons =new ArrayList<JFXButton>(Arrays.asList(new JFXButton [] {addBtn,deleteBtn}))  ;

    	return buttons;
    	
    }


   private void addOrderDetail() {
		
		AddSellerOrderDetailView form=new AddSellerOrderDetailView();
		URL u=	 getClass().getClassLoader().getResource("appResources/custom.css");

		Scene scene1= new Scene(form.getView(), 350, 420);
		Stage popupwindow=new Stage();
		popupwindow.setResizable(false);
	    String css =u.toExternalForm();
		scene1.getStylesheets().addAll(css); 
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("This is a pop up window");
		      
		popupwindow.setScene(scene1);
	popupwindow.setOnHiding( ev -> {
			

			System.out.println("window closes");
	        addOrderDetailRow();
	    
			
			
		});
		      
		popupwindow.showAndWait();

		
	
	
		
		
		
		
		
		
		
		
	}

   private void addOrderDetailRow() {
	   
	  
	   Map m =this.getOrderDataMap();
	   SellerOrderDetailVB viewBean=new SellerOrderDetailVB();
	  
	   viewBean.setAmount(Double.parseDouble(String.valueOf(m.get("amount")))  );
	   viewBean.setCount(Integer.parseInt(String.valueOf( m.get("count")))  );
	   viewBean.setCustomerOrderName(String.valueOf( m.get("customerOrderName")));
	   viewBean.setGrossWeight(Double.parseDouble(String.valueOf(m.get("grossWeight") ))  );
	   viewBean.setNetWeight(Double.parseDouble(String.valueOf( m.get("netWeight")))  );
	   viewBean.setProductName(String.valueOf( m.get("productName"))  );
	   viewBean.setUnitePrice(Double.parseDouble(String.valueOf(m.get("unitePrice")))  );
	   viewBean.setCustomerOrderId(Integer.parseInt(String.valueOf(m.get("customerOrderId")))   );
	   viewBean.setProductId(Integer.parseInt(String.valueOf(m.get("productId") ))    );
	   
	 this.orderDetail_CT.getTable().getItems().add(viewBean);

   }


	

}
