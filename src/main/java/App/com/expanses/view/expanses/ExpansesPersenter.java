package App.com.expanses.view.expanses;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.glyphfont.FontAwesome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import App.com.expanses.action.ExpansesAction;
import App.com.expanses.view.beans.IncomeVB;
import App.com.expanses.view.beans.OutcomeVB;
import App.core.Enum.IncomeTypesEnum;
import App.core.Enum.OutcomeTypeEnum;
import App.core.UIComponents.comboBox.ComboBoxItem;
import App.core.UIComponents.customTable.Column;
import App.core.UIComponents.customTable.CustomTable;
import App.core.applicationContext.ApplicationContext;
import App.core.beans.Contractor;
import App.core.beans.Customer;
import App.core.beans.Income;
import App.core.beans.IncomeDetail;
import App.core.beans.LoanAccount;
import App.core.beans.Loaners;
import App.core.beans.Outcome;
import App.core.beans.OutcomeDetail;
import App.core.beans.OutcomeType;
import App.core.beans.Seller;
import App.core.exception.DataBaseException;
import App.core.exception.EmptyResultSetException;
import App.core.exception.InvalidReferenceException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class ExpansesPersenter extends ExpansesAction implements Initializable {

	
	Logger logger = Logger.getLogger(this.getClass().getName());	


    @FXML
    private AnchorPane incomeTable_loc;

    @FXML
    private Label outcomeName_label;

    @FXML
    private Label outcomeAmount_label;

    @FXML
    private JFXComboBox<ComboBoxItem> outcomeType_combo;

    @FXML
    private Label initailBalanceValue_label;

    @FXML
    private JFXTextField incomeAmount_TF;

    @FXML
    private Label incomeName_label;

    @FXML
    private Label incomeNotes_label;

    @FXML
    private Label incomeType_label;

    @FXML
    private JFXTextField incomeName_TF;

    @FXML
    private Label outcomeNotes_label;

    @FXML
    private JFXButton income_btn;

    @FXML
    private JFXComboBox<ComboBoxItem> incomeType_combo;

    @FXML
    private Label currentBalance_label;

    @FXML
    private JFXButton outcome_btn;

    @FXML
    private Label initailBalance_label;

    @FXML
    private Label outcomeType_label;

    @FXML
    private JFXTextField outcomeAmount_TF;

    @FXML
    private Label currentBalanceValue_label;

    @FXML
    private JFXTextArea incomeNotes_TA;

    @FXML
    private JFXTextArea outcomeNotes_TA;

    @FXML
    private JFXTextField outcomeName_TF;

    @FXML
    private Label incomeAmount_label;

    @FXML
    private AnchorPane outcomeTable_loc;
	
	private CustomTable<IncomeVB>incomeCustomTable; 
	private CustomTable<OutcomeVB>outcomeCustomTable; 
	
	
    private JFXComboBox<ComboBoxItem> incomeMonth_CB;
    private JFXComboBox<ComboBoxItem> incomeDay_CB;
    private JFXComboBox<ComboBoxItem> outcomeMonth_CB;
    private JFXComboBox<ComboBoxItem> outcomeDay_CB;

    private Label incomeTotalAmount_label;
    private Label incomeTotalAmountValue_label;

    private Label outcomeTotalAmount_label;
    private Label outcomeTotalAmountValue_label;

	private SimpleDateFormat daySdf=new SimpleDateFormat("dd");


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	  	  logger.log(Level.INFO,"============================================================================================================");
	init();
		
	}
  private void init() {
	  
	  
	  
 		currentBalance_label.setText(this.getMessage("label.safe.balance"));
		initailBalance_label.setText(this.getMessage("label.safe.intialBalance"));
		incomeAmount_label.setText(this.getMessage("label.money.amount"));
		incomeType_label.setText(this.getMessage("label.safe.income.kind"));
		incomeNotes_label.setText(this.getMessage("label.notes"));
		outcomeAmount_label.setText(this.getMessage("label.money.amount"));
		outcomeNotes_label.setText(this.getMessage("label.notes"));
		outcomeType_label.setText(this.getMessage("label.safe.outcome.Expansekind"));
		outcomeName_label.setText(this.getMessage("label.name"));
		incomeName_label.setText(this.getMessage("label.name"));


  //==============================================================================================================
	  
		List outcomeColumns=prepareOutcomeTabelColumns();
		List incomeColumns=prepareIncomeTableColumns();
		List incomeNodes=prepareIncomeHeaderNodes();
		List outcomeNodes=prepareOutcomeHeaderNodes();

 //==============================================================================================================
		incomeCustomTable=new CustomTable<IncomeVB>(incomeColumns, incomeNodes, null, null, null, CustomTable.headTableCard, IncomeVB.class);
		outcomeCustomTable=new CustomTable<OutcomeVB>(outcomeColumns, outcomeNodes, null, null, null, CustomTable.headTableCard, OutcomeVB.class);
		FlowPane incomeHeader=incomeCustomTable.getActionsPanel();
		incomeHeader.setVgap(10);
		incomeHeader.setAlignment(Pos.CENTER);
		FlowPane outcomeHeader=outcomeCustomTable.getActionsPanel();
		
		outcomeHeader.setVgap(10);
		outcomeHeader.setAlignment(Pos.CENTER);
		
		fitToAnchorePane(incomeCustomTable.getCutomTableComponent());
		fitToAnchorePane(outcomeCustomTable.getCutomTableComponent());
	  
		incomeTable_loc.getChildren().addAll(incomeCustomTable.getCutomTableComponent());
		outcomeTable_loc.getChildren().addAll(outcomeCustomTable.getCutomTableComponent());
 //==============================================================================================================
	  fillIncomeMonthes();
	  fillOutcomeMonthes();
	  fillOutcomeCombo();
 //==============================================================================================================

	  income_btn.setText(this.getMessage("button.save"));
  	    income_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.SAVE));
	    income_btn.getStyleClass().setAll("btn","btn-primary");  
	    
	    outcome_btn.setText(this.getMessage("button.save"));
  	    outcome_btn.setGraphic(new FontAwesome().create(FontAwesome.Glyph.SAVE));
	    outcome_btn.getStyleClass().setAll("btn","btn-primary");  
	  
  }
  
  
  
	private void fillOutcomeCombo() {
	
		
		List outcomeTypes=new ArrayList();
		try {
			outcomeTypes = this.getBaseService().findAllBeans(OutcomeType.class);
		} catch (DataBaseException |EmptyResultSetException e) {		}
		
		
		List requirerdTypes=new ArrayList(Arrays.asList(4,8,1,6,1,15,9,11));
		for (Iterator iterator = outcomeTypes.iterator(); iterator.hasNext();) {
			OutcomeType type = (OutcomeType) iterator.next();
			if(requirerdTypes.contains(type.getId()))
			outcomeType_combo.getItems().add(new ComboBoxItem(type.getId(),type.getNameAr()));

		}
		
		
		outcomeType_combo.getSelectionModel().selectFirst();
		outcomeType_combo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                    final Number oldvalue, final Number newvalue)
            { 
            	
            	ComboBoxItem item = outcomeType_combo.getSelectionModel().getSelectedItem();
            if (item.getValue() == OutcomeTypeEnum.OUT_PAY_LOAN) {
                outcomeName_TF.setEditable(true);
            } else {
            	outcomeName_TF.setEditable(false);
            }
            
            }
        });
		
		
}
	private List<Column> prepareOutcomeTabelColumns(){
        
        List<Column> columns=new ArrayList<Column>();
  
       Column c2=new Column(this.getMessage("label.money.amount"), "amount", "string", 10, true);
       columns.add(c2);
       
        c2=new Column(this.getMessage("label.name"), "name", "string", 20, true);
       columns.add(c2);
       
        c2=new Column(this.getMessage("label.safe.outcome.Expansekind"), "type", "string", 20, true);
       columns.add(c2);
       
        c2=new Column(this.getMessage("invoice.No"), "orderTage", "string", 20, true);
       columns.add(c2);
       
        c2=new Column(this.getMessage("label.fridage.name"), "fridageName", "string", 10, true);
       columns.add(c2);
       
        c2=new Column(this.getMessage("label.report"), "report", "string", 20, true);
       columns.add(c2);
       
        
   
   
   return columns;
   
   
   
   
   
   
   }
  
  
  
