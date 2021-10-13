package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.jdbc.ConnectionManager;
import system.utils.ViewLoaderUtils;

/**
 * JavaFX App
 */
public class App extends Application {
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		ViewLoaderUtils.loadHomeWindow(stage);
	}
	
}