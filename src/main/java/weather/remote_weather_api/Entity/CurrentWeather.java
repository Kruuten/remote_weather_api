package weather.remote_weather_api.Entity;


import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


public class CurrentWeather implements Serializable {
    private final String PATH = "src/main/resources/Cities/city.list.json";

    private String city;
    private String country;
    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal pressure;
    private BigDecimal windSpeed;
    private Set<String> countries;
    private Map<String, String> worldMap;

    public CurrentWeather() {
    }

    public CurrentWeather(String description,String city, String country, BigDecimal temperature,
                          BigDecimal feelsLike, BigDecimal pressure, BigDecimal windSpeed
    ) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<String> getCountries() {
        return countries;
    }

    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }

    public Map<String, String> getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(Map<String, String> worldMap) {
        this.worldMap = worldMap;
    }

    public static Map<String, String> initWorldMap(String path){
        Map<String, String> worldMap = new TreeMap<>();
        try {
            JSONArray obj = jsonPath(path);
            for (int i = 0; i < obj.length(); i++) {
                String city = obj.getJSONObject(i).getString("name");
                String country = obj.getJSONObject(i).getString("country");
                worldMap.put(city, country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return worldMap;
    }

    public static Set<String> initCountriesSet(String path){
        Set<String> countries = new TreeSet<>();
        try {
            JSONArray obj = jsonPath(path);
            for (int i = 0; i < obj.length(); i++) {
                String country = obj.getJSONObject(i).getString("country");
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public static JSONArray jsonPath(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            builder.append(line);
        }
        return new JSONArray(builder.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentWeather that = (CurrentWeather) o;
        return Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(description, that.description) && Objects.equals(temperature, that.temperature) && Objects.equals(feelsLike, that.feelsLike) && Objects.equals(pressure, that.pressure) && Objects.equals(windSpeed, that.windSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, description, temperature, feelsLike, pressure, windSpeed);
    }
}