package com.example.iot_ans_desktop_app;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;


public interface Esp32Service {
    @GET("/api/states/switch.iot_esp_ans_relay_1")
    Call<JsonArray> getSwitchStatus(@Header("Authorization") String authToken);

    @POST("/api/services/switch/turn_on")
    Call<JsonArray> turnOnSwitch(@Header("Authorization") String authToken, @Body JsonObject body);

    @POST("/api/services/switch/turn_off")
    Call<JsonArray> turnOffSwitch(@Header("Authorization") String authToken, @Body JsonObject body);

}

