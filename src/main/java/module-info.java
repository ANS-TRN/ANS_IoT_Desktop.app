module com.example.iot_ans_desktop_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.iot_ans_desktop_app to javafx.fxml;
    exports com.example.iot_ans_desktop_app;
}