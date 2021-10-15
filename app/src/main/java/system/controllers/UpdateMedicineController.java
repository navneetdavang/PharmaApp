package system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import system.utils.ViewLoaderUtils;

public class UpdateMedicineController {
	
	@FXML
	private Hyperlink back_btn;
	
	@FXML
	private Button clear_fields_btn;
	
	@FXML
	private Button update_btn;
	
	@FXML
	private TextField medicine_name;
	
	@FXML
	private TextField medicine_stock;
	
	@FXML
	private TextField medicine_price;
	
	@FXML
	public void onClickUpdate(ActionEvent event) {
		
		String med_name = medicine_name.getText().toString().trim();
		String med_quant = medicine_stock.getText().toString().trim();
		String med_price = medicine_price.getText().toString().trim();
		
		if(med_quant.equals("") || med_price.equals("")) {
			AlertUtils.showAlert("Please fill all the Details", AlertType.WARNING).show();
		}else {
			
			Medicine medicine = new Medicine(med_name, 
					Integer.parseInt(med_quant), 
					Integer.parseInt(med_price));
		
			if(MedicineServices.updateMedicine(medicine)) {
				if(AlertUtils.showAlert("Medicine updated Successfully:)", AlertType.INFORMATION)
						.showAndWait().get() == ButtonType.OK){
					
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					ViewLoaderUtils.loadChemistDashboard(stage);
				}
			}else {
				AlertUtils.showAlert("Unable to update medicine", AlertType.ERROR).show();
			}
		}
	}
	

	// on click back redirect to chemist dashboard
	@FXML
	public void onClickBack(ActionEvent e) {
		ViewLoaderUtils.loadChemistDashboard(((Stage)((Node)e.getSource()).getScene().getWindow()));
	}
	
	@FXML
	public void onClickClearFields(ActionEvent e) {
		medicine_stock.clear();
		medicine_price.clear();
	}
	
	public void setFields(Medicine med) {
		medicine_name.setText(med.getName());
		medicine_stock.setText(String.valueOf(med.getStock()));
		medicine_price.setText(String.valueOf(med.getPrice()));
	}
	
	
	
}
