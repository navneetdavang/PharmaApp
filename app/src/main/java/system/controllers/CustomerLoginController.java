package system.controllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.beans.Customer;
import system.services.LoginServices;
import system.utils.AlertUtils;

public class CustomerLoginController {

	@FXML
	private Button login_clear_btn;
	
	@FXML
	private Hyperlink register_page_hyperlink;
	
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
			AlertUtils.showAlert("Please Fill all the Details", AlertType.WARNING).show();
		}else {
			Customer customer = LoginServices.customerLoginService(username, password);
			
			if(customer == null)
				AlertUtils.showAlert("Please Enter Valid Username/Email & Password", AlertType.ERROR).show();
			else {
				AlertUtils.showAlert("Login Successfully:)", AlertType.INFORMATION).show();
				Stage stage = (Stage) login_btn.getScene().getWindow();
				loadCustomerDashboard(stage);
			}
		}
		
	}
	
	@FXML 
	public void onClickRegisterHyperlink(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Customer Registration.fxml"));
		
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
	
		stage.setResizable(false);
		stage.setTitle("Chemist Registration");
		stage.show();
	}
	
	public void loadCustomerDashboard(Stage stage) {
		try {
			Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Customer Dashboard.fxml"));
			
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
