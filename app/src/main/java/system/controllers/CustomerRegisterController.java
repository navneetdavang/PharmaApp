package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import system.beans.Customer;
import system.services.LoginServices;
import system.services.RegistrationServices;
import system.utils.AlertUtils;
import system.utils.ValidationUtils;

public class CustomerRegisterController implements Initializable{
	@FXML
	private Button reg_btn;
	
	@FXML
	private TextField fname_field;
	
	@FXML
	private TextField lname_field;
	
	@FXML
	private TextField contact_field;
	
	@FXML
	private TextField email_field;
	
	@FXML
	private TextField password_field;
	
	@FXML
	private TextField cpassword_field;
	
	@FXML
	private Button clear_btn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// listner for first name
		fname_field.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateName(newValue)) {
					fname_field.setText(oldValue);
				}
			}
			
		});

		
		// listner for last name
		lname_field.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateName(newValue)) {
					lname_field.setText(oldValue);
				}
			}
			
		});
		
		// listner for contact field
		contact_field.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if(!new ValidationUtils().validateContact(newValue)) {
					contact_field.setText(oldValue);
				}
			}
			
		});
		
		contact_field.focusedProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
			
				if(oldValue) {	
					if(contact_field.getText().toString().trim().length() < 10) {
						AlertUtils.showAlert("Invalid Contact No.", AlertType.ERROR).show();
						contact_field.clear();
					}		
				}
			}
		});
		
		// listner for email validation 
		email_field.focusedProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(email_field.getText().toString().trim().length() > 0) {
					if(oldValue) {
						if(!new ValidationUtils().validateEmail(email_field.getText().toString().trim())) {
							AlertUtils.showAlert("Invalid Email ID", AlertType.ERROR).show();	
						}	
					}
				}
				
			}
			
		});
		
		
		// listner for password validation 
		password_field.focusedProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if(oldValue && password_field.getText().toString().trim().length() > 0) {
					if(!new ValidationUtils().validatePassword(password_field.getText().toString().trim())) {
						if(AlertUtils.showAlert("Weak Password", AlertType.WARNING).showAndWait().get()
								== ButtonType.OK) {
							password_field.clear();
						}
					}	
				}
			}
			
		});
		

	}
	
	@FXML 
	public void clearTextField(ActionEvent e) {
		fname_field.clear();
		lname_field.clear();
		contact_field.clear();
		email_field.clear();
		password_field.clear();
		cpassword_field.clear();
	}
	
	@FXML 
	public void onClickRegister(ActionEvent e) {
		
		String fname = fname_field.getText().trim();
		String lname = lname_field.getText().trim();
		String contact = contact_field.getText().trim();
		String email = email_field.getText().trim();
		String password = password_field.getText().trim();
		String cpassword = cpassword_field.getText().trim();
		
		if(fname.equals("") || lname.equals("") || contact.equals("") || email.equals("") || password.equals("") || cpassword.equals("")) {
			AlertUtils.showAlert("Please Fill all the Details", AlertType.WARNING).show();
		}else {
			//Customer customer = LoginServices.customerLoginService(username, password);
			if(password.equals(cpassword)) {
				Customer customer = RegistrationServices.customerRegService(fname, lname, contact, email, password);
				if(customer == null)
					AlertUtils.showAlert("Please Enter Valid Username/Email & Password", AlertType.ERROR).show();
				else {
					AlertUtils.showAlert("Registration Successfully:)", AlertType.INFORMATION).show();
					Stage stage = (Stage) reg_btn.getScene().getWindow();
					stage.close();
				}
			}
			else {
				AlertUtils.showAlert("Password and Confirm Password does not match", AlertType.ERROR).show();
			}
			
		}
		
	}

	
}
