package com.example.iot_ans_desktop_app;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DataProcessor {
    public static void processSensorData(String responseData) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);

        String state = jsonObject.get("state").getAsString();
        JsonObject attributes = jsonObject.getAsJsonObject("attributes");
        String unitOfMeasurement = attributes.get("unit_of_measurement").getAsString();
        String friendlyName = attributes.get("friendly_name").getAsString();

        // Wykorzystanie wyciągniętych atrybutów w kodzie JavaFX
        System.out.println("State: " + state);
        System.out.println("Unit of Measurement: " + unitOfMeasurement);
        System.out.println("Friendly Name: " + friendlyName);

        // Możesz przypisać wartości do odpowiednich pól w swojej aplikacji JavaFX lub wykorzystać je w inny sposób.
    }
}
