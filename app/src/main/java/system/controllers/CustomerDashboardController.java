package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomerDashboardController implements Initializable {
	@FXML
	private Button logout_btn;
	
	@FXML
	public void onClickLogout(ActionEvent event) {
		
		try {
			Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Pharmacy Management System.fxml"));
		
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
			
			
		}catch(Exception except) {
			except.printStackTrace();
			System.out.println("Error while loading the Chemist Dashboard");
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
