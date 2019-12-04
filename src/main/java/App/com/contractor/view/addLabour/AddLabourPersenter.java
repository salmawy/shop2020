package App.com.contractor.view.addLabour;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import App.com.contractor.action.ContractorAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class AddLabourPersenter extends ContractorAction implements Initializable {
	  @FXML
	    private HBox paid_toogleBtn;

	    @FXML
	    private JFXButton cancel_btn;

	    @FXML
	    private JFXTextField name_TF;

	    @FXML
	    private Label name_label;

	    @FXML
	    private Label amount_label;

	    @FXML
	    private JFXTextArea note_TA;

	    @FXML
	    private JFXTextField amount_TF;

	    @FXML
	    private Label date_label;

	    @FXML
	    private AnchorPane root_pane;

	    @FXML
	    private Pane coloredPane;

	    @FXML
	    private Label title_label;

	    @FXML
	    private HBox datePicker_loc;

	    @FXML
	    private JFXButton saveBtn;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
