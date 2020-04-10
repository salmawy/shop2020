package App.com.expanses.view.addOutcome;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.glyphfont.FontAwesome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import App.com.expanses.action.ExpansesAction;
import App.core.Enum.OutcomeTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.OutcomeDetail;
import App.core.beans.OutcomeType;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddOutcomePersenter extends ExpansesAction implements Initializable {
	
	
    Logger logger = Logger.getLogger(this.getClass().getName());	
  
    
    @FXML
    private JFXButton cancel_btn;

    @FXML
    private JFXTextArea note_TA;

    @FXML
    private Label outcomeAmount_label;

    @FXML
    private AnchorPane root_pane;

    @FXML
    private JFXComboBox<ComboBoxItem> outcomeType_combo;

    @FXML
    private Label outcomeType_label;

    @FXML
    private JFXTextField outcomeAmount_TF;

    @FXML
    private Label datePicker_label;

    @FXML
    private Pane coloredPane;

    @FXML
    private Label title_label;

    @FXML
    private VBox datePicker_loc;

    @FXML
    private JFXButton saveBtn;
    
    
    private List requirerdTypes;
	    
	    private JFXDatePicker datePicker;
 	   private  int id;

	    public AddOutcomePersenter() {
	    	
	    	
	    	id=(int) request.get("outcomeDetailId");
	    	

			 requirerdTypes=new ArrayList(Arrays.asList(OutcomeTypeEnum.labours
					,OutcomeTypeEnum.maintaince,OutcomeTypeEnum.varaity,
					OutcomeTypeEnum.allah,OutcomeTypeEnum.forgivness));
			
		}
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	  	  logger.log(Level.INFO,"============================================================================================================");
		
	  	  
	  	  init();
		 
		 
	}



	private void init() { 
		String title=(id==0)?getMessage("outcome.add.data"):getMessage("outcome.edit.data");				
		title_label.setText(title);
		  //==============================================================================================================

		  fillOutcomeCombo();
		  //==============================================================================================================
		 datePicker_label.setText(getMessage("label.date"));
		 datePicker=new JFXDatePicker();
		   	datePicker.getEditor().setAlignment(Pos.CENTER_LEFT);
		   	datePicker.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		   	datePicker.setPrefWidth(317);
		   	 
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
		   	datePicker_loc.getChildren().add(datePicker);
		   	datePicker_loc.setPrefWidth(317);
		  
		  //==============================================================================================================

	    saveBtn.setText(this.getMessage("button.save"));
	    saveBtn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.SAVE));
	    saveBtn.getStyleClass().setAll("btn","btn-primary");  
	  
		//-----------------------------
		cancel_btn.setText(this.getMessage("button.cancel"));
		cancel_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.REMOVE));
	    cancel_btn.getStyleClass().setAll("btn","btn-danger");
	    cancel_btn.setOnAction(e -> {
	    	cancel();
	    });	
	    
		
		outcomeAmount_label.setText(this.getMessage("label.money.amount"));
		note_TA.setText(this.getMessage("label.notes"));
		outcomeType_label.setText(this.getMessage("label.safe.outcome.Expansekind"));
		//outcomeName_label.setText(this.getMessage("label.name"));
		
 //==============================================================================================================
	
		if(id!=0) {
			try {
				OutcomeDetail detail= 	(OutcomeDetail)getBaseService().getBean(OutcomeDetail.class, id);
				//-----------------------------------------------------------------------------
			List <ComboBoxItem>types=	outcomeType_combo.getItems();
				
 				int i=0;
				for ( i = 0; i < types.size(); i++) {
					
					if ((int)types.get(i).getValue()==detail.getTypeId()) {
						break;
					}
					
				}
				this.outcomeType_combo.getSelectionModel().select(i);

				//-------------------------------------------------------------------------------
				outcomeAmount_TF.setText(String.valueOf(detail.getAmount()));
				//-------------------------------------------------------------------------------
				datePicker.setValue(getBaseService().convertToLocalDateViaMilisecond(detail.getOutcome().getOutcomeDate()));
				//-------------------------------------------------------------------------------
				note_TA.setText(detail.getNotes());
				
				
				
				
			} catch (DataBaseException | InvalidReferenceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
		
		
	}

    
	@FXML
	private void saveOutcome() {
		if(validateOutForm()) {
		
		try {   
			
			 double amount = Double.parseDouble(this.outcomeAmount_TF.getText());

	            String notes = note_TA.getText();
			int typeId=outcomeType_combo.getSelectionModel().getSelectedItem().getValue();
	       //     if (typeId == OutcomeTypeEnum.OUT_PAY_LOAN) {
	      //          this.getExpansesServices().loanPayTansaction(outcomeName_TF.getText(), new Date(), amount, typeId, notes, ApplicationContext.fridage.getId());
	       ///     } else {
	            	
			Date date=getBaseService().convertToDateViaInstant(datePicker.getValue());
	            	
			
			if(id==0)
			this.getExpansesServices().outcomeTransaction(date, amount, notes, typeId, -1, -1, ApplicationContext.fridage.getId(),ApplicationContext.season.getId());
			else 
				this.getExpansesServices().editOutcomeTransaction(date, amount, notes, typeId, -1, -1, ApplicationContext.fridage.getId(),ApplicationContext.season.getId(),id);

			
			// }
			alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.save"));
			cancel();
	   
	   }catch (Exception ex) {
	    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));

	
		}
		
		
		
		
		
	}
		
	}
  
	//--------------------------------------------------------------------------------------------------------------------------------

		private void fillOutcomeCombo() {
		
			
			List outcomeTypes=new ArrayList();
			try {
				outcomeTypes = this.getBaseService().findAllBeans(OutcomeType.class);
			} catch (DataBaseException |EmptyResultSetException e) {		}
			
			
			for (Iterator iterator = outcomeTypes.iterator(); iterator.hasNext();) {
				OutcomeType type = (OutcomeType) iterator.next();
				if(requirerdTypes.contains(type.getId()))
				outcomeType_combo.getItems().add(new ComboBoxItem(type.getId(),type.getNameAr()));

			}
			
			
			outcomeType_combo.getSelectionModel().selectFirst();
		 
			
	}
		//--------------------------------------------------------------------------------------------------------------------------------

	    boolean validateOutForm() {
	        String amount = outcomeAmount_TF.getText();

	       // String name = outcomeName_TF.getText();
	       // int typeId = outcomeType_combo.getSelectionModel().getSelectedItem().getValue();
	        double safaBalance=this.getExpansesServices().getSafeBalance(ApplicationContext.season.getId());
	        if (amount.isEmpty()) {
	        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.amount"));
	        	return false;

	        } else if (safaBalance<Double.parseDouble(amount)) {
	        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.notEnough.safeBalance"));

	            return false;
	        }
	        
	        /*  else if (typeId == OutcomeTypeEnum.OUT_PAY_LOAN) {
	        	
	            LoanAccount account = this.getExpansesServices().getLoanerAccount(name);

	        	
	             if (name.isEmpty()) {
	            	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.name"));

	                return false;
	 
	            }
			
			 * if (account == null) {
			 * alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),
			 * this.getMessage("msg.err.notfound.name"));
			 * 
			 * return false;
			 * 
			 * } if (account.getDueAmount() > 0 && account.getType().equals("OUT_LOAN")) {
			 * alert(AlertType.WARNING,this.getMessage("msg.warning"),this.getMessage(
			 * "msg.warning"),this.getMessage("msg.err.amountShouldBecollestedFromLoaner")
			 * +" : "+account.getDueAmount());
			 * 
			 * return false;
			 * 
			 * }
			 
	            if (Double.parseDouble(amount) > account.getDueAmount()) {
	            	alert(AlertType.WARNING,this.getMessage("msg.warning"),this.getMessage("msg.warning"),this.getMessage("msg.err.input.amount.greather")+" : "+account.getDueAmount());

	                return false;
	            }
	        } */
	        return true;

	    }
		//--------------------------------------------------------------------------------------------------------------------------------
 
	    private void alert(AlertType alertType,String title,String headerText,String message) {
			 Alert a = new Alert(alertType);
			 a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			 a.setTitle(title );
			 a.setHeaderText(headerText);
			 a.setContentText(message); 
	      a.show(); 
		  
	}
		//--------------------------------------------------------------------------------------------------------------------------------
    
		private void cancel() {
			 Stage stage = (Stage) cancel_btn.getScene().getWindow();
		      // do what you have to do
		      stage.close();
		   
		   
		}
		
	  
}
