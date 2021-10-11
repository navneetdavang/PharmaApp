package system.controllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerLoginController {

	@FXML 
	private Hyperlink register_page_hyperlink;
	
	
	@FXML 
	public void onClickRegisterHyperlink(ActionEvent e)throws IOException {
		System.out.println("Clicked Registration Page link");
		
		// switching the registration page
		Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Customer Registration.fxml"));
		
		Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Customer Registration");
		stage.show();
	}
}
