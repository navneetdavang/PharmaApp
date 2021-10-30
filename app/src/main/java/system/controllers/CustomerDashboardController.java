package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.beans.CartItem;
import system.beans.Customer;
import system.beans.Medicine;
import system.daos.MedicineDao;
import system.services.BillServices;
import system.services.MedicineServices;
import system.utils.AlertUtils;
import system.utils.ValidationUtils;
import system.utils.ViewLoaderUtils;

public class CustomerDashboardController implements Initializable {
	
	private Customer customer;
	
    @FXML
    private Button add_cart_btn;
    
    @FXML 
    private Button remove_item_btn;

    @FXML
    private TableColumn<CartItem, String> cart_medicine_name;

    @FXML
    private TableColumn<CartItem, Integer> cart_price;

    @FXML
    private TableColumn<CartItem, Integer> cart_quantity;

    @FXML
    private TableColumn<CartItem, Integer> cart_sr_no;

    @FXML
    private TableView<CartItem> cart_table;

    @FXML
    private TableColumn<CartItem, Integer> cart_total;

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
    
    // fx arraylist collection to hold the cart items
    ObservableList<Medicine> cart_list = FXCollections.observableArrayList();
   
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//

		// listner for quantity_field
		quantity_field.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateContact(newValue)) {
					quantity_field.setText(oldValue);
				}
			}
			
		});
		
		
		// searching the medicine
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
		
			f_med_list.setPredicate(p -> p.getName().toLowerCase().contains(newValue.toLowerCase().trim()));
		});
		
		
		total_amount_field.textProperty().addListener((observable, oldValue, newValue) -> {
			
			System.out.println(newValue);
			if(Integer.parseInt(newValue.toString()) > 0) {
				pay_btn.setDisable(false);
			}else {
				pay_btn.setDisable(true);
			}
		});
		
		// setting the total amount field to 0;
		total_amount_field.setText("0");
	
		
		// cart table updating
		updateCartTable();
		
		// medicine table updating
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
		medicine_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		medicine_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		med_list = MedicineDao.getAllMedicine();
		
		if(med_list == null) {
			medicine_table.setPlaceholder(new Label("There are no Medicines to display"));
		}else {
			f_med_list = new FilteredList<Medicine>(med_list, p->p.getStock() >= 1);
			medicine_table.setItems(f_med_list);
		}
		
	}
	
	// event handler on click add to cart 
	@FXML
	public void onClickAddToCart(ActionEvent event) {
		System.out.println("Clicked on add to cart btn");
		
		// checking if the user has selected the row 
		Medicine med = medicine_table.getSelectionModel().getSelectedItem();
		int selected_row_idx = medicine_table.getSelectionModel().getSelectedIndex();
		
		if(med == null) {
			AlertUtils.showAlert("Please select the medicine in the row to add", AlertType.WARNING).show();
		}else {
			
			String quantity_string = quantity_field.getText().toString().trim();
			if(quantity_string.equals("")) {
				AlertUtils.showAlert("Please enter the quantity of medicine you required", AlertType.WARNING).show();
			}else {
				System.out.println(quantity_string);
				
				// checking if the quantity is less than or equal to stock
				int quantity = Integer.parseInt(quantity_string.toString());
				
				
				
				if(med.getStock() == 0) {
					// if stock is empty
					AlertUtils.showAlert("Medicine is out of stock now!!!", AlertType.WARNING).show();
					
				}else if(quantity <= med.getStock()) {
					
					// adding the item to the cart
					quantity_field.clear();
					
					// decreasing the stock
					medicine_table.getItems().get(selected_row_idx)
					.setStock(medicine_table.getItems().get(selected_row_idx).getStock() - quantity);
					
					
					CartItem cartItem = new CartItem(med, quantity);
					
					// checking if the item is already there or not
					int loc = -1;
					for(int i = 0; i < cart_table.getItems().size(); i++) {
						if(cart_table.getItems().get(i).getName().equals(cartItem.getName())) {
							loc = i;
							break;
						}
					}
					
					if(loc != -1) {
						quantity += cart_table.getItems().get(loc).getQuantity();
						cartItem.setQuantity(quantity);
						
						ObservableList<CartItem> cartItems = cart_table.getItems();
						cartItems.set(loc, cartItem);
						
					}else {
						cart_table.getItems().add(cartItem);
					}
						
					
					int total_amount = 0;
                    
                    for(int i = 0; i < cart_table.getItems().size(); i++) {
                    	total_amount += cart_table.getItems().get(i).getTotal();
                    }
                    
                    total_amount_field.setText(total_amount + "");
					
					
				}else {
					AlertUtils.showAlert("We don't have that much stock", AlertType.WARNING).show();
				}
				
			}
		}
		
	}
	
	// event handler for click action on clear quantity btn
	@FXML
	public void onClickClearQuantity(ActionEvent event) {
		quantity_field.clear();
	}
	
	// on click remove item delete the cart item
	@FXML
	public void onClickRemoveItem(ActionEvent e) {
		
		// checking if the user has selected the item in the cart or not
		CartItem cartItem = cart_table.getSelectionModel().getSelectedItem();
		if(cartItem == null) {
			AlertUtils.showAlert("Please select the item in the cart to remove", AlertType.WARNING).show();
		}else {
			cart_table.getItems().remove(cartItem);
			
			int total_amount = Integer.parseInt(total_amount_field.getText().toString());
            
			total_amount -= cartItem.getTotal();
            
            total_amount_field.setText(total_amount + "");
            f_med_list = null;
            updateMedicineTable();
		}
		
	}
	
	// event handler for click action on clear cart table
	@FXML 
	public void onClickClearCart(ActionEvent event) {
		cart_table.getItems().clear();
		total_amount_field.setText(0 + "");
	}
	
	// evnet handler for click action on pay
	@FXML 
	public void onClickPay(ActionEvent event) {
		
		if(AlertUtils.showAlert("Do you want to Pay?", AlertType.CONFIRMATION)
				.showAndWait().get() == ButtonType.OK) {
			int total_amount = Integer.parseInt(total_amount_field.getText().toString());
			if(BillServices.addBill(customer.getId(), total_amount)) {
				
				// updating the stock 
				cart_table.getItems().stream().forEach(item->{
					MedicineServices.updateMedicine(item.getName(), item.getQuantity());
				});
				
				cart_table.getItems().clear();
				if(AlertUtils.showAlert("Payment Done Successfully", AlertType.INFORMATION)
						.showAndWait().get() == ButtonType.OK) {
					updateMedicineTable();
					total_amount_field.setText(0 + "");
				}
			}
		}
			
	}
	
	
	public void setCustomer(Customer cust) {
		customer = new Customer(cust);
		System.out.println("cust id ; " + cust.getId());
		System.out.println("customer id ; " + customer.getId());
		customer_username_txt.setText(customer.getEmailId());;
	}
	
	// method to update the cartitems 
	public void updateCartTable() {
		
		// setting the property value for the cart table columns
		cart_sr_no.setCellValueFactory(new PropertyValueFactory<>("id"));
		cart_medicine_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		cart_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		cart_price.setCellValueFactory(new PropertyValueFactory<>("price"));
		cart_total.setCellValueFactory(new PropertyValueFactory<>("total"));
		
	}

	
}
