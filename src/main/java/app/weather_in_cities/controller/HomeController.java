package app.weather_in_cities.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to the service for finding weather information in Russian cities!";
    }

    @GetMapping("/weather/{city}")
    public String weather() {
        return "Weather";
    }

    @GetMapping("/weather/{city}/forecast")
    public String weatherForecast() {
        return "Weather forecast";
    }
}
