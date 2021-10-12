package system.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {
	
	
	// method to show alert 
	public static Alert showAlert(String msg, AlertType type) {
		Alert alert = new Alert(type);
		alert.setHeaderText(msg);
		alert.setContentText(msg);
		return alert;
	}
	
}
