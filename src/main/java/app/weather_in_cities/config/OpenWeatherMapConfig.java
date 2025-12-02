package app.weather_in_cities.config;

import app.weather_in_cities.api.DefaultApi;
import app.weather_in_cities.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherMapConfig {

    @Value("${openweathermap.api:key}")
    private String apiKey;

    @Bean
    DefaultApi openWeatherMapApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setApiKey(apiKey);

        DefaultApi defaultApi = new DefaultApi(apiClient);
        return defaultApi;
    }
}
