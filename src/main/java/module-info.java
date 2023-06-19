module com.example.iot_ans_desktop_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires retrofit2;
    requires retrofit2.converter.gson;

    opens com.example.iot_ans_desktop_app to javafx.fxml;
    exports com.example.iot_ans_desktop_app;
}