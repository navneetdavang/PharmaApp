package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.beans.Customer;
import system.beans.Medicine;
import system.daos.MedicineDao;
import system.utils.AlertUtils;
import system.utils.ViewLoaderUtils;

public class CustomerDashboardController implements Initializable {
	
	private static Customer customer;
	
    @FXML
    private Button add_cart_btn;

    @FXML
    private TableColumn<?, ?> cart_medicine_name;

    @FXML
    private TableColumn<?, ?> cart_price;

    @FXML
    private TableColumn<?, ?> cart_quantity;

    @FXML
    private TableColumn<?, ?> cart_sr_no;

    @FXML
    private TableView<?> cart_table;

    @FXML
    private TableColumn<?, ?> cart_total;

    @FXML
    private Button clear_cart_btn;

    @FXML
    private Button clear_quantity_field;

    @FXML
    private Text customer_username_txt;

    @FXML
    private Button logout_btn;

    @FXML
    private Button pay_btn;

    @FXML
    private TextField quantity_field;

    @FXML
    private TextField search_field;

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

    @FXML
    private TextField total_amount_field;
    
    // fx arraylist collection to hold the medicine
    ObservableList<Medicine> med_list = FXCollections.observableArrayList();
    FilteredList<Medicine> f_med_list ;
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
		
			f_med_list.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));
		});
		
		updateMedicineTable();
	}
	
	
	@FXML
	public void onClickLogout(ActionEvent e) {
		
		if(AlertUtils.showAlert("Do you want to logout?", AlertType.CONFIRMATION)
				.showAndWait().get() == ButtonType.OK) {
			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			ViewLoaderUtils.loadHomeWindow(stage);
		}

	}
	
	
	// method to set the medicine table 
	public void updateMedicineTable() {
		// setting the property value for columns
		sr_no.setCellValueFactory(new PropertyValueFactory<>("id"));
		medicine_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		medicine_stock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		medicine_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		med_list = MedicineDao.getAllMedicine();
		
		if(med_list == null) {
			medicine_table.setPlaceholder(new Label("There are no Medicines to display"));
		}else {
			f_med_list = new FilteredList<Medicine>(med_list, p->true);
			medicine_table.setItems(f_med_list);
		}
		
	}
	
	
	public void setCustomer(Customer cust) {
		customer = new Customer(cust);
		customer_username_txt.setText(customer.getEmailId());;

	}
	
}
