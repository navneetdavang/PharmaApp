package system.controllers;

import java.io.IOException;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChemistDashboardController {
	
	@FXML
	private Button logout_btn;
	
	@FXML 
	private Button add_medicine_btn;
	
	@FXML
	private TableView medicine_table;
	
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
	
	@FXML
	public void onClickAddMedicine(ActionEvent event) throws IOException {
		
		System.out.println("Clicked on Add Medicine Button");
		
		Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Add Medicine.fxml"));
		
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.setResizable(false);
		stage.setTitle("Add Medicine");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
		
	}
	
	

}
