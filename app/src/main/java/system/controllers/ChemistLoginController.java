package system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import system.beans.Chemist;
import system.services.LoginServices;

public class ChemistLoginController {
	
	@FXML
	private Button login_clear_btn;
	
	@FXML
	private Button login_btn;
	
	@FXML 
	private TextField username_field;
	
	@FXML
	private PasswordField password_field;
	
	
	// to claer the all fields in the login form
	@FXML 
	public void clearTextField(ActionEvent e) {
		username_field.clear();
		password_field.clear();
	}
	
	@FXML 
	public void onClickLogin(ActionEvent e) {
		
		String username = username_field.getText().trim();
		String password = password_field.getText().trim();
		
		if(username.equals("") || password.equals("")) {
			showAlert("Please Fill all the Details", AlertType.WARNING);
		}else {
			Chemist chemist = LoginServices.chemistLoginService(username, password);
			
			if(chemist == null)
				showAlert("Please enter valid username & password", AlertType.ERROR);
			else {
				showAlert("Login Successfully:)", AlertType.INFORMATION);
				Stage stage = (Stage) login_btn.getScene().getWindow();
				stage.close();
			}
		}
		

		
		
	}
	
	public void showAlert(String msg, AlertType type) {
		Alert alert = new Alert(type);
		alert.setContentText(msg);
		alert.show();
	}
	

}
