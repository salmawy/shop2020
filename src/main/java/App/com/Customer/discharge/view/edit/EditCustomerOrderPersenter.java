package App.com.Customer.discharge.view.edit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import App.com.Customer.action.CustomerBaseAction;
import App.com.Customer.discharge.view.beans.CustomerViewBean;
import App.core.Enum.CustomerTypeEnum;
import App.core.Enum.VechileTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.CustomTable;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Customer;
import App.core.beans.CustomerOrder;
import App.core.beans.Product;
import App.core.beans.Store;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import App.core.validator.Validator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class EditCustomerOrderPersenter extends CustomerBaseAction implements Initializable {
    
	Logger logger = Logger.getLogger(this.getClass().getName());	

  
   
   
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
    private CustomerOrder oldOrder;
   
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	errIcon=loadImage("icons/error-icon.png");
   	
    	
   	init();
  }
    
    
    
    private void init()  {
    	
    	
    	
		gridPane=new GridPane();
		gridPane.setCenterShape(true);
	    gridPane.setAlignment(gridPane.getAlignment().BASELINE_RIGHT);
		gridPane.setVgap(20);
		initInputGridPane();
       saveBtn.setOnAction(e -> {
			   save();
			
		  });
       
       
       
       
       
       loadOrderData();
       
    	
    }
private void loadOrderData() {
	
	int orderId=(int) this.request.get("orderId");
	
	try {
		oldOrder=(CustomerOrder) this.getBaseRetrievalService().getBean(CustomerOrder.class, orderId);
		
		cutomerBox.getSelectionModel().select(oldOrder.getCustomer().getTypeId()-1);
		name.setText(oldOrder.getCustomer().getName());
		phone.setText(oldOrder.getCustomer().getPhone());
		address.setText(oldOrder.getCustomer().getAddress());
		
		grossWeight.setText(oldOrder.getGrossweight().toString());
		nolun.setText(String.valueOf(oldOrder.getNolun()));
		gift.setText(String.valueOf(oldOrder.getTips()));
		count.setText(String.valueOf(oldOrder.getUnits()));
		code.setText(oldOrder.getOrderTag());
		vehicleTypeBox.getSelectionModel().select(oldOrder.getVechileTypeId()-1);
		notes.setText(oldOrder.getNotes());
		
		Instant instant=Instant.ofEpochMilli(oldOrder.getOrderDate().getTime());
	    LocalDate localate =LocalDate.ofInstant(instant, ZoneId.systemDefault());
	    this.datePicker.setValue(localate);
	
	
	
	
	
	
	
	
	
	
	
	} catch (DataBaseException | InvalidReferenceException e) {
 	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.cannot.load.data"));
e.printStackTrace();
	}
	
		
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
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.normal,this.getMessage("customer.type.normal")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.purchases,this.getMessage("customer.type.purchaes")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.kareem,this.getMessage("customer.type.karrem")));
cutomerBox.getItems().add(new ComboBoxItem(CustomerTypeEnum.mahmed,this.getMessage("customer.type.mahmed")));
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

	
	
	
    Instant instant=Instant.now();
    LocalDate localate =LocalDate.ofInstant(instant, ZoneId.systemDefault());
    this.datePicker.setValue(localate);

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
	Text layoutIcon = FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.SAVE);
    layoutIcon.getStyleClass().addAll("button-icon", "layout-button-icon");    	    
    saveBtn.setGraphic(layoutIcon);
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
  
    private void save() {
    	
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

				this.getCustomerService().editCustomerOrder(order, oldOrder);
				alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.edit"));
				 
				
				
				Stage stage = (Stage) saveBtn.getScene().getWindow();
				   this.response=new HashMap<String, Object>();
				   response.put("valid",true);
				   
				      stage.close(); 
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
    
    
    
    public void inatiatePage() {
        
        
        this.name.setText("");
        this.nolun.setText("");
        this.notes.setText("");
        this.grossWeight.setText("");
         this.count.setText("");
          this.gift.setText("");

    }
    
    

 }
