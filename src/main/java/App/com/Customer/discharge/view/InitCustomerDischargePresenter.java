/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.com.Customer.discharge.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.glyphfont.FontAwesome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import App.com.Customer.action.CustomerBaseAction;
import App.com.Customer.discharge.view.beans.CustomerViewBean;
import App.com.Customer.discharge.view.edit.EditCustomerOrderView;
import App.core.Enum.CustomerTypeEnum;
import App.core.Enum.VechileTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Product;
import App.core.beans.Store;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.validator.Validator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author ahmed
 */
public class InitCustomerDischargePresenter extends CustomerBaseAction  implements Initializable {
    
	Logger logger = Logger.getLogger(this.getClass().getName());	

      @FXML
    private Label datePicker_Label;

    @FXML
    private FlowPane headLoc;

    @FXML
    private AnchorPane gridLoc;
    
    @FXML
    private AnchorPane inputForm_loc;
    
    @FXML
    private JFXButton saveBtn;
    private GridPane gridPane;

    private CustomTable<CustomerViewBean> gride;
    private JFXDatePicker datePicker;
    private   JFXTextField name;
    private   JFXTextField phone;
    private   JFXTextField address;
    private   JFXTextField grossWeight;
    private   JFXTextField count;
    private   JFXTextField nolun;
    private   JFXTextField gift;
    private   JFXTextField code;
    private   JFXTextArea notes;
    private Validator myValidator;
    private  JFXComboBox<ComboBoxItem> productTyp;
    private  JFXComboBox<ComboBoxItem> storeLocation;
    private   JFXComboBox<ComboBoxItem> vehicleTypeBox;
    private JFXComboBox<ComboBoxItem> cutomerBox;
    private Image errIcon;
    private  JFXDatePicker bookDatePicker;
   
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	errIcon=loadImage("icons/error-icon.png");
  	  logger.log(Level.INFO,"============================================================================================================");

    	
   	init();
   	getBook();
  }
    
    
    
    private void init()  {
    	
    	
    	
		gridPane=new GridPane();
		gridPane.setCenterShape(true);
	    gridPane.setAlignment(gridPane.getAlignment().BASELINE_RIGHT);
		gridPane.setVgap(20);
   
		  List<Column> columns=prepareTabelColumns(); 
		  List<JFXButton> buttons=prepareheaderNodes();
		//  List<CustomerViewBean>data=loadData(new Date());
		  gride=new CustomTable<CustomerViewBean>(columns,buttons,null,null,null,CustomTable.headTableCard,CustomerViewBean.class); 
		  gride.getCutomTableComponent().setPrefHeight(400);
   
		  fitToAnchorePane(gride.getCutomTableComponent());
		
		  gridLoc.getChildren().setAll(gride.getCutomTableComponent());
		  initInputGridPane();
		  saveBtn.setOnAction(e -> {
			  
			  saveCustomerOrder();
			  
			  
			  
		  });
    	
    }
private void initInputGridPane() {
	

	Label typeLabel=new Label(this.getMessage("label.customer.Type"));

	Label nameLabel=new Label(this.getMessage("customer.name"));
	
	Label phoneLabel=new Label(this.getMessage("customer.phone"));
	
	Label addressLabel=new Label(this.getMessage("customer.address"));
	
	Label productIdLabel=new Label(this.getMessage("label.product"));
	
	Label grossWeightLabel=new Label(this.getMessage("label.grossWeight"));
	
	Label countLabel=new Label(this.getMessage("label.count.sabait"));
	
	Label storeIdLabel=new Label(this.getMessage("label.store.name"));
	
	Label giftLabel=new Label(this.getMessage("label.gift"));
	
	Label vehicelTypeLabel=new Label(this.getMessage("label.vehicle.type"));
	
	Label notesLabel=new Label(this.getMessage("label.notes"));
	
	Label tageLabel=new Label(this.getMessage("label.code"));
	
	Label nolunLabel=new Label(this.getMessage("label.nolun"));
	Label dateLabel=new Label(this.getMessage("label.date"));


cutomerBox=new JFXComboBox();
cutomerBox.getStyleClass().add("comboBox");

cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.kareem,this.getMessage("customer.type.karrem")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.mahmed,this.getMessage("customer.type.mahmed")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.normal,this.getMessage("customer.type.normal")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.purchases,this.getMessage("customer.type.purchaes")));
cutomerBox.getSelectionModel().selectFirst();

 vehicleTypeBox=new JFXComboBox();
