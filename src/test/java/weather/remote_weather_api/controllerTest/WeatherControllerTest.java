package weather.remote_weather_api.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import weather.remote_weather_api.Controller.WeatherController;
import weather.remote_weather_api.Service.WeatherService;
import weather.remote_weather_api.entityTest.EntityTest;
import weather.remote_weather_api.serviceTest.ServiceTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WeatherControllerTest.class, EntityTest.class, ServiceTest.class})
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController wc;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(wc).build();
    }

    @Test
    public void showMainPageTest() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }


    @Test
    public void chooseCityTest() throws Exception {
        mockMvc.perform(get("/city?country=RU")).andExpect(status().isOk());
    }

    @Test
    public void getCurrentWeatherTest() throws Exception{
        mockMvc.perform(get("/current-weather?city=Moscow")).andExpect(status().isOk());
    }
}
