package system.controllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
	private Button customer_btn;
	
	@FXML
	public void onClickCustomer(ActionEvent e) throws IOException {
		System.out.println("Clicked Customer Btn");
		
		// switching to the login page 
		Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Customer Login.fxml"));
		
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Customer Login");
		stage.show();
	}
	
	
	
}
