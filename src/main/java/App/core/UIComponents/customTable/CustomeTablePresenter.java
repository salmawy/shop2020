package App.core.UIComponents.customTable;

import java.net.URL;
import java.util.ResourceBundle;

import App.com.application.view.ApplicationView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomeTablePresenter implements Initializable  {

	
	
    @FXML
    private HBox head;

    @FXML
    private AnchorPane rootAnchorePan;

    @FXML
    private VBox tableContainer;
	
	
	
	
	
	
	
	
	
	
	public void setTableContainer(VBox tableContainer) {
		this.tableContainer = tableContainer;
	}










	public HBox getHead() {
		return head;
	}










	public void setHead(HBox head) {
		this.head = head;
	}










	public AnchorPane getRootAnchorePan() {
		return rootAnchorePan;
	}










	public void setRootAnchorePan(AnchorPane rootAnchorePan) {
		this.rootAnchorePan = rootAnchorePan;
	}




















	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	@FXML 
	
	private void loadDischargePanel() {
		
	    ApplicationView appView=new ApplicationView();
	    Scene scene=new Scene(appView.getView());
		
		
		
		
		
	}
	
	
	
	
	
}
