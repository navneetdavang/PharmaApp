module com.pms.app {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pms.app to javafx.fxml;
    exports com.pms.app;
}
