package system.controllers;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import system.beans.Chemist;
import system.services.LoginServices;
import system.utils.AlertUtils;

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
		this.username_field.clear();
		this.password_field.clear();
	}
	
	@FXML 
	public void onClickLogin(ActionEvent e) {
		
		String username = username_field.getText().trim();
		String password = password_field.getText().trim();
		
		if(username.equals("") || password.equals("")) {
			AlertUtils.showAlert("Please Fill all the Details", AlertType.WARNING).show();
		}else {
			Chemist chemist = LoginServices.chemistLoginService(username, password);
			
			if(chemist == null)
				AlertUtils.showAlert("Please enter valid Username & Password", AlertType.ERROR).show();
			else {
				
				if(AlertUtils.showAlert("Login Successfully:)", AlertType.INFORMATION).
						showAndWait().get() == ButtonType.OK) {;
					Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
					loadChemistDashboard(stage);
				}
				
				  
			}
		}	
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
