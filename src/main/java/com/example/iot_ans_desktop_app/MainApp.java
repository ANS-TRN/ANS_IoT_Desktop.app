package com.example.iot_ans_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1360, 1100);
        stage.setTitle("Aplikacja_IoT");
        stage.setScene(scene);
        stage.show();

        // Pobierz dane z ESP32 - Wilgotność
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wilgotnosc_1");

        // Pobierz dane z ESP32 - Temperatura
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_temperatura_1");
    }

    public static void main(String[] args) {
        launch();
    }
}
