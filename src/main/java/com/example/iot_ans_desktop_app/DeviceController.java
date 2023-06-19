package com.example.iot_ans_desktop_app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceController {
    private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiZjViYWE2NWFmNWI0MTU2OWY3ZGY2ZWI4NTA4Y2Y0YyIsImlhdCI6MTY4NzEwNDg0MiwiZXhwIjoyMDAyNDY0ODQyfQ.K8hXuDLbONxjn9chtViIcDO1IKmd-P-ZHvP2YQSs8RY";
    private static final String DEVICE_URL = "https://ha.salonar.pl";

    @FXML
    private Button toggleButton;

    @FXML
    private Label deviceStatusLabel;

    private boolean isDeviceOn;
    private Esp32Service esp32Service;

    @FXML
    private void initialize() {
        isDeviceOn = false;

        // Inicjalizacja Retrofita
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DEVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Utworzenie instancji interfejsu Esp32Service
        esp32Service = retrofit.create(Esp32Service.class);

        updateDeviceStatus();
    }

    @FXML
    private void handleToggleButtonAction() {
        sendDeviceCommand("toggle");
    }

//...

    private void sendDeviceCommand(String command) {
        try {
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("entity_id", "switch.iot_esp_ans_relay_1");

            Call<JsonArray> switchStatusCall;
            if (command.equals("toggle")) {
                switchStatusCall = esp32Service.turnOnSwitch(AUTH_TOKEN, requestBody);
            } else {
                switchStatusCall = esp32Service.turnOffSwitch(AUTH_TOKEN, requestBody);
            }

            // Wywołanie żądania asynchronicznie
            switchStatusCall.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    int statusCode = response.code();
                    JsonArray responseBody = response.body();
                    String responseMessage = response.message();

                    // Wyświetl szczegóły odpowiedzi serwera
                    System.out.println("Status kod: " + statusCode);
                    System.out.println("Ciało odpowiedzi: " + responseBody);
                    System.out.println("Wiadomość odpowiedzi: " + responseMessage);

                    if (response.isSuccessful()) {
                        // Obsługa sukcesu
                        isDeviceOn = !isDeviceOn;
                        updateDeviceStatus();
                    } else {
                        showErrorMessage("Wystąpił błąd podczas wysyłania komendy: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    showErrorMessage("Wystąpił błąd podczas wysyłania komendy: " + t.getMessage());
                }
            });

        } catch (Exception e) {
            showErrorMessage("Wystąpił błąd podczas wysyłania komendy: " + e.getMessage());
        }
    }

//...


    private void updateDeviceStatus() {
        Platform.runLater(() -> {
            if (isDeviceOn) {
                toggleButton.setText("Wyłącz");
                deviceStatusLabel.setText("Urządzenie jest włączone");
            } else {
                toggleButton.setText("Włącz");
                deviceStatusLabel.setText("Urządzenie jest wyłączone");
            }
        });
    }

    private void showErrorMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
