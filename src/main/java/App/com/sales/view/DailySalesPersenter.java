package App.com.sales.view;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;

import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.glyphfont.FontAwesome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import App.com.sales.action.SalesAction;
import App.com.sales.view.beans.SellerOrderDetailVB;
import App.com.sales.view.beans.SellerOrderVB;
import App.com.sales.view.dialog.AddSellerOrderDetailView;
import App.com.sales.view.edit.EditSellerOrderView;
import App.core.Enum.SellerTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.UIComponents.customTable.CustomTableActions;
import App.core.beans.IncomeDetail;
import App.core.beans.Seller;
import App.core.beans.SellerOrder;
import App.core.beans.SellerOrderWeight;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.validator.Validator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DailySalesPersenter extends SalesAction implements Initializable, CustomTableActions{

	
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
	
	
	Validator myvaValidator;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		gridPane=new GridPane();
	
		
		
		headerPane.setSpacing(10);
		bookDetailPane=new AnchorPane();
		bookMasterPane=new AnchorPane();
		
		
	//
    	    
    	    
    	edit_btn=new JFXButton(this.getMessage("button.edit"));
          edit_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.EDIT));
        edit_btn.setDisable(true);
        edit_btn.getStyleClass().setAll("btn","btn-primary","btn-sm");  
        edit_btn.setOnAction(e -> {
        	editOrder();
        });	
   
        detail_btn=new JFXButton(this.getMessage("button.detail"));
          detail_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.TABLE));
        detail_btn.getStyleClass().setAll("btn","btn-info","btn-sm");  
        detail_btn.setDisable(true);
        detail_btn.setOnAction(e -> {
        	
            SellerOrderVB orderVB=	(SellerOrderVB) this.sellerOrdersCustomTable.getTable().getSelectionModel().getSelectedItem();
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(bookDetailPane);
            fillHeaderButtons(2);
            loadOrderDetail(orderVB.getId());

           
            
          
            	
            	
            });	
        prif_btn=new JFXButton(this.getMessage("button.prif"));
         prif_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.ARROW_RIGHT));
       prif_btn.getStyleClass().setAll("btn","btn-sm","btn-info");  
            		
       prif_btn.setOnAction(e -> {
       	
        
           fillHeaderButtons(1);
           
           stackPane.getChildren().clear();
           stackPane.getChildren().addAll(bookMasterPane);
           	
           });
		
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
   			loadBookSellerOrders(date);
   			
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
   		
   		
   	});
		
		
		
		List <Column>sellerOrderColumns=prepareSellerOrdersColumns();
		List <Column>SellerOrderDetailColumns=prepareSellerOrderDetailColumns();
		
		List orderDetailControles=prepareOrderDetailcontrolles();
		sellerOrdersCustomTable=new CustomTable<SellerOrderVB>(sellerOrderColumns, null, null, null, this, CustomTable.tableCard, SellerOrderVB.class);
		sellerOrderDetailsCustomTable=new CustomTable<SellerOrderDetailVB>(SellerOrderDetailColumns, null, null, null, null, CustomTable.tableCard, SellerOrderDetailVB.class);
		orderDetail_CT=new CustomTable<SellerOrderDetailVB>(SellerOrderDetailColumns, orderDetailControles, null, null, null, CustomTable.headTableCard, SellerOrderDetailVB.class);

		
		fitToAnchorePane(sellerOrdersCustomTable.getCutomTableComponent());
		fitToAnchorePane(sellerOrderDetailsCustomTable.getCutomTableComponent());
		fitToAnchorePane(orderDetail_CT.getCutomTableComponent());

		bookMasterPane.getChildren().addAll(sellerOrdersCustomTable.getCutomTableComponent());
		bookDetailPane.getChildren().addAll(sellerOrderDetailsCustomTable.getCutomTableComponent());

		stackPane.getChildren().addAll(bookMasterPane);
		
		//===============upper section=====================================
		orderDetail_loc.getChildren().addAll(orderDetail_CT.getCutomTableComponent());
		orderDetail_CT.getCutomTableComponent().setPrefSize(700, 270);
		sellerOrdersCustomTable.getCutomTableComponent().setMaxHeight(200);
		fillHeaderButtons(1);
		
		//================================

		// TODO Auto-generated method stub
		
		sellerType_CB=new JFXComboBox();
		sellerType_CB.getStyleClass().add("comboBox");
		sellerType_CB.getItems().add(new ComboBoxItem(SellerTypeEnum.cash,this.getMessage("seller.type.cash")));
		sellerType_CB.getItems().add(new ComboBoxItem(SellerTypeEnum.permenant,this.getMessage("seller.type.permenant")));
		sellerType_CB.getSelectionModel().selectFirst();
		sellerType_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                    final Number oldvalue, final Number newvalue)
            {
            	
            	ComboBoxItem item=sellerType_CB.getSelectionModel().getSelectedItem();
            	if(item.getValue()==SellerTypeEnum.cash) {
            		
            		name.setDisable(true);
            		name.setText("");
            		
            	}
            	else {
            		
            		name.setDisable(false);
	
            		
            		
            	}
            	
            	
            }
        });
		
		
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
		 totalAmount.setDisable(true);
		 totalAmount.textProperty().addListener((observable, oldValue, newValue) -> {
			    System.out.println("totalAmount changed from " + oldValue + " to " + newValue);
			    myvaValidator=new Validator();
			    if(newValue.length()>0) {
				    myvaValidator.getValidDouble(newValue, 0, Double.MAX_VALUE, "grossWeightValue", true);
				    if(myvaValidator.noException()) {
				    
				    	trackPaidValue();
				    }
				    else {
				    	
				    	newValue=oldValue;
				    	totalAmount.setText(newValue);
				    	
				    }
			    	
			    }
		 });
		 paidAmount=new JFXTextField();
		 paidAmount.getStyleClass().add("TextField");
		 paidAmount.textProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("totalAmount changed from " + oldValue + " to " + newValue);
			    myvaValidator=new Validator();
			    if(newValue.length()>0) {
				    myvaValidator.getValidDouble(newValue, 0, Double.MAX_VALUE, "grossWeightValue", true);
				    if(myvaValidator.noException()) {
				    
				    	trackRestValue();
				    }
				    else {
				    	
				    	newValue=oldValue;
				    	paidAmount.setText(newValue);
				    	
				    }
			    	
			    }
		 });
		 
		 restAmount=new JFXTextField();
		 restAmount.getStyleClass().add("TextField");
		 restAmount.setDisable(true);

		 saveBtn.setText(this.getMessage("button.save"));
  		    saveBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.SAVE));
		    saveBtn.getStyleClass().setAll("btn","btn-primary");  
		    saveBtn.setOnAction(e -> {
		       	
		        
		       if(validateInputData()) {
		    	   
		    	   
		    	   try {
		    	   
		    	   double paidAmount_=(paidAmount.getText().isEmpty())?0.0:Double.parseDouble(paidAmount.getText());
		    	   int type=sellerType_CB.getSelectionModel().getSelectedItem().getValue();
		    	   Seller seller=new Seller();
		    	   seller.setName(name.getText());
		    	   seller.setAddress(address.getText());
		    	   seller.setPhone(phone.getText());
		    	   seller.setTypeId(type);
		    	   
		    	   SellerOrder order=new SellerOrder();
		    	   order.setOrderDate(new Date());
		    	   order.setFridageId(this.getFridage().getId());
		    	   order.setSeasonId(getSeason().getId());
		    	   order.setTotalCost(Double.parseDouble(totalAmount.getText()));
		    	   List orderDetails=orderDetail_CT.getTable().getItems();
		    	   Set<SellerOrderWeight>orerDetail=new HashSet<SellerOrderWeight>();
		    	   for (Iterator iterator = orderDetails.iterator(); iterator.hasNext();) {
					SellerOrderDetailVB row = (SellerOrderDetailVB) iterator.next();
					SellerOrderWeight temp=new SellerOrderWeight();
					temp.setAmount(row.getAmount());
					temp.setCustomerOrderId(row.getCustomerOrderId());
					temp.setGrossQuantity(row.getGrossWeight());
					temp.setNetQuantity(row.getNetWeight());
					temp.setPackageNumber(row.getCount());
					temp.setProductId(row.getProductId());
					temp.setUnitePrice(row.getUnitePrice());
					orerDetail.add(temp);
					
				}
		    	   
		    	   order.setOrderWeights(orerDetail);
		    	   this.getSalesService().saveSellerOrder(seller, order, paidAmount_);
		    	   
		    	   intiateAddOrderPage();
		    	   alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.save"));
		    	   
		    	   }catch (Exception ex) {
			    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));

			
				}
		    	   
		    	   
		    	   
		    	   
		       }
		           	
		           });
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
		    
		    
		  	


			loadLastSales();
	
		
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
  
		/*
		 *  c=new Column("chk", "chk", "ck", 5, true); columns.add(c);
		 */
        
        Column   c=new Column("id", "id", "int", 0, false);
          columns.add(c);
        
        c=new Column(this.getMessage("seller.name"), "sellerName", "date", 30, true);
          columns.add(c);
         
          c=new Column(this.getMessage("seller.type"), "sellerType", "String", 20, true);
          columns.add(c);
     
          c=new Column(this.getMessage("label.total.amount"), "totalAmount", "double", 25, true);
          columns.add(c);
          
          c=new Column(this.getMessage("label.money.paidAmount"), "paidAmount", "double", 30, true);
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
      	    addBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.PLUS));
    	    addBtn.getStyleClass().setAll("btn","btn-info","btn-sm");                     //(2)
    	    addBtn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
 	    	   public void handle(MouseEvent event) { 
 	    		      System.out.println("add has been clicked"); 
 	    		      addOrderDetail(); 
 	    		   } 
 	    		}));
    	    
    	    JFXButton deleteBtn=new JFXButton(this.getMessage("button.delete"));
          	    deleteBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.TRASH));
        	    deleteBtn.getStyleClass().setAll("btn","btn-danger","btn-sm");                     //(2)
        	    deleteBtn.setOnAction(e -> {
        	        SellerOrderDetailVB selectedItem =(SellerOrderDetailVB) orderDetail_CT.getTable().getSelectionModel().getSelectedItem();
        	        orderDetail_CT.getTable().getItems().remove(selectedItem);
        	   	 updateTotalAmountValue(selectedItem.getAmount(), -1);

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
	   boolean save=(m.get("save")!=null)? (boolean) m.get("save"):false;
	   if(save ){
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
	 updateTotalAmountValue(viewBean.getAmount(), 1);
	   }

   }

   
   
   private void updateTotalAmountValue(double value,int mode) {
	//mode 1 => addition else subtract
	double  oldValue=(totalAmount.getText()!=null&&totalAmount.getText()!=""&&totalAmount.getText().length()>0)?Double.parseDouble(totalAmount.getText()):0.0;

	double newValue=(mode==1)?oldValue+value:oldValue-value;
	
	totalAmount.setText(String.valueOf(newValue));
	
	
	
	
	
}



@SuppressWarnings("unchecked")
private void loadOrderDetail(int orderId) {
	
	SellerOrder order;
	try {
		order = (SellerOrder) this.getBaseRetrievalService().getBean(SellerOrder.class, orderId);
	
	List data=new ArrayList();
	
	for (Iterator iterator = order.getOrderWeights().iterator(); iterator.hasNext();) {
		SellerOrderWeight weight = (SellerOrderWeight) iterator.next();
		SellerOrderDetailVB viewBean=new SellerOrderDetailVB();
		viewBean.setAmount(weight.getAmount());
		viewBean.setCount(weight.getPackageNumber());
		viewBean.setCustomerOrderId(weight.getCustomerOrderId());
		viewBean.setCustomerOrderName(weight.getCustomerOrder().getOrderTag());
		viewBean.setGrossWeight(weight.getGrossQuantity());
		viewBean.setNetWeight(weight.getNetQuantity());
		viewBean.setProductId(weight.getProductId());
		viewBean.setProductName(weight.getProduct().getName());
		viewBean.setSellerWeightId(weight.getId());
		viewBean.setUnitePrice(weight.getUnitePrice());
		data.add(viewBean);
	}
this.sellerOrderDetailsCustomTable.loadTableData(data);
	
	} catch (DataBaseException | InvalidReferenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
	



private void trackPaidValue() {
    if (!totalAmount.getText().isEmpty()) {
        double total = Double.parseDouble(totalAmount.getText());

        if (total != 0) {
            int index = sellerType_CB.getSelectionModel().getSelectedItem().getValue();
            if (index == 1) {
                paidAmount.setText(paidAmount.getText());

            }

        }
    } else {
    	paidAmount.setText("");

    }

}





private void	trackRestValue(){

String total = totalAmount.getText();
String paid = paidAmount.getText();
double x = 0;
if (total != null && paid != null) {
    if (!total.isEmpty() && !paid.isEmpty()) {
        double paidvalue = Double.parseDouble(paid);
        double totalvalue = Double.parseDouble(total);
        x = totalvalue - paidvalue;

    }
    if (!total.isEmpty() && paid.isEmpty()) {
        x = Double.parseDouble(total);
    }

}

restAmount.setText(String.valueOf(x));
}



boolean validateInputData() {
    boolean valid = false;
	 Alert a = new Alert(AlertType.ERROR);
	 a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
a.setTitle(this.getMessage("msg.err"));
		a.setHeaderText(this.getMessage("msg.err"));
    int index = sellerType_CB.getSelectionModel().getSelectedIndex();
    double rest = (restAmount.getText().isEmpty())?0.0:Double.parseDouble(restAmount.getText());

    if (totalAmount.getText().isEmpty() || totalAmount.getText().equals("0.0")) {

			
			 a.setContentText(this.getMessage("msg.err.required.order.data")); 
			              a.show(); 

    }       


    else  if (name.getText().isEmpty() && index == 1) {

		
		 a.setContentText(this.getMessage("msg.err.required.sellerName")); 
		  
        // show the dialog 
        a.show(); 
        return false;
    }
    else  if (rest < 0) {
        
        a.setContentText(this.getMessage("msg.err.input.amount.greather")+totalAmount.getText()); 
		  
        // show the dialog 
        a.show(); 
           
	   return false;

    } 
        return true;
   

}
  private void alert(AlertType alertType,String title,String headerText,String message) {
		 Alert a = new Alert(alertType);
		 a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		 a.setTitle(title );
		 a.setHeaderText(headerText);
		 a.setContentText(message); 
         a.show(); 
	  
  }

public void intiateAddOrderPage() {
    this.address.setText("");
    this.paidAmount.setText("");
    this.phone.setText("");
    this.totalAmount.setText("");
    this.restAmount.setText("");
    this.name.setText("");

   orderDetail_CT.getTable().getItems().clear();
   
}



@Override
public void rowSelected() {
	
	this.detail_btn.setDisable(false);
	this.edit_btn.setDisable(false);
	
	
}
private void loadLastSales() {
	
	try {
		Date date= (Date) this.getSalesService().aggregate("SellerOrder", "max", "orderDate", null);
		
			loadBookSellerOrders(date);
			//this.datePicker.set
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			LocalDate local=  LocalDate.of( c.get(c.YEAR), c.get(c.MONTH), c.get(c.DAY_OF_MONTH));	
			this.datePicker.setValue(local);
	} catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	
	
}




private void loadBookSellerOrders(Date date) {
	
	
	

    
	sellerOrdersCustomTable.getTable().getItems().clear();

	
	
List data=new ArrayList();
		try {
		List result=this.getSalesService().getSellersOrders(date);
		
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			SellerOrder order = (SellerOrder) iterator.next();
			SellerOrderVB viewBean=new SellerOrderVB();
			double paidAmount=getPaidAmount(order.getId());
	
			String sellerTypeText=(order.getSeller().getTypeId()==SellerTypeEnum.cash)?this.getMessage("seller.type.cash"):this.getMessage("seller.type.permenant");
			viewBean.setId(order.getId());
			viewBean.setSellerName(order.getSeller().getName());
			viewBean.setPaidAmount(paidAmount);
			viewBean.setSellerType(sellerTypeText);
			viewBean.setTotalAmount(order.getTotalCost());
			data.add(viewBean);
		}
		this.sellerOrdersCustomTable.loadTableData(data);
		
	} catch (EmptyResultSetException e1) {
		// TODO Auto-generated catch block
		alert(AlertType.WARNING, "", "", this.getMessage("msg.warning.noData"));
	} catch (DataBaseException e1) {
		// TODO Auto-generated catch block
		alert(AlertType.ERROR, "", "", this.getMessage("msg.err.cannot.load.data"));
	}

}



private double getPaidAmount(int orderId) {

	
	Map<String,Object>map=new HashMap<String,Object>();
	map.put("sellerOrderId", orderId);
	try {
		IncomeDetail income=	(IncomeDetail)this.getBaseService().findAllBeans(IncomeDetail.class, map, null).get(0);
	return income.getAmount();
	} catch (DataBaseException e) {
		// TODO Auto-generated catch block
 	} catch (EmptyResultSetException e) {
		// TODO Auto-generated catch block
 	}
	
	
	
	
	
	return 0.0;
	
	
	
	
}




private void editOrder() {
	
	
    SellerOrderVB orderVB=	(SellerOrderVB) this.sellerOrdersCustomTable.getTable().getSelectionModel().getSelectedItem();

	this.request=new HashMap<String,Object>();
	
	request.put("orderId", orderVB.getId());
	
	EditSellerOrderView form=new EditSellerOrderView();
	URL u=	 getClass().getClassLoader().getResource("appResources/custom.css");

	Scene scene1= new Scene(form.getView(), 1100, 500);
	Stage popupwindow=new Stage();
	popupwindow.setResizable(true);
    String css =u.toExternalForm();
	scene1.getStylesheets().addAll(css); 
	popupwindow.initModality(Modality.APPLICATION_MODAL);
	popupwindow.setTitle(this.getMessage("msg.info.edit.sellerOrder"));
	      
	popupwindow.setScene(scene1);
	popupwindow.setOnHiding( ev -> {
		

		System.out.println("window closes");
		
    
		
		
	});
	      
	popupwindow.showAndWait();
	
	
}

@Override
public void rowSelected(Object o) {
	// TODO Auto-generated method stub
	
}









}