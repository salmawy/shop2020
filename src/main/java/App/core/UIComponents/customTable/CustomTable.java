/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.core.UIComponents.customTable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.kordamp.bootstrapfx.scene.layout.Panel;

import com.jfoenix.controls.JFXButton;


import App.com.Customer.discharge.view.CustomerViewBean;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;



/**
 *
 * @author salmawy
 * 
 *         cutsome tabel is not controller so i can send parameters to it
 */
public class CustomTable<RowClass>  {

	private FlowPane head;
	private VBox tableContainer;
	private AnchorPane cutomTableComponent;
	private TableView<RowClass> table;
	private CheckBox selectAllCheckBox;
	private Class myclass;
	private final String actionsPanelId = "actionsPanel";
	private final String confirmActionPanelId = "confirmActionPanel";
	private final String tableContainerId = "tableContainer";

	public CustomTable(List columns, List buttons, List keyColumns, List<RowClass> data, CustomTableActions actions) {
		myclass=CustomerViewBean.class;
	table = new <RowClass>TableView();
	table.setMinWidth(1000);	
	table.setPrefWidth(1000);	

		    CustomeTableView tableView=new CustomeTableView();
			this.cutomTableComponent=(AnchorPane) tableView.getView();
			Panel myPanel=(Panel) cutomTableComponent.getChildren().get(0);
			head= (FlowPane) myPanel.getTop();
			
			
			
			ScrollPane 	scrollPane = (ScrollPane)myPanel.getCenter();
			scrollPane.setMaxWidth(1100);
			scrollPane.setMaxHeight(400);
			
			
			
			tableContainer=new VBox();
			tableContainer.setPrefSize(700, 600);
			
			scrollPane.setContent(tableContainer);			
		

			
		setColumnsConfiguration(columns);
		setButtonsConfiguration(buttons);
		
		if(data!=null&&data.size()>0) {
			
			
			loadTableData(data);
			
		}
			

	
	System.out.print("custom table has been loaded succeffuly ");
	
	}



	private void setColumnsConfiguration(List columns) {

	

		for (int i = 0; i <columns.size(); i++) {

			Column column = (Column) columns.get(i);
			
			if(column.getId().equals("chk")){
				
				TableColumn<RowClass, Boolean> selectedCol = new TableColumn<RowClass, Boolean>();
				selectedCol.setMinWidth(50);
				selectedCol.setGraphic(getSelectAllCheckBox());
				selectedCol.setCellValueFactory(new PropertyValueFactory<RowClass, Boolean>(column.getId()));
				selectedCol.setCellFactory(new Callback<TableColumn<RowClass, Boolean>, TableCell<RowClass, Boolean>>() {
					public TableCell<RowClass, Boolean> call(TableColumn<RowClass, Boolean> p) {
						final TableCell<RowClass, Boolean> cell = new TableCell<RowClass, Boolean>() {
							@Override
							public void updateItem(final Boolean item, boolean empty) {
								if (item == null)
									return;
								super.updateItem(item, empty);
								if (!isEmpty()) {
									final RowClass item1 = getTableView().getItems().get(getIndex());
									CheckBox checkBox = new CheckBox();
									
								
									BooleanProperty mychk=(BooleanProperty) invokeMethode(item1, "chkProperty");
									
									checkBox.selectedProperty().bindBidirectional(mychk);
									setGraphic(checkBox);
								}
							}
						};
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
				
				
				selectedCol.setPrefWidth(column.getSize());
				selectedCol.setVisible(column.getShow());
						 
				 
				selectedCol.prefWidthProperty().bind(table.widthProperty()
				            .multiply(column.getSize() / 100.0)
				            .subtract(((table.getInsets().getLeft() + table.getInsets().getRight()) / columns.size())));	
			
			
				table.getColumns().add(i, selectedCol);
			}
			
			else {
			TableColumn col = new TableColumn(column.getName());
			 col.setPrefWidth(column.getSize());
			 col.setVisible(column.getShow());
					 
			 
			 col.prefWidthProperty().bind(table.widthProperty()
			            .multiply(column.getSize() / 100.0)
			            .subtract(((table.getInsets().getLeft() + table.getInsets().getRight()) / columns.size())));
		
			 col.setCellValueFactory(new PropertyValueFactory<RowClass, String>(column.getId()));
			table.getColumns().add(i, col);
			}

		}

		
	table.setNodeOrientation(table.getNodeOrientation().RIGHT_TO_LEFT) ;

	
		tableContainer.getChildren().removeAll();
		tableContainer.getChildren().addAll(table);

	}

	private void setButtonsConfiguration(List buttons) {

		this.head.getChildren().removeAll();
		for (int i = 0; i < buttons.size(); i++) {
			JFXButton btn=(JFXButton) buttons.get(i);
			this.head.getChildren().add( btn );

		}

	}

	public void loadTableData(List<RowClass> data) {

		ObservableList<RowClass> observableList = FXCollections.observableArrayList(data);
		this.table.setItems(observableList);

	}

	/**
	 * @return the actionsPanel
	 */
	public FlowPane getActionsPanel() {
		return head;
	}

	/**
	 * @param actionsPanel the actionsPanel to set
	 */
	public void setActionsPanel(FlowPane actionsPanel) {
		this.head = actionsPanel;
	}

	/*    *//**
			 * @return the tableContainer
			 *//*
				 * public JFXScrollPane getTableContainer() { return tableContainer; }
				 */

	/**
	 * @param tableContainer the tableContainer to set
	 */
	public void setTableContainer(VBox tableContainer) {
		this.tableContainer = tableContainer;
	}

	/**
	 * @return the table
	 */
	public TableView getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */

	public AnchorPane getCutomTableComponent() {
		return cutomTableComponent;
	}

	public void setCutomTableComponent(AnchorPane cutomTableComponent) {
		this.cutomTableComponent = cutomTableComponent;
	}

	public String getActionsPanelId() {
		return actionsPanelId;
	}

	public String getConfirmActionPanelId() {
		return confirmActionPanelId;
	}

	public String getTableContainerId() {
		return tableContainerId;
	}

	public void setTable(TableView<RowClass> table) {
		this.table = table;
	}
	
	
	/**
	 * Lazy getter for the selectAllCheckBox.
	 * 
	 * @return selectAllCheckBox
	 */
	public CheckBox getSelectAllCheckBox() {
		if (selectAllCheckBox == null) {
			final CheckBox selectAllCheckBox = new CheckBox();

			// Adding EventHandler to the CheckBox to select/deselect all employees in table.
			selectAllCheckBox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Setting the value in all the employees.
					for (RowClass item : table.getItems()) {
					invokeMethode(item, "setChk",selectAllCheckBox.isSelected());	
					}
				}
			});

			this.selectAllCheckBox = selectAllCheckBox;
		}
		return selectAllCheckBox;
	}

	

	private Object invokeMethode(Object instance,String methodeName) {
		Object returnObj=null;
		try {
		Method methode= myclass.getMethod(methodeName);
		returnObj=	methode.invoke(instance);
		
		
		
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return returnObj;

		
		
		
	}
	private Object invokeMethode(Object instance,String methodeName,Object arg) {
		
		Object returnObj=null;
		try {
			Method methode= myclass.getMethod(methodeName,boolean.class);

			returnObj =methode.invoke(instance,arg);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return returnObj;
		
		
		
		
	}
}
