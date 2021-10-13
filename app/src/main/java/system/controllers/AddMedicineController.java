package system.controllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
						.showAndWait().get() == ButtonType.OK){
					
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					loadChemistDashboard(stage);
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
	
	
	// method to load the chemist dashboard
	public void loadChemistDashboard(Stage stage) {
			
			try {
				Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Chemist Dashboard.fxml"));
			
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("Medicore");
			stage.setResizable(false);
			stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading the Chemist Dashboard");
		}
		
	}
	
}