vehicleTypeBox.getStyleClass().add("comboBox");

vehicleTypeBox.getItems().add(new ComboBoxItem(VechileTypeEnum.van,this.getMessage("label.vehicle.van")));
vehicleTypeBox.getItems().add(new ComboBoxItem(VechileTypeEnum.car,this.getMessage("label.vehicle.car")));
vehicleTypeBox.getItems().add(new ComboBoxItem(VechileTypeEnum.HVAN,this.getMessage("label.vehicle.HVan")));

vehicleTypeBox.getSelectionModel().selectFirst();

//============================================================================================================
	datePicker=new JFXDatePicker();

	datePicker.getEditor().setAlignment(Pos.CENTER);
	datePicker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	datePicker.setConverter(new StringConverter<LocalDate>()
	{
	    private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");

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

	
	
	
      this.datePicker.setValue(getBaseService().convertToLocalDateViaMilisecond(new Date()));

	//=============================name ===============================================================================

	 name=new JFXTextField();
	 name.getStyleClass().add("TextField");
	 RequiredFieldValidator  nameValidator=new RequiredFieldValidator();
	 nameValidator.setMessage(this.getMessage("msg.err.required.value"));
	 nameValidator.setIcon(new ImageView(errIcon));
	
	 name.getValidators().add(nameValidator);
	 TextFields.bindAutoCompletion(name, t-> {
		 	int customerTypeId=cutomerBox.getSelectionModel().getSelectedItem().getValue();
         return this.getCustomerService().getSuggestedCustomerName( t.getUserText(),customerTypeId) ;
         
         
         

     });
	 name.focusedProperty().addListener(new ChangeListener<Boolean>() {

			

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					name.validate();
				}	
				
				
			}
		});
		//============================phone================================================================================
	 
	 phone=new JFXTextField();
	 phone.getStyleClass().add("jfx-text-field");
	
	 
	 address=new JFXTextField();
	 address.getStyleClass().add("jfx-text-field");
	//*********************************gross weight****************************************************************************************************** 
	 grossWeight=new JFXTextField();
	 grossWeight.getStyleClass().add("TextField");
	 RequiredFieldValidator  grossWeightValidator=new RequiredFieldValidator();
	 grossWeightValidator.setMessage(this.getMessage("msg.err.required.value"));
	 grossWeightValidator.setIcon(new ImageView(errIcon));

	 grossWeight.getValidators().add(grossWeightValidator);
	 grossWeight.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("grossWeight changed from " + oldValue + " to " + newValue);
		    myValidator=new Validator();
		    if(newValue.length()>0) {
			    myValidator.getValidDouble(newValue, 0, Double.MAX_VALUE, "grossWeightValue", true);
			    if(!myValidator.noException()) {
			    
			    	newValue=oldValue;
			    	grossWeight.setText(newValue);
			    }
			  
		    	
		    }
		
			
		});
		grossWeight.focusedProperty().addListener(new ChangeListener<Boolean>() {

		

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					grossWeight.validate();
					
				}				
			}
		});
