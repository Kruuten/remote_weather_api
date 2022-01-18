package weather.remote_weather_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.meta.When;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@SpringBootTest
class RemoteWeatherApiApplicationTests {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    RestTemplateBuilder restTemplateBuilder;


    @Test
    public void jsonPathRest() throws Exception{
        JSONArray expectedObj = new JSONArray("[\n" +
                "  {\n" +
                "    \"id\": 833,\n" +
                "    \"name\": \"Ḩeşār-e Sefīd\",\n" +
                "    \"state\": \"\",\n" +
                "    \"country\": \"IR\",\n" +
                "    \"coord\": {\n" +
                "      \"lon\": 47.159401,\n" +
                "      \"lat\": 34.330502\n" +
                "    }\n" +
                "  }\n" +
                "]");


        String json ="src/main/resources/Cities/test.json";

        BufferedReader reader = new BufferedReader(new FileReader(json));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        JSONArray result = new JSONArray(builder.toString());
        Assertions.assertThat(result.toString()).isEqualTo(expectedObj.toString());
    }

    @Test
    public void initCountrySetTest() throws Exception{
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("IR");

        Set<String> resultSet = new HashSet<>();

        JSONArray obj = new JSONArray("[\n" +
                "  {\n" +
                "    \"id\": 833,\n" +
                "    \"name\": \"Ḩeşār-e Sefīd\",\n" +
                "    \"state\": \"\",\n" +
                "    \"country\": \"IR\",\n" +
                "    \"coord\": {\n" +
                "      \"lon\": 47.159401,\n" +
                "      \"lat\": 34.330502\n" +
                "    }\n" +
                "  }\n" +
                "]");
        for (int i = 0; i < obj.length(); i++) {
            String country = obj.getJSONObject(i).getString("country");
            resultSet.add(country);
        }

        Assertions.assertThat(resultSet).isEqualTo(expectedSet);
    }

    @Test
    public void initWorldMapTest() throws Exception{
        Map<String, String> expectedMap = new TreeMap<>();
        expectedMap.put("Ḩeşār-e Sefīd", "IR");

        Map<String, String> resultMap = new TreeMap<>();
        JSONArray obj = new JSONArray("[\n" +
                "  {\n" +
                "    \"id\": 833,\n" +
                "    \"name\": \"Ḩeşār-e Sefīd\",\n" +
                "    \"state\": \"\",\n" +
                "    \"country\": \"IR\",\n" +
                "    \"coord\": {\n" +
                "      \"lon\": 47.159401,\n" +
                "      \"lat\": 34.330502\n" +
                "    }\n" +
                "  }\n" +
                "]");

        for (int i = 0; i < obj.length(); i++) {
            String city = obj.getJSONObject(i).getString("name");
            String country = obj.getJSONObject(i).getString("country");
            resultMap.put(city, country);
        }

        Assertions.assertThat(expectedMap).isEqualTo(resultMap);
    }

    public void convertTest() throws Exception{

    }
}

