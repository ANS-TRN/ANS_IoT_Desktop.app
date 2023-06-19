package com.example.iot_ans_desktop_app;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SensorDataFetcher {
    private static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiZjViYWE2NWFmNWI0MTU2OWY3ZGY2ZWI4NTA4Y2Y0YyIsImlhdCI6MTY4NzEwNDg0MiwiZXhwIjoyMDAyNDY0ODQyfQ.K8hXuDLbONxjn9chtViIcDO1IKmd-P-ZHvP2YQSs8RY";

    public static void fetchSensorData(String sensorUrl, Label label) {
        try {
            URL url = new URL(sensorUrl);
            HttpURLConnection connection = Connection_ESP.openConnection(url, AUTH_TOKEN);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Przetwarzanie odpowiedzi
                String responseData = response.toString();
                DataProcessor.processSensorData(responseData, label);
            } else {
                // Obsługa błędu
                System.out.println("Wystąpił błąd: " + responseCode);
            }
        } catch (IOException e) {
            // Obsługa wyjątku
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }
}