private List<Column> prepareIncomeTableColumns(){
        
        List<Column> columns=new ArrayList<Column>();
  
       Column c2=new Column(this.getMessage("label.money.amount"), "amount", "string", 15, true);
       columns.add(c2);
       
       c2=new Column(this.getMessage("label.safe.income.kind"), "type", "string", 25, true);
       columns.add(c2);
      
       
       c2=new Column(this.getMessage("label.name"), "name", "string", 25, true);
       columns.add(c2);
   
       
        c2=new Column(this.getMessage("label.report"), "notes", "string", 35, true);
       columns.add(c2);
       
        
   
   
   return columns;
   
   
   
   
   
   
   }
  
  
  
  
  private List prepareIncomeHeaderNodes() {
	 
	  
	  this.incomeDay_CB=new JFXComboBox<ComboBoxItem>();
      this.incomeMonth_CB=new JFXComboBox<ComboBoxItem>();
      incomeDay_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                    final Number oldvalue, final Number newvalue)
            {
            	
            	ComboBoxItem day=incomeDay_CB.getSelectionModel().getSelectedItem();
            	
					loadIncomeData(day.getValue());
					
			 }
        });
		
      incomeMonth_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
      {
          public void changed(ObservableValue<? extends Number> ov,
                  final Number oldvalue, final Number newvalue)
          {
          	
          	fillIncomeDays();
	 	
          }
      });
		
			
	
      incomeTotalAmount_label=new Label(this.getMessage("label.total"));
      
	  this.incomeTotalAmountValue_label=new Label("0.0");

	 	  return new ArrayList(Arrays.asList(incomeDay_CB,incomeMonth_CB,incomeTotalAmount_label,incomeTotalAmountValue_label));

	  
	  
  }
  
  
   private void  loadIncomeData(Date date) {
		

	   try {
		Income income=(Income) this.getExpansesServices().getIncome(date);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("incomeId", income.getId());
	List data=	this.getBaseService().findAllBeans(IncomeDetail.class, map, null);
	List tableData=new ArrayList();
	double totalCash=0.0;
	double totalAmount=0.0;
	int index=1;
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			IncomeDetail detail = (IncomeDetail) iterator.next();
			IncomeVB row=new IncomeVB();
			
			totalAmount+=detail.getAmount();
			
		
			String name="";
			if (detail.getTypeId()==IncomeTypesEnum.INTST_PAY) {
				Seller seller=(Seller) this.getBaseService().getBean(Seller.class, detail.getSellerId());
				name=seller.getName();
			}
			else if (detail.getTypeId()==IncomeTypesEnum.IN_LOAN||detail.getTypeId()==IncomeTypesEnum.IN_PAY_LOAN)
			{
				Loaners loaner=(Loaners) this.getBaseService().getBean(Loaners.class, detail.getSellerId());
				name=loaner.getName();
			}
			else if(detail.getTypeId()==IncomeTypesEnum.CASH) {
				totalCash+=detail.getAmount();

			continue;
				
			}
			
			row.setAmount(detail.getAmount());
			row.setId(detail.getId());
			row.setName(name);
			row.setNotes(detail.getNotes());
			row.setType(detail.getType().getName());
			tableData.add(index,row);
			index++;
		}
		
		IncomeVB row=new IncomeVB();
		row.setId(0);
		row.setAmount(totalCash);
		row.setType(this.getMessage("seller.type.cash"));
		row.setNotes(" ");
		row.setName(" ");
		tableData.add(0,row);
		this.incomeCustomTable.loadTableData(tableData);
		
		this.incomeTotalAmountValue_label.setText(String.valueOf(totalAmount));
		
		
	} catch (EmptyResultSetException e) {
		
	} catch (DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
			logger.warning(" no seller found ");
	}
	   
	   
	   
	   
	   
	   
	   
	   
   }
  
  
   private void  loadIncomeData(int incomeId) {
		

	   try {
		
		   Map<String,Object> map=new HashMap<String, Object>();
		   map.put("incomeId", incomeId);
	List data=	this.getBaseService().findAllBeans(IncomeDetail.class, map, null);
	List tableData=new ArrayList();
	double totalCash=0.0;
	double totalAmount=0.0;
	
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			IncomeDetail detail = (IncomeDetail) iterator.next();
			IncomeVB row=new IncomeVB();
			
			totalAmount+=detail.getAmount();
			
		
			String name="";
			if (detail.getTypeId()==IncomeTypesEnum.INTST_PAY) {
				Seller seller=(Seller) this.getBaseService().getBean(Seller.class, detail.getSellerId());
				name=seller.getName();
			}
			else if (detail.getTypeId()==IncomeTypesEnum.IN_LOAN||detail.getTypeId()==IncomeTypesEnum.IN_PAY_LOAN)
			{
				Loaners loaner=(Loaners) this.getBaseService().getBean(Loaners.class, detail.getSellerId());
				name=loaner.getName();
			}
			else if(detail.getTypeId()==IncomeTypesEnum.CASH) {
				totalCash+=detail.getAmount();

			continue;
				
			}
			
			row.setAmount(detail.getAmount());
			row.setId(detail.getId());
			row.setName(name);
			row.setNotes(detail.getNotes());
			row.setType(detail.getType().getName());
			tableData.add(row);
		}
		
		IncomeVB row=new IncomeVB();
		row.setId(0);
		row.setAmount(totalCash);
		row.setType(this.getMessage("seller.type.cash"));
		row.setNotes(" ");
		row.setName(" ");
		tableData.add(0,row);
		this.incomeCustomTable.loadTableData(tableData);
		
		this.incomeTotalAmountValue_label.setText(String.valueOf(totalAmount));
		
		
	} catch (EmptyResultSetException e) {
		
	} catch (DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
			logger.warning(" no seller found ");
	}
	   
	   
	   
	   
	   
	   
	   
	   
   }
  
 
 
  private void fillOutcomeDays() {
	  
	  List comboDays=new ArrayList();
	  ComboBoxItem month=outcomeMonth_CB.getSelectionModel().getSelectedItem();
	  try {
	List days=	this.getExpansesServices().getOutcomeDays(month.getText());
	for (Iterator iterator = days.iterator(); iterator.hasNext();) {
		Outcome outcome = (Outcome) iterator.next();
		ComboBoxItem item=new ComboBoxItem(outcome.getId(), daySdf.format(outcome.getOutcomeDate()));
		comboDays.add(item);
		
	}
	
	
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 this.outcomeDay_CB.getItems().clear(); 
	 this.outcomeDay_CB.getItems().setAll(comboDays); 
	 outcomeDay_CB.getSelectionModel().selectFirst();
	 loadOutcomeData(outcomeDay_CB.getSelectionModel().getSelectedItem().getValue());

	  
	  
  }
  private void fillIncomeDays() {
	  
	  List comboDays=new ArrayList();
	  ComboBoxItem month=incomeMonth_CB.getSelectionModel().getSelectedItem();
	  try {
	List days=	this.getExpansesServices().getIncomeDays(month.getText());
	for (Iterator iterator = days.iterator(); iterator.hasNext();) {
		Income income = (Income) iterator.next();
		ComboBoxItem item=new ComboBoxItem(income.getId(), daySdf.format(income.getIncomeDate()));
		comboDays.add(item);
		
	}
	
	
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 this.incomeDay_CB.getItems().clear(); 
	 this.incomeDay_CB.getItems().setAll(comboDays); 
	 incomeDay_CB.getSelectionModel().selectFirst();
	 loadIncomeData(incomeDay_CB.getSelectionModel().getSelectedItem().getValue());

	  
	  
  }
  
  
  
  
  //000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
  
  private void fillOutcomeMonthes() {
	  
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("seasonId", ApplicationContext.season.getId());
	  try {
		  
		  
		List result=this.getExpansesServices().getOutcomeMonthes(ApplicationContext.season.getId());
	
		List comboData=new ArrayList();
	  for (Iterator iterator = result.iterator(); iterator.hasNext();) {
		  String month = (String) iterator.next();
	
		int value=Integer.parseInt(month.split("-")[1]);
		String text= month;
			ComboBoxItem item=new ComboBoxItem(value,text);
			comboData.add(item);
		
		
	}
	  
	  this.outcomeMonth_CB.getItems().clear();
	  this.outcomeMonth_CB.getItems().setAll(comboData);
	  outcomeMonth_CB.getSelectionModel().selectFirst();
	  fillOutcomeDays();
	  
	  
	  
	  
	  
	  
	  
	  } catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
	  
  }
  
  private void fillIncomeMonthes() {
	  
	
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("seasonId", ApplicationContext.season.getId());
	  try {
		  
		  
		List result=this.getExpansesServices().getIncomeMonthes(ApplicationContext.season.getId());
	
		List comboData=new ArrayList();
	  for (Iterator iterator = result.iterator(); iterator.hasNext();) {
		  String month = (String) iterator.next();
	
		int value=Integer.parseInt(month.split("-")[1]);
		String text= month;
			ComboBoxItem item=new ComboBoxItem(value,text);
			comboData.add(item);
		
		
	}
	  
	  this.incomeMonth_CB.getItems().clear();
	  this.incomeMonth_CB.getItems().setAll(comboData);
	  incomeMonth_CB.getSelectionModel().selectFirst();
	  fillIncomeDays();
	  
	  
	  
	  
	  
	  
	  
	  } catch (DataBaseException | EmptyResultSetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
	  
  }
 
  private void loadOutcomeData(Date date) {
	  
	  
	  
	  try {
		Outcome outcome=(Outcome) this.getExpansesServices().getOutcome(date);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("outcomeId", outcome.getId());
	List data=	this.getBaseService().findAllBeans(IncomeDetail.class, map, null);
	List tableData=new ArrayList();
		double totalAmount=0.0;
		
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			OutcomeDetail detail = (OutcomeDetail) iterator.next();
			
			OutcomeVB row=new OutcomeVB();
			row.setId(detail.getId());
			row.setReport(detail.getNotes());
			row.setOrderTage(String.valueOf(detail.getOrderId()));
			row.setType(detail.getType().getName());
			row.setFridageName(detail.getFridage().getName());
			totalAmount+=detail.getAmount();
			String name=" ";
			if(detail.getCustomerId()==-1) {
				
	 if(detail.getTypeId()==OutcomeTypeEnum.K_L||detail.getTypeId()==OutcomeTypeEnum.K_L||detail.getTypeId()==OutcomeTypeEnum.K_L)

	 {
		    Contractor contractor=(Contractor) this.getBaseService().getBean(Contractor.class, detail.getCustomerId());
			name=contractor.getName();
		 
		 
     } else if (detail.getTypeId()==OutcomeTypeEnum.OUT_LOAN||detail.getTypeId()==OutcomeTypeEnum.OUT_PAY_LOAN) {
				
    	     Loaners loaner=(Loaners) this.getBaseService().getBean(Loaners.class, detail.getCustomerId());
			name=loaner.getName();
				
			}
     else {
        
	     Customer customer=(Customer) this.getBaseService().getBean(Customer.class, detail.getCustomerId());

    	 name =customer.getName();

     }	}
		
			 row.setName(name);
			 tableData.add(row);
			 this.outcomeTotalAmountValue_label.setText(String.valueOf(totalAmount));
		
		}
		
		
		
		this.outcomeCustomTable.loadTableData(tableData);

		
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  
	  
	  
  }
  
  
  
  private void loadOutcomeData(int outcomeId ) {
	  
	
	  try {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("outcomeId", outcomeId);
	List data=	this.getBaseService().findAllBeans(OutcomeDetail.class, map, null);
	List tableData=new ArrayList();
		double totalAmount=0.0;
		
		for (Iterator iterator = data.iterator(); iterator.hasNext();) {
			OutcomeDetail detail = (OutcomeDetail) iterator.next();
			
			OutcomeVB row=new OutcomeVB();
			row.setId(detail.getId());
			row.setAmount(detail.getAmount());
           
			String orderTag = (detail.getOrderId()==null||detail.getOrderId()==-1 )? "" :String.valueOf(detail.getOrderId()) ;

			row.setReport(detail.getNotes());
			row.setOrderTage(orderTag);
			row.setType(detail.getType().getNameAr());
			row.setFridageName(detail.getFridage().getName());
			totalAmount+=detail.getAmount();
			String name=" ";
			if(detail.getCustomerId()!=-1) {
				
	 if(detail.getTypeId()==OutcomeTypeEnum.K_L||detail.getTypeId()==OutcomeTypeEnum.K_V||detail.getTypeId()==OutcomeTypeEnum.K_S)

	 {
		    Contractor contractor=(Contractor) this.getBaseService().getBean(Contractor.class, detail.getCustomerId());
			name=contractor.getName();
		 
		 
     } else if (detail.getTypeId()==OutcomeTypeEnum.OUT_LOAN||detail.getTypeId()==OutcomeTypeEnum.OUT_PAY_LOAN) {
				
    	     Loaners loaner=(Loaners) this.getBaseService().getBean(Loaners.class, detail.getCustomerId());
			name=loaner.getName();
				
			}
     else {
        
	     Customer customer=(Customer) this.getBaseService().getBean(Customer.class, detail.getCustomerId());

    	 name =customer.getName();

     }	}
		
			 row.setName(name);
			 tableData.add(row);
			 this.outcomeTotalAmountValue_label.setText(String.valueOf(totalAmount));
		
		}
		
		
		
		this.outcomeCustomTable.loadTableData(tableData);

		
	} catch (EmptyResultSetException | DataBaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidReferenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  
	  
	  
  }
  
  
  
  private List prepareOutcomeHeaderNodes() {
	 
	  
	  this.outcomeDay_CB=new JFXComboBox<ComboBoxItem>();
      this.outcomeMonth_CB=new JFXComboBox<ComboBoxItem>();
      outcomeDay_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                    final Number oldvalue, final Number newvalue)
            {
            	
            	ComboBoxItem day=outcomeDay_CB.getSelectionModel().getSelectedItem();
            	
					loadOutcomeData(day.getValue());
					
			 }
        });
		
      outcomeMonth_CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
      {
          public void changed(ObservableValue<? extends Number> ov,
                  final Number oldvalue, final Number newvalue)
          {
          	
          	fillOutcomeDays();
	 	
          }
      });
		
			
	
      
      
	  this.outcomeTotalAmount_label =new Label(this.getMessage("label.total"));
	  this.outcomeTotalAmountValue_label=new Label("0.0");
	 
	  return new ArrayList(Arrays.asList(outcomeDay_CB,outcomeMonth_CB,outcomeTotalAmount_label,outcomeTotalAmountValue_label));

	  
	  
  }
  
  

  private void fitToAnchorePane(Node node) {
  	
  	
  	AnchorPane.setTopAnchor(node,  0.0); 
  	AnchorPane.setLeftAnchor(node,  0.0); 
  	AnchorPane.setRightAnchor(node,  0.0); 
  	AnchorPane.setBottomAnchor(node,  0.0); 
  	
  	
  	
  }  
	
  
  private void alert(AlertType alertType,String title,String headerText,String message) {
		 Alert a = new Alert(alertType);
		 a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		 a.setTitle(title );
		 a.setHeaderText(headerText);
		 a.setContentText(message); 
      a.show(); 
	  
}
  

	@FXML
	private void saveOutcome() {
		
		
		try {   
			
			 double amount = Double.parseDouble(this.outcomeAmount_TF.getText());

	            String notes = outcomeNotes_TA.getText();
			int typeId=outcomeType_combo.getSelectionModel().getSelectedItem().getValue();
	            if (typeId == OutcomeTypeEnum.OUT_PAY_LOAN) {
	                this.getExpansesServices().loanPayTansaction(outcomeName_TF.getText(), new Date(), amount, typeId, notes, ApplicationContext.fridage.getId());
	            } else {
	            	this.getExpansesServices().outcomeTransaction(new Date(), amount, notes, typeId, -1, -1, ApplicationContext.fridage.getId(),ApplicationContext.season.getId());
	            }
			alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.save"));
	   
	   }catch (Exception ex) {
	    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));

	
		}
		
		
		
		
		
	}
  
	@FXML
	private void saveIncome() {
		

		   int typeId = incomeType_combo.getSelectionModel().getSelectedItem().getValue();
		   double amount = Double.parseDouble(this.incomeAmount_TF.getText()); 
           String notes = incomeNotes_TA.getText();
		
		try {   
			

	        this.getExpansesServices().loanPayTansaction(incomeName_TF.getText(), new Date(), amount, typeId, notes, ApplicationContext.fridage.getId());
			alert(AlertType.INFORMATION, "", "", this.getMessage("msg.done.save"));
	   
	   }catch (Exception ex) {
	    	   alert(AlertType.ERROR, this.getMessage("msg.err"),this.getMessage("msg.err"), this.getMessage("msg.err.general"));

	
		}
		
		
		
		
		
	
		
		
		
	}


    void intiateInPage() {
        this.incomeAmount_TF.setText("");
        this.incomeName_TF.setText("");
        this.incomeNotes_TA.setText("");
        fillIncomeMonthes();
       // refreshBalance();

    }
    

    boolean validateOutForm() {
        String amount = outcomeAmount_TF.getText();

        String name = outcomeName_TF.getText();
        int typeId = outcomeType_combo.getSelectionModel().getSelectedItem().getValue();
        double safaBalance=this.getExpansesServices().getSafeBalance(ApplicationContext.season.getId());
        if (amount.isEmpty()) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.amount"));
        	return false;

        } else if (safaBalance<Double.parseDouble(amount)) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.notEnough.safeBalance"));

            return false;
        } else if (typeId == OutcomeTypeEnum.OUT_PAY_LOAN) {
        	
            LoanAccount account = this.getExpansesServices().getLoanerAccount(name);

        	
            if (name.isEmpty()) {
            	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.name"));

                return false;
 
            }

            if (account == null) {
            	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.notfound.name"));

                return false;

            }
            if (account.getDueAmount() > 0 && account.getType().equals("OUT_LOAN")) {
            	alert(AlertType.WARNING,this.getMessage("msg.warning"),this.getMessage("msg.warning"),this.getMessage("msg.err.amountShouldBecollestedFromLoaner")+" : "+account.getDueAmount());

                return false;

            }
            if (Double.parseDouble(amount) > account.getDueAmount()) {
            	alert(AlertType.WARNING,this.getMessage("msg.warning"),this.getMessage("msg.warning"),this.getMessage("msg.err.input.amount.greather")+" : "+account.getDueAmount());

                return false;
            }
        } 
        return true;

    }
 
    boolean validateInForm() {
        String name = incomeName_TF.getText();
        String inAmount = incomeAmount_TF.getText();
        LoanAccount account = this.getExpansesServices().getLoanerAccount(name);

        if (name.isEmpty()) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.name"));

            return false;
        } else if (inAmount.isEmpty()) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.required.amount"));

            return false;
        }

        if (account == null) {

        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("msg.err.notfound.name"));
            return false;//err.notfound.name
 
        }

        if (Double.parseDouble(inAmount) > 0 && account.getType().equals("IN_LOAN")) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage("err.amountShouldBePayedFromLoaner"));

            return false;

        }
        if (Double.parseDouble(inAmount) > account.getDueAmount()) {
        	alert(AlertType.ERROR,this.getMessage("msg.err"),this.getMessage("msg.err"),this.getMessage(" msg.err.input.amount.greather"));

            return false;
        } 
        
        return true;

    }

   
}
