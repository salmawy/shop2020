package App.com.billing.view.invoicePayment;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import App.com.billing.action.BillingAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class InvoicePaymentPersenter extends BillingAction implements Initializable {
	@FXML
    private JFXButton suggestedInvoices_btn;

    @FXML
    private JFXTextField name_TF;

    @FXML
    private AnchorPane invoicesTable_table;

    @FXML
    private AnchorPane customersTable_loc;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
