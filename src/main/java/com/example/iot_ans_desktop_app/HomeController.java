package com.example.iot_ans_desktop_app;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label currentDateLabel;

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
}
