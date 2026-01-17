package com.example.weather_service.controller;

import com.example.weather_service.dto.WeatherResponseDto;
import com.example.weather_service.exception.BadRequestException;
import com.example.weather_service.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anikroy
 */
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherResponseDto getWeather(@RequestParam  String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new BadRequestException("City name must not be empty");
        }
        return weatherService.getWeatherByCity(city.trim());
    }
}
