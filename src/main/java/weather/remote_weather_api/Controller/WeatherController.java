package weather.remote_weather_api.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import weather.remote_weather_api.Service.WeatherService;
import weather.remote_weather_api.Weather.CurrentWeather;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String showMainPage(CurrentWeather currentWeather, Model model){
        Set<String> countries = CurrentWeather.initCountriesSet();
        model.addAttribute("countries", countries);
        return "country";

    }

    @GetMapping("/city")
    public String chooseCity(@RequestParam("country") String country, Model model, CurrentWeather currentWeather){
        currentWeather.setCountry(country);
        Map<String, String> worldMap = CurrentWeather.initWorldMap();
        Set<String> cities = new TreeSet<>();
        for (Map.Entry<String, String> search : worldMap.entrySet()){
            if (search.getValue().equals(country))
                cities.add(search.getKey());
        }
        model.addAttribute("cities", cities);
        return "city";
    }


    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model, CurrentWeather currentWeather) {
            model.addAttribute("currentWeather", weatherService.getCurrentWeather(currentWeather.getCity(), currentWeather.getCountry()));

        return "current-weather";
    }

}
