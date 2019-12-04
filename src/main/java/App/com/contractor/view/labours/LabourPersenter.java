package App.com.contractor.view.labours;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import App.com.contractor.action.ContractorAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LabourPersenter extends ContractorAction implements Initializable {
	
	
	


    @FXML
    private Label owner1Value_label;

    @FXML
    private JFXTextField name_TF;

    @FXML
    private Label owner1_label;

    @FXML
    private Label shopValue_label;

    @FXML
    private Pane shop_coloredPane;

    @FXML
    private Label shop_label;

    @FXML
    private AnchorPane transactions_table;

    @FXML
    private Pane owner_coloredPane;

    @FXML
    private AnchorPane contractotTable_loc;

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
