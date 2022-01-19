package weather.remote_weather_api.Entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ShowWeather {

    public static void start() {
        final String API_KEY = "2440d8dd3f7174db3c12ba0537447416";
        final String LAT = "60.99";
        final String LON = "30.9";
        final String URL_STRING = "https://api.openweathermap.org/data/2.5/onecall?" +
                "lat=" + LAT +
                "&lon=" + LON +
                "&exclude=current,minutely,hourly,alerts" +
                "&units=metric" +
                "&appid=" + API_KEY;

        try {
            URL url = new URL(URL_STRING);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line);
            }
            reader.close();

            Map<String, Object> resultMap = jsonConverter(builder.toString());
            List<Map<String, Object>> dailyList = (List<Map<String, Object>>) resultMap.get("daily");

            List<Double> pressureList = new ArrayList<>();
            List<Double> temperatureList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                double pressure = (double) dailyList.get(i).get("pressure");
                pressureList.add(pressure);

                Map<String, Object> temperatureMap = jsonConverter(dailyList.get(i).get("temp").toString());
                double maxTemperature = (double) temperatureMap.get("night") - (double) temperatureMap.get("morn");
                temperatureList.add(maxTemperature);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            double maxPressure = pressureList
                    .stream()
                    .max(Comparator.comparing(Double::valueOf)).get();
            double maxPressDate = (double) dailyList
                    .get(pressureList.indexOf(maxPressure)).get("dt");
            LocalDate maxDateTime = LocalDate.ofInstant(Instant.ofEpochSecond((long) maxPressDate), TimeZone.getDefault().toZoneId());


            double minTemperature = temperatureList
                    .stream()
                    .min(Comparator.comparing(Double::valueOf))
                    .get();
            double minTempDate = (double) dailyList
                    .get(temperatureList.indexOf(minTemperature))
                    .get("dt");
            LocalDate minDateTime = LocalDate.ofInstant(Instant.ofEpochSecond((long)minTempDate), ZoneId.systemDefault());

            System.out.println(resultMap.get("timezone"));
            System.out.printf("Max pressure for 5 days is %.0f mHg at %s%n", maxPressure, maxDateTime.format(formatter));
            System.out.printf("Min temperature for 5 days is %.0f C at %s%n", minTemperature, minDateTime.format(formatter));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Map<String, Object> jsonConverter(String jsonData){
        return new Gson().fromJson(jsonData, new TypeToken<Map<String, Object>>(){}
                         .getType());
    }
}
