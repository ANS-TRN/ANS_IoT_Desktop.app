package com.example.iot_ans_desktop_app;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class HomeController implements Initializable {
    @FXML
    private Label currentDateLabel;

    @FXML
    private Label wilgotnoscWzglednaLabel;

    @FXML
    private Label temperaturaLabel;

    @FXML
    private Label sygnalLabel;

    @FXML
    private Label wilgotnoscBezwzglednaLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ustawienie formatu daty
        DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");

        // Pobranie aktualnej daty
        Date currentDate = Calendar.getInstance().getTime();
        String formattedDate = dateFormat.format(currentDate);

        // Ustawienie wartości etykiety "Aktualna_data" na sformatowaną datę
        currentDateLabel.setText(formattedDate);
    }

    public Label getWilgotnoscWzglednaLabel() {
        return wilgotnoscWzglednaLabel;
    }

    public Label getTemperaturaLabel() {
        return temperaturaLabel;
    }

    public Label getSygnalLabel() {
        return sygnalLabel;
    }

    public Label getWilgotnoscBezwzglednaLabel() {
        return wilgotnoscBezwzglednaLabel;
    }
    @FXML
    private void openDeviceWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("device_screen.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Zarządzenie urządzeniem");
            stage.setScene(new Scene(loader.load(), 400, 300));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
