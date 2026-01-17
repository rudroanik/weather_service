package com.example.weather_service.service;

import com.example.weather_service.dto.WeatherResponseDto;
import com.example.weather_service.exception.BadRequestException;
import com.example.weather_service.model.WeatherApiResponse;
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
            WeatherApiResponse apiResponse = restTemplate.getForObject(url, WeatherApiResponse.class);

            if (apiResponse == null) {
                throw new BadRequestException("Weather data not available");
            }

            return new WeatherResponseDto(
                    apiResponse.getLocation().getLat(),
                    apiResponse.getLocation().getLon(),
                    apiResponse.getCurrent().getTempC(),
                    apiResponse.getCurrent().getWindKph(),
                    apiResponse.getCurrent().getHumidity()
            );

        } catch (Exception e) {
            throw new BadRequestException("Invalid city name or Weather API error");
        }
    }
}
