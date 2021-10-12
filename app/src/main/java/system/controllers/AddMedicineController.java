package system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import system.beans.Medicine;
import system.services.MedicineServices;
import system.utils.AlertUtils;

public class AddMedicineController {

	@FXML
	private Button add_btn;
	
	@FXML
	private TextField medicine_name; 
	
	@FXML
	public TextField medicine_quantity;
	
	@FXML
	private TextField medicine_price;
	
	@FXML
	private Button clear_fields_btn;
	
	@FXML
	public void onClickAdd(ActionEvent event) {
		
		String med_name = medicine_name.getText().toString().trim();
		String med_quant = medicine_quantity.getText().toString().trim();
		String med_price = medicine_price.getText().toString().trim();
		
		if(med_name.equals("")|| med_quant.equals("") || med_price.equals("")) {
			AlertUtils.showAlert("Please fill all the Details", AlertType.WARNING).show();
		}else {
			
			Medicine medicine = new Medicine(med_name, 
					Integer.parseInt(med_quant), 
					Integer.parseInt(med_price));
		
			if(MedicineServices.addMedicine(medicine)) {
				if(AlertUtils.showAlert("Medicine added Successfully:)", AlertType.INFORMATION)
						.showAndWait().get() == ButtonType.OK);{
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.close();
				}
			}else {
				AlertUtils.showAlert("Unable to add medicine", AlertType.ERROR).show();
			}
		}
		
	}
	
	@FXML
	public void onClickClearFields(ActionEvent event) {
		this.medicine_name.clear();
		this.medicine_quantity.clear();
		this.medicine_price.clear();
	}
	
	
	// force the field to be numeric only
}
