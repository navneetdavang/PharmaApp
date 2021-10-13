package system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.beans.Medicine;
import system.daos.MedicineDao;

public class ChemistDashboardController implements Initializable {
	
	@FXML
	private Label admin_label;
	
	@FXML
	private Button logout_btn;
	
	@FXML 
	private Button add_medicine_btn;
	
	@FXML
    private TableView<Medicine> medicine_table;
	
	@FXML
    private TableColumn<Medicine, String> medicine_name;

    @FXML
    private TableColumn<Medicine, Integer> medicine_price;

    @FXML
    private TableColumn<Medicine, Integer> medicine_stock;


    @FXML
    private TableColumn<Medicine, Integer> sr_no;
    
	ObservableList<Medicine> med_list = FXCollections.observableArrayList();
	

    
	
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
		
//		Stage stage = new Stage();
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);		
		stage.setResizable(false);
		stage.setTitle("Add Medicine");
		stage.show();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTableView();
		
	}
	
	public void updateTableView() {
		// setting the property value for columns
		sr_no.setCellValueFactory(new PropertyValueFactory<>("id"));
		medicine_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		medicine_stock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		medicine_price.setCellValueFactory(new PropertyValueFactory<>("price"));

		
		// loading data into med_list
		med_list = MedicineDao.getAllMedicine();
		
		if(med_list == null) {
			medicine_table.setPlaceholder(new Label("There are no Medicines to display"));
		}else {
			medicine_table.setItems(med_list);
		}
				
	}
	

}
