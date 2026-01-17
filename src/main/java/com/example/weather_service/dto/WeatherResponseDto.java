package com.example.weather_service.dto;

public record  WeatherResponseDto(
        double latitude,
        double longitude,
        double temperatureCelsius,
        double windKmh,
        int humidity
) {}