//*********************************count****************************************************************************************************** 

	 count=new JFXTextField();
	 count.getStyleClass().add("TextField");
	 count.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("count changed from " + oldValue + " to " + newValue);
		    myValidator=new Validator();
		    if(newValue.length()>0) {
			    myValidator.getValidInt(newValue, 0, Integer.MAX_VALUE, "count", false);
			    if(!myValidator.noException()) {
			    
			    	newValue=oldValue;
			    	count.setText(newValue);
			    }
			  
		    	
		    }
		
			
		});
	//*********************************nolun****************************************************************************************************** 
	 
	 nolun=new JFXTextField();
	 nolun.getStyleClass().add("TextField");
	 RequiredFieldValidator  noluntValidator=new RequiredFieldValidator();
	 noluntValidator.setMessage(this.getMessage("msg.err.required.value"));
	 noluntValidator.setIcon(new ImageView(errIcon));

	 nolun.getValidators().add(noluntValidator);
	 nolun.focusedProperty().addListener(new ChangeListener<Boolean>() {

			

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					nolun.validate();
					
				}				
			}
		});
	 
	 nolun.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("nolun changed from " + oldValue + " to " + newValue);
		    myValidator=new Validator();
		    if(newValue.length()>0) {
			    myValidator.getValidDouble(newValue, 0, Double.MAX_VALUE, "grossWeightValue", true);
			    if(!myValidator.noException()) {
			    
			    	newValue=oldValue;
			    	nolun.setText(newValue);
			    }
			  
		    	
		    }
		
			
		});
	 
		//*********************************tips****************************************************************************************************** 

	 gift=new JFXTextField();
	 gift.getStyleClass().add("TextField");
	 RequiredFieldValidator giftValidator=new RequiredFieldValidator();
	 giftValidator.setMessage(this.getMessage("msg.err.required.value"));
	 giftValidator.setIcon(new ImageView(errIcon));

	 
	 gift.getValidators().add(giftValidator);
	 gift.textProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("gift changed from " + oldValue + " to " + newValue);
		    myValidator=new Validator();
		    if(newValue.length()>0) {
			    myValidator.getValidDouble(newValue, 0, Double.MAX_VALUE, "grossWeightValue", true);
			    if(!myValidator.noException()) {
			    
			    	newValue=oldValue;
			    	gift.setText(newValue);
			    }
			  
		    	
		    }
		
			
		});
	 gift.focusedProperty().addListener(new ChangeListener<Boolean>() {

			

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					gift.validate();
					
				}				
			}
		});
		//*********************************code****************************************************************************************************** 

	 code=new JFXTextField();
	//code.setFocusColor(Color.valueOf("#365fda"));
	// code.setUnFocusColor(Color.valueOf("#090e5b"));

	 code.getStyleClass().add("jfx-text-field");
//*********************************notes****************************************************************************************************** 

	 notes=new JFXTextArea();
	 notes.getStyleClass().add("textArea");
	//*********************************productTyp****************************************************************************************************** 

	productTyp=new <ComboBoxItem> JFXComboBox();
	productTyp.getStyleClass().add("comboBox");
	//*********************************save btn****************************************************************************************************** 

	saveBtn.setText(this.getMessage("button.save"));
 	saveBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.PLUS));
    saveBtn.setStyle("-fx-margin:100px");
    saveBtn.getStyleClass().setAll("btn","btn-primary");  
	
	//*********************************storeLocation****************************************************************************************************** 

	
	
	storeLocation=new <ComboBoxItem> JFXComboBox();
	productTyp.getStyleClass().add("comboBox");

	storeLocation.getStyleClass().add("comboBox");
