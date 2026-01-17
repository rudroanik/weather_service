package com.example.weather_service.service;

import com.example.weather_service.dto.WeatherResponseDto;
import com.example.weather_service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService {

    @Value("${weather.api.base-url}")
    private String baseUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponseDto getWeatherByCity(String city) {

        if (city.isEmpty()) {
            throw new BadRequestException("City parameter is required");
        }

        String url = String.format("%s?key=%s&q=%s", baseUrl, apiKey, city);

        try {
            Map response = restTemplate.getForObject(url, Map.class);

            Map location = (Map) response.get("location");
            Map current = (Map) response.get("current");

            return new WeatherResponseDto(
                    ((Number) location.get("lat")).doubleValue(),
                    ((Number) location.get("lon")).doubleValue(),
                    ((Number) current.get("temp_c")).doubleValue(),
                    ((Number) current.get("wind_kph")).doubleValue(),
                    ((Number) current.get("humidity")).intValue()
            );

        } catch (Exception e) {
            throw new BadRequestException("Invalid city name or Weather API error");
        }
    }
}
