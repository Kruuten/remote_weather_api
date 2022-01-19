package weather.remote_weather_api.entityTest;

import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import weather.remote_weather_api.Entity.CurrentWeather;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EntityTest {

    private final String JSON = "[\n" +
            "  {\n" +
            "    \"id\": 833,\n" +
            "    \"name\": \"Ḩeşār-e Sefīd\",\n" +
            "    \"state\": \"\",\n" +
            "    \"country\": \"IR\",\n" +
            "    \"coord\": {\n" +
            "      \"lon\": 47.159401,\n" +
            "      \"lat\": 34.330502\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2960,\n" +
            "    \"name\": \"‘Ayn Ḩalāqīm\",\n" +
            "    \"state\": \"\",\n" +
            "    \"country\": \"SY\",\n" +
            "    \"coord\": {\n" +
            "      \"lon\": 36.321911,\n" +
            "      \"lat\": 34.940079\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3245,\n" +
            "    \"name\": \"Taglag\",\n" +
            "    \"state\": \"\",\n" +
            "    \"country\": \"IR\",\n" +
            "    \"coord\": {\n" +
            "      \"lon\": 44.98333,\n" +
            "      \"lat\": 38.450001\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3530,\n" +
            "    \"name\": \"Qabāghlū\",\n" +
            "    \"state\": \"\",\n" +
            "    \"country\": \"IR\",\n" +
            "    \"coord\": {\n" +
            "      \"lon\": 46.168499,\n" +
            "      \"lat\": 36.173302\n" +
            "    }\n" +
            "  }\n" +
            "  ]";

    @Test
    public void jsonPathTest() throws Exception{
        String testPath = "src/main/resources/Cities/test.json";
        JSONArray expectedArray = new JSONArray(JSON);
        JSONArray resultArray = CurrentWeather.jsonPath(testPath);

        Assertions.assertThat(resultArray.toString()).isEqualTo(expectedArray.toString());
    }

    @Test
    public void initCountrySetTest() throws Exception{
        String testPath = "src/main/resources/Cities/test.json";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("IR");
        expectedSet.add("SY");

        Set<String> resultSet = CurrentWeather.initCountriesSet(testPath);

        Assertions.assertThat(resultSet).isEqualTo(expectedSet);
    }

    @Test
    public void initWorldMapTest() throws Exception{
        String testPath = "src/main/resources/Cities/test.json";
        Map<String, String> expectedMap = new TreeMap<>();
        expectedMap.put("Ḩeşār-e Sefīd", "IR");
        expectedMap.put("‘Ayn Ḩalāqīm", "SY");
        expectedMap.put("Taglag", "IR");
        expectedMap.put("Qabāghlū", "IR");

        Map<String, String> resultMap = CurrentWeather.initWorldMap(testPath);

        Assertions.assertThat(expectedMap).isEqualTo(resultMap);
    }
}
