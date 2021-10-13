package system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import system.beans.Customer;
import system.services.LoginServices;
import system.services.RegistrationServices;
import system.utils.AlertUtils;

public class CustomerRegisterController {
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
