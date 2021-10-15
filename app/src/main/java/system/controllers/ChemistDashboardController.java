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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.beans.Medicine;
import system.daos.MedicineDao;
import system.services.MedicineServices;
import system.utils.AlertUtils;
import system.utils.ViewLoaderUtils;

public class ChemistDashboardController implements Initializable {
	
	@FXML
	private Label admin_label;
	
	@FXML
	private Button logout_btn;
	
	@FXML 
	private Button add_medicine_btn;
	
	@FXML 
	private Button remove_medicine_btn;
	
	@FXML 
	private Button update_medicine_btn;
	
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
		
		if(AlertUtils.showAlert("Do you want to logout?", AlertType.CONFIRMATION)
				.showAndWait().get() == ButtonType.OK) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			ViewLoaderUtils.loadHomeWindow(stage);
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
		medicine_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		medicine_price.setCellValueFactory(new PropertyValueFactory<>("price"));

		
		// loading data into med_list
		med_list = MedicineDao.getAllMedicine();
		
		if(med_list == null) {
			medicine_table.setPlaceholder(new Label("There are no Medicines to display"));
		}else {
			medicine_table.setItems(med_list);
		}
				
	}
	
	
	// method to remove medicine on click
	@FXML
	public void onClickRemoveMedicine(ActionEvent event) {
		System.out.println("Cliked on Remove Medicine Button");
		
		// checking if the user selected the row or not
		Medicine med = medicine_table.getSelectionModel().getSelectedItem();
		if(med == null)
			AlertUtils.showAlert("Please select the medicine in the row to remove", AlertType.WARNING).show();
		else {
			
			if(MedicineServices.deleteMedicine(med)) {
				try {
				medicine_table.getItems().removeAll(med);
				}catch(Exception e) {
					System.out.println("Error while deleting the row in the gui");
				}
				AlertUtils.showAlert("Medicine Deleted Sucessfully:)", AlertType.INFORMATION).show();
			}else {
				AlertUtils.showAlert("Unable to Delete the Medicine", AlertType.WARNING).show();
			}
			
		}
	
	}
	
	// method for onClick Update button
	@FXML 
	public void onClickUpdateMedicine(ActionEvent event) throws IOException {
		System.out.println("Clicked on Update Medicine Button");
		
		Medicine med = medicine_table.getSelectionModel().getSelectedItem();
		
		
		// if there is no medicine is selected show the alert
		if(med == null) {
			AlertUtils.showAlert("Please select the medicine in the row to update", AlertType.WARNING).show();
		}else {
			
			FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/fxmls/Update Medicine.fxml"));		
			Parent root = loader.load();
			UpdateMedicineController controller = loader.getController();
			controller.setFields(new Medicine(med));
			
//			Stage stage = new Stage();
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);		
			stage.setResizable(false);
			stage.setTitle("Update Medicine");
			stage.show();
		}
		
		
		
	}
	
	

}
