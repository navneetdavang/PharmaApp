package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import system.beans.Medicine;
import system.services.MedicineServices;
import system.utils.AlertUtils;
import system.utils.ValidationUtils;
import system.utils.ViewLoaderUtils;

public class AddMedicineController implements Initializable{

	@FXML
	private Hyperlink back_btn;
	
	@FXML
	private Button add_btn;
	
	@FXML
	private TextField medicine_name; 
	
	@FXML
	public TextField medicine_stock;
	
	@FXML
	private TextField medicine_price;
	
	@FXML
	private Button clear_fields_btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// medicine_stock listner
		medicine_stock.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateContact(newValue)) {
					medicine_stock.setText(oldValue);
				}
			}
			
		});
		
		// medicine_stock listner
		medicine_price.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateContact(newValue)) {
					medicine_price.setText(oldValue);
				}
			}
			
		});


	}
	
	
	@FXML
	public void onClickAdd(ActionEvent event) {
		
		String med_name = medicine_name.getText().toString().trim();
		String med_quant = medicine_stock.getText().toString().trim();
		String med_price = medicine_price.getText().toString().trim();
		
		if(med_name.equals("")|| med_quant.equals("") || med_price.equals("")) {
			AlertUtils.showAlert("Please fill all the Details", AlertType.WARNING).show();
		}else {
			
			Medicine medicine = new Medicine(med_name, 
					Integer.parseInt(med_quant), 
					Integer.parseInt(med_price));
		
			if(MedicineServices.addMedicine(medicine)) {
				if(AlertUtils.showAlert("Medicine added Successfully:)", AlertType.INFORMATION)
						.showAndWait().get() == ButtonType.OK){
					
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					ViewLoaderUtils.loadChemistDashboard(stage);
				}
			}else {
				AlertUtils.showAlert("Unable to add medicine", AlertType.ERROR).show();
			}
		}
		
	}
	
	// on click back redirect to chemist dashboard
	@FXML
	public void onClickBack(ActionEvent e) {
		ViewLoaderUtils.loadChemistDashboard(((Stage)((Node)e.getSource()).getScene().getWindow()));
	}
	
	@FXML
	public void onClickClearFields(ActionEvent event) {
		this.medicine_name.clear();
		this.medicine_stock.clear();
		this.medicine_price.clear();
	}

	
	
	
	
}
