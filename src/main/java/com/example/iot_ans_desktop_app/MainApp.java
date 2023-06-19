package com.example.iot_ans_desktop_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private HomeController homeController;
    private LoginController loginController;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loginLoader = new FXMLLoader(MainApp.class.getResource("login_screen.fxml"));
        Scene loginScene = new Scene(loginLoader.load(), 700, 500);
        stage.setTitle("Logowanie");
        stage.setScene(loginScene);
        stage.show();

        loginController = loginLoader.getController();
        loginController.setMainApp(this);
    }

    public void showHomeScreen() {
        try {
            FXMLLoader homeLoader = new FXMLLoader(MainApp.class.getResource("home_screen.fxml"));
            Scene scene = new Scene(homeLoader.load(), 1350, 900);
            Stage stage = new Stage();
            stage.setTitle("Aplikacja_IoT");
            stage.setScene(scene);
            stage.show();

            // Zamknij scenę logowania
            primaryStage.close();

            homeController = homeLoader.getController();
            // Pobierz dane z ESP32 - Wilgotność
            SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wilgotnosc_1", homeController.getWilgotnoscWzglednaLabel());

            // Pobierz dane z ESP32 - Temperatura
            SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_temperatura_1", homeController.getTemperaturaLabel());

            // Pobierz dane z ESP32 - WiFi
            SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wifi_signal_1", homeController.getSygnalLabel());

            // Pobierz dane z ESP32 - Wilgotność bezwzględna
            SensorDataFetcher.fetchSensorData("https://ha.salonar.pl/api/states/sensor.iot_esp_ans_wilgotnosc_bezwzgledna_1", homeController.getWilgotnoscBezwzglednaLabel());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
