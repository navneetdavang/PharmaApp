package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(App.class.getResource("/system/fxmls/Pharmacy Management System.fxml"));
		
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Medicore");
		stage.show();
	}
	

}