package com.example.iot_ans_desktop_app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.scene.control.Label;

public class DataProcessor {
    public static void processSensorData(String responseData, Label label) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);

        String state = jsonObject.get("state").getAsString();
        JsonObject attributes = jsonObject.getAsJsonObject("attributes");
        String unitOfMeasurement = attributes.get("unit_of_measurement").getAsString();

        // Wykorzystanie wyciągniętych atrybutów w kodzie JavaFX
        System.out.println("State: " + state);
        System.out.println("Unit of Measurement: " + unitOfMeasurement);

        // Aktualizacja wartości labela
        String labelText = state + " " + unitOfMeasurement;
        label.setText(labelText);
    }
}
