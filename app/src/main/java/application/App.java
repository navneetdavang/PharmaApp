package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.jdbc.ConnectionManager;

/**
 * JavaFX App
 */
public class App extends Application {
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		loadHomeWindow(stage);
	}
	
	
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
	


}