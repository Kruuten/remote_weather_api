package weather.remote_weather_api;

import Weather.ShowWeather;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RemoteWeatherApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteWeatherApiApplication.class, args);
        ShowWeather.start();

    }

}
