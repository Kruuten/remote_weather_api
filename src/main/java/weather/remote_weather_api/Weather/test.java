package weather.remote_weather_api.Weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws IOException {
        CurrentWeather currentWeather = new CurrentWeather();
        Set<String> set = currentWeather.initCountriesSet();
        System.out.println(set);
        Map<String, String> worldMap = currentWeather.initWorldMap();
        System.out.println(worldMap);

    }
//    public static Set<String> initCountriesSet(){
//        Set<String> countries = new TreeSet<>();
//        try {
//            JSONArray obj = jsonPath();
//            for (int i = 0; i < obj.length(); i++) {
//                String country = obj.getJSONObject(i).getString("country");
//                countries.add(country);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return countries;
//    }
//    public static JSONArray jsonPath() throws IOException {
//        String jsonPath = "src/main/resources/Cities/city.list.json";
//        BufferedReader reader = new BufferedReader(new FileReader(jsonPath));
//        StringBuilder builder = new StringBuilder();
//        String line;
//        while((line = reader.readLine()) != null){
//            builder.append(line);
//        }
//        JSONArray obj = new JSONArray(builder.toString());
//        return obj;
//    }
//
//    public static Map<String, String> initWorldMap(){
//        Map<String, String> worldMap = new TreeMap<>();
//        try {
//            JSONArray obj = jsonPath();
//
//            Set<String> countries = new TreeSet<>();
//            for (int i = 0; i < obj.length(); i++) {
//                String city = obj.getJSONObject(i).getString("name");
//                String country = obj.getJSONObject(i).getString("country");
//                worldMap.put(city, country);
//                countries.add(country);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return worldMap;
//    }
}

