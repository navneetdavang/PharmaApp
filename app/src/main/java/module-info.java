module com.pms.app {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens application to javafx.fxml;
    opens system.controllers to javafx.fxml;
    
    exports application;
}