try {
	List products=this.getBaseService().findAllBeans(Product.class);
	for (Object p : products) {
		Product prod=(Product) p;
		productTyp.getItems().add(new ComboBoxItem(prod.getId(),prod.getName()));

	}
	productTyp.getSelectionModel().selectFirst();

			
			  List stores=this.getBaseService().findAllBeans(Store.class);
			  for (Object it :stores) 
			  { Store store=(Store) it; storeLocation.getItems().add(new
			  ComboBoxItem(store.getId(),String.valueOf(store.getId())));
			  
			  }
			 
	
			  storeLocation.getSelectionModel().selectFirst();

} catch (DataBaseException | EmptyResultSetException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	gridPane.setCenterShape(true);
	//gridePanel.setHgap(10);
	gridPane.setAlignment(gridPane.getAlignment().CENTER);
	


	gridPane.add(typeLabel, 0, 0);
	gridPane.add(cutomerBox, 1, 0);
	
	
	gridPane.add(dateLabel, 2, 0);
	gridPane.add(datePicker, 3, 0);
	
	
	gridPane.add(nameLabel, 0, 1);
	gridPane.add(name, 1, 1);

	gridPane.add(phoneLabel, 2, 1);
	gridPane.add(phone, 3, 1);

	gridPane.add(addressLabel, 0, 2);
	gridPane.add(address, 1, 2);

	gridPane.add(productIdLabel, 2, 2);
	gridPane.add(productTyp, 3, 2);

	gridPane.add(grossWeightLabel, 0, 3);
	gridPane.add(grossWeight, 1, 3);

	gridPane.add(countLabel, 2, 3);
	gridPane.add(count, 3, 3);

	gridPane.add(nolunLabel, 0, 4);
	gridPane.add(nolun, 1, 4);

	gridPane.add(storeIdLabel, 2, 4);
	gridPane.add(storeLocation, 3, 4);

	

	

	
	gridPane.add(giftLabel, 4, 0);
	gridPane.add(gift, 5, 0);

	gridPane.add(tageLabel, 4, 1);
	gridPane.add(code, 5,1);
	
	
	gridPane.add(vehicelTypeLabel, 4, 2);
	gridPane.add(vehicleTypeBox, 5, 2);
	
	gridPane.add(notesLabel, 4, 4);
	gridPane.add(notes, 5, 4);


	
	
	//gridPane.add(saveBtn, 3,6);
	
	
	AnchorPane.setTopAnchor(gridPane,  0.0); 
	AnchorPane.setLeftAnchor(gridPane,  0.0); 
	AnchorPane.setBottomAnchor(gridPane,  0.0); 

inputForm_loc.getChildren().clear();
inputForm_loc.getChildren().setAll(gridPane);
}
    
    
    
    private List<Column> prepareTabelColumns(){
    
    
         List<Column> columns=new ArrayList<Column>();
        Column c=new Column(" ", "chk", "chk", 5, true);
        columns.add(c);
        Column c1=new Column("orderId", "orderId", "int", 0, false);
         columns.add(c1);
        Column c2=new Column(this.getMessage("customer.name"), "customerName", "string", 20, true);
           columns.add(c2);
        Column c3=new Column(this.getMessage("label.nolun"), "nowlun", "double", 15, true);
           columns.add(c3);
        Column c4=new Column(this.getMessage("lanel.quantity"), "quantity", "double", 15, true);
           columns.add(c4);
        Column c5=new Column(this.getMessage("label.count"), "count", "int", 10, true);
           columns.add(c5);
        Column c6=new Column(this.getMessage("label.gift"), "gift", "double", 10, true);
           columns.add(c6);
        Column c7=new Column(this.getMessage("label.store.name"), "storeName", "string", 15, true);
           columns.add(c7);
        Column c8=new Column(this.getMessage("finished"), "finished", "int", 10, true);
        columns.add(c8);
    
    
    return columns;
    
    
    
    
    
    
    }
    
    
    private List prepareheaderNodes(){
    	
    	
    	JFXButton editBtn=new JFXButton(this.getMessage("button.edit"));
     	    editBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.EDIT));
    	    editBtn.getStyleClass().setAll("btn","btn-primary");  
    	    
    	    editBtn.setOnAction(e -> {
  			  
    	    	editCustomerOrder();
  			  
  			  
  			  
  		  });
    	  //-----------------------------------------------------------------------------  
    	     bookDatePicker=new JFXDatePicker();
    	    bookDatePicker.setPadding(new Insets(0, 0, 0, 100));

    	    bookDatePicker.getEditor().setAlignment(Pos.CENTER);
    	    bookDatePicker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    	    bookDatePicker.setConverter(new StringConverter<LocalDate>()
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

    	    bookDatePicker.setOnAction(e -> {
    	   		
    	    	 LocalDate localate =bookDatePicker.getValue();
    	        Instant instant=Instant.from(localate.atStartOfDay(ZoneId.systemDefault()));
    	    	
    	    	Date date= Date.from(instant);
    	   			loadData(date);
    	   		});
    			

    	    //editBtn.getStyleClass().add("control-button");
    	    List nodes =new ArrayList(Arrays.asList(editBtn,bookDatePicker))  ;

    	return nodes;
    	
    }


