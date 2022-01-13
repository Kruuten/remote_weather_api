package weather.remote_weather_api.Controller;


import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import weather.remote_weather_api.Service.LiveWeatherService;
import weather.remote_weather_api.Weather.CurrentWeather;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


@Controller
public class CurrentWeatherController {

    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

//    @GetMapping("/")
//    public String showMainPage(CurrentWeather currentWeather, Model model){
//            Set<String> countries = currentWeather.initCountriesSet();
//            Map<String, String> worldMap = currentWeather.initWorldMap();
//
//            model.addAttribute("countries", countries);
//            model.addAttribute("cities", countries);
//
//        return "mainpage";
//
//    }

    @GetMapping("/")
    public String showMainPage(CurrentWeather currentWeather, Model model){
        String jsonPath = "src/main/resources/Cities/city.list.json";
        Map<String, String> worldMap = new TreeMap<>();
        Set<String> countries = new TreeSet<>();
        Set<String> cities = new TreeSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonPath));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            JSONArray obj = new JSONArray(builder.toString());
            for (int i = 0; i < obj.length(); i++) {
                String city = obj.getJSONObject(i).getString("name");
                String country = obj.getJSONObject(i).getString("country");
                worldMap.put(city, country);
                countries.add(country);
                cities.add(city);
            }

            model.addAttribute("countries", countries);
            model.addAttribute("cities", cities);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "mainpage";

    }


    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Moscow","ru"));
        return "current-weather";
    }

}
