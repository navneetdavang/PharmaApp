module com.pms.app {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires java.sql;

    opens application to javafx.fxml;
    opens system.controllers to javafx.fxml;
    opens system.beans to javafx.base;
    
    exports application;
}
