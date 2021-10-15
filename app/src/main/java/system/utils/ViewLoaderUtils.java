package system.utils;

import application.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.beans.Customer;
import system.controllers.CustomerDashboardController;

public class ViewLoaderUtils {

	
	// to load the homepage
	public static void loadHomeWindow(Stage stage) {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Pharmacy Management System.fxml"));
			
			Scene scene = new Scene(root);
			stage.setResizable(false);	// preventing full screen
			stage.setScene(scene);
			stage.setTitle("Medicore");	// setting the title
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading the HomePage");
		}
		
	}
	
	
	// method to load the chemist dashboard
	public static void loadChemistDashboard(Stage stage) {
			
		try {
			
			Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Chemist Dashboard.fxml"));
			
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("Medicore");
			stage.setResizable(false);
			stage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading the Chemist Dashboard");
		}
		
	}

	
	// method to load the chemist dashboard
	public static void loadCustomerDashboard(Stage stage, Customer customer) {
			
		try {
			
			FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/fxmls/Customer Dashboard.fxml"));
			Parent root = (Parent) loader.load();
			
			CustomerDashboardController controller = loader.getController();
			controller.setCustomer(customer);
			
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Medicore");
			stage.setResizable(false);
			stage.setX(200);
			stage.setY(100);
			stage.show();

			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while loading the Customer Dashboard");
		}
		
	}
	
	
}
