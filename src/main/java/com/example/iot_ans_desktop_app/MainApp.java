package com.example.iot_ans_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private HomeController homeController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("home_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1360, 1000);
        stage.setTitle("Aplikacja_IoT");
        stage.setScene(scene);
        stage.show();

        homeController = fxmlLoader.getController();

        // Pobierz dane z ESP32 - Wilgotność
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wilgotnosc_1", homeController.getWilgotnoscWzglednaLabel());

        // Pobierz dane z ESP32 - Temperatura
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_temperatura_1", homeController.getTemperaturaLabel());

        // Pobierz dane z ESP32 - WiFi
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wifi_signal_1", homeController.getSygnalLabel());

        // Pobierz dane z ESP32 - Wilgotność bezwzględna
        SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wilgotnosc_bezwzgledna_1", homeController.getWilgotnoscBezwzglednaLabel());
    }

    public static void main(String[] args) {
        launch();
    }
}
