package com.example.weather_service.model;

import lombok.Data;

@Data
public class WeatherApiResponse {
    private Location location;
    private Current current;
}
