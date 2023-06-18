package com.example.iot_ans_desktop_app;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection_ESP {
    public static HttpURLConnection openConnection(URL url, String authToken) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + authToken);
        return connection;
    }

    public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static String executeRequest(URL url, String authToken) {
        try {
            HttpURLConnection connection = openConnection(url, authToken);
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                return response.toString();
            } else {
                showAlert("Error: " + responseCode);
            }

        } catch (IOException e) {
            showAlert("Error: " + e.getMessage());
        }

        return null;
    }
}
