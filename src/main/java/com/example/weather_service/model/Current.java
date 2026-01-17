package com.example.weather_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Current {
    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("wind_kph")
    private double windKph;

    private int humidity;
}