List <CustomerViewBean> loadData(Date date) {
	
	this.gride.getTable().getItems().clear();

		 
	List customerOrders=new ArrayList<>();
	List customerViewBeans=new ArrayList<>();
	try {
			 customerOrders = this.getCustomerService().getCustomerOrders(date);
				
		

		for (Object it : customerOrders) {
			CustomerOrder order=(CustomerOrder) it;
			CustomerViewBean viewBean=new CustomerViewBean();
			viewBean.setOrderId(order.getId());
			viewBean.setCount(order.getUnits());
			viewBean.setCustomerName(order.getCustomer().getName());
			viewBean.setFinished(order.getFinished());
			viewBean.setGift(order.getTips());
			viewBean.setQuantity(order.getGrossweight());
			viewBean.setNowlun(order.getNolun());
			viewBean.setProductName(order.getProduct().getName());
			viewBean.setStoreName("1");
			
			customerViewBeans.add(viewBean);

			
		}
		
		this.gride.loadTableData(customerViewBeans);

	} catch (EmptyResultSetException e1) {
		// TODO Auto-generated catch block
		alert(AlertType.WARNING, "", "", this.getMessage("msg.warning.noData"));
	} catch (DataBaseException e1) {
		// TODO Auto-generated catch block
		alert(AlertType.ERROR, "", "", this.getMessage("msg.err.cannot.load.data"));
	}
	
	
	
	return customerViewBeans;
} 
private void fitToAnchorePane(Node node) {
	
	
	AnchorPane.setTopAnchor(node,  0.0); 
	AnchorPane.setLeftAnchor(node,  0.0); 
	AnchorPane.setRightAnchor(node,  0.0); 
	AnchorPane.setBottomAnchor(node,  0.0); 
	
	
	
} 
    private Image loadImage(String path) {
    	
    	Image icn=null;
    	
    	 
   	 try {
   			File file = new File(getClass().getClassLoader().getResource(path).getFile());
   			
   			
   			icn=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 
    	
    	
    	return 	 icn;
    	
    	
    	
    }
    
    

    boolean validateForm() {

       String customerName=name.getText();
        String noloun = nolun.getText();
        String tips = gift.getText();
        if (customerName.isEmpty()||noloun.isEmpty()||tips.isEmpty()) {
        	alert(AlertType.INFORMATION, "", "", this.getMessage("msg.err.required.values"));
            return false;
        } 

        double d_tips = Double.parseDouble(tips);
        double nol = Double.parseDouble(noloun);
        if (!vallidateSafeWithdrawal(nol+d_tips)) {
        	
        	alert(AlertType.INFORMATION, "", "", this.getMessage("msg.err.notEnough.safeBalance"));

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
    
    
    
    
    private boolean vallidateSafeWithdrawal(double amount) {
    	
    double  balance=	this.getCustomerService().getSafeBalance(ApplicationContext.season.getId());
    if (amount > balance) {

        return false;
    }
    return true;
    	
    }
  
    private void saveCustomerOrder() {
    	
        if (validateForm()) {
        	   try {
            
            String customerName = name.getText();
            int customerTypeId=cutomerBox.getSelectionModel().getSelectedItem().getValue();
            String addressValue = address.getText();
            String phoneValue = phone.getText();
            String tagValue = code.getText();
            int productId = productTyp.getSelectionModel().getSelectedItem().getValue();
            double weightValue = (grossWeight.getText().isEmpty()) ? 0 : Double.parseDouble(grossWeight.getText());
            double noloun = Double.parseDouble(nolun.getText());
            int store_id = storeLocation.getSelectionModel().getSelectedItem().getValue();
            double tips = Double.parseDouble(gift.getText());
            int vechileTypeId = vehicleTypeBox.getSelectionModel().getSelectedItem().getValue();
            String notesValue = notes.getText();
            Date orderDate=getValueOfDatePicker();
            int unites = (count.getText().isEmpty()) ? 0 : Integer.parseInt(count.getText());
            
            Customer customer=new Customer();
            customer.setName(customerName);
            customer.setTypeId(customerTypeId);
            customer.setPhone(phoneValue);
            customer.setAddress(addressValue);

            CustomerOrder order=new CustomerOrder();
            order.setProductId(productId);
            order.setVechileTypeId(vechileTypeId);
            order.setGrossweight(weightValue);
            order.setNolun(noloun);
            order.setTips(tips);
            order.setStoreId(store_id);
            order.setSeasonId(ApplicationContext.season.getId());
            order.setFridageId(ApplicationContext.fridage.getId());
            order.setNotes(notesValue);
            order.setCustomer(customer);
            order.setFinished(0);
            order.setOrderDate(orderDate);
            order.setOrderDate(orderDate);
            order.setUnits(unites);
            order.setOrderTag(tagValue);

				this.getCustomerService().saveCustomerOrder(order);
				alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.save"));
				inatiatePage() ;
			} catch (DataBaseException e) {
		    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));
				e.printStackTrace();
			}
            //inatiatePage();
        }

    	
    	
    }
    
    private Date getValueOfDatePicker() {
    	
    	
        LocalDate localate =datePicker.getValue();
        Instant instant=Instant.from(localate.atStartOfDay(ZoneId.systemDefault()));
    	
    	return Date.from(instant);
    	
    }
    
    private Date fromLocalDateToDate(LocalDate localDate) {
    	
    	
        Instant instant=Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	
    	return Date.from(instant);
    	
    }
    
    public void inatiatePage() {
        
        
        this.name.setText("");
        this.nolun.setText("");
        this.notes.setText("");
        this.grossWeight.setText("");
        this.count.setText("");
        this.gift.setText("");
        code.setText("");
        this.loadData(getValueOfDatePicker());

    }
    
    
    
    private void getBook() {
    	
    	

    	
    	try {
    		Date date= (Date) this.getBaseRetrievalService().aggregate("CustomerOrder", "max", "orderDate", null);
    		
    		loadData(date);
    		
    		
      	    this.bookDatePicker.setValue(getBaseService().convertToLocalDateViaMilisecond(new Date()));
     	
    	} catch (DataBaseException | EmptyResultSetException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        	
    }
    private void editCustomerOrder() {
    	
    	CustomerViewBean item=(CustomerViewBean) this.gride.getTable().getSelectionModel().getSelectedItem();
    	
    	this.request=new HashMap<String,Object>();
    	request.put("orderId", item.getOrderId());
    	
    	
    	EditCustomerOrderView form=new EditCustomerOrderView();
    	URL u=	 getClass().getClassLoader().getResource("appResources/custom.css");

    	Scene scene1= new Scene(form.getView(), 1000, 400);
    	Stage popupwindow=new Stage();
    	popupwindow.setMinHeight(400);
    	popupwindow.setMinWidth(900);

    	popupwindow.setResizable(true);
        String css =u.toExternalForm();
    	scene1.getStylesheets().addAll(css); 
    	popupwindow.initModality(Modality.APPLICATION_MODAL);
    	popupwindow.setTitle(this.getMessage("msg.info.edit.customerOrder"));
    	      
    	popupwindow.setScene(scene1);
    	popupwindow.setOnHiding( ev -> {
    		
try {
    		System.out.println("window closes");
    		boolean valid=(boolean) this.response.get("valid");
    		
    		if(valid)
    			loadData(fromLocalDateToDate(bookDatePicker.getValue()));
        
}catch (Exception e) {
	// TODO: handle exception
}
    		
    	});
    	
    	popupwindow.showAndWait();

    	
    	
    }
}
