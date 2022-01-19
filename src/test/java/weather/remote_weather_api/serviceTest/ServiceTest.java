package weather.remote_weather_api.serviceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import weather.remote_weather_api.Entity.CurrentWeather;
import weather.remote_weather_api.Service.WeatherService;
import java.math.BigDecimal;
import java.net.URI;


public class ServiceTest {

    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;

    private ObjectMapper objectMapper;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);
        weatherService = new WeatherService(restTemplateBuilder, objectMapper);
    }

    private final String JSON ="{\n" +
            "  \"coord\": {\n" +
            "    \"lon\": 37.6156,\n" +
            "    \"lat\": 55.7522\n" +
            "  },\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 601,\n" +
            "      \"main\": \"Snow\",\n" +
            "      \"description\": \"snow\",\n" +
            "      \"icon\": \"13d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"base\": \"stations\",\n" +
            "  \"main\": {\n" +
            "    \"temp\": -4.58,\n" +
            "    \"feels_like\": -11.58,\n" +
            "    \"temp_min\": -4.85,\n" +
            "    \"temp_max\": -2.9,\n" +
            "    \"pressure\": 1002,\n" +
            "    \"humidity\": 89,\n" +
            "    \"sea_level\": 1002,\n" +
            "    \"grnd_level\": 983\n" +
            "  },\n" +
            "  \"visibility\": 10000,\n" +
            "  \"wind\": {\n" +
            "    \"speed\": 6.78,\n" +
            "    \"deg\": 353,\n" +
            "    \"gust\": 14.03\n" +
            "  },\n" +
            "  \"snow\": {\n" +
            "    \"1h\": 1\n" +
            "  },\n" +
            "  \"clouds\": {\n" +
            "    \"all\": 100\n" +
            "  },\n" +
            "  \"dt\": 1642496012,\n" +
            "  \"sys\": {\n" +
            "    \"type\": 2,\n" +
            "    \"id\": 47754,\n" +
            "    \"country\": \"RU\",\n" +
            "    \"sunrise\": 1642484760,\n" +
            "    \"sunset\": 1642512799\n" +
            "  },\n" +
            "  \"timezone\": 10800,\n" +
            "  \"id\": 524901,\n" +
            "  \"name\": \"Moscow\",\n" +
            "  \"cod\": 200\n" +
            "}";

    @Test
    public void getCurrentWeatherTest() throws Exception{
        String city = "Moscow";
        String country = "RU";
        String path ="http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID=&units=metric";

        URI url = new UriTemplate(path).expand(city, country, apiKey);
        ResponseEntity<String> expected = new ResponseEntity<String>( JSON, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, String.class)).thenReturn(expected);
        CurrentWeather actualResult = weatherService.getCurrentWeather(city, country);

        Assertions.assertThat(actualResult.getCity()).isEqualTo("Moscow");
        Assertions.assertThat(actualResult.getCountry()).isEqualTo("RU");
        Assertions.assertThat(actualResult.getFeelsLike()).isEqualTo(BigDecimal.valueOf(-11.58));

    }
}
