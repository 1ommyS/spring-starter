package oleg_learn_spring;

import com.example.demo.DemoApplication;
import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.example.demo.presentation.car.CarController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = DemoApplication.class)
public class CarControllerTests {
    
    private CarController cc;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jackson;
    
    @BeforeEach
    void methodSetup() throws Exception {
        var carService = new CarService();
        var mapper = new ModelMapper();
        cc = new CarController(carService, mapper);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cc).build();
    }
    
    @Test void carControllerIsNotNull() throws Exception {
        assertTrue(cc != null);
    }
    
    @Test void getAllCars() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(jsonPath("$", hasSize(1)));
    }
    
    @Test void getOneCar() throws Exception {
        mockMvc.perform(get("/cars/0"))
                .andExpectAll(
                        jsonPath("$.model").value("BMW5"),
                        jsonPath("$.owner.id").value(12),
                        jsonPath("$.owner.name").value("Vasya")
                );
    }
    
    @Test void createACar() throws Exception {
        CarService cs = new CarService();
        Car car = cs.getCar(0);
        String body = jackson.writeValueAsString(car);
        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpectAll(
                        jsonPath("$.model").value("BMW5"),
                        jsonPath("$.owner.id").value(12),
                        jsonPath("$.owner.name").value("Vasya")
                );
    }
    
    @Test void updateACar() throws Exception {
        CarService cs = new CarService();
        Car car = cs.getCar(0);
        car.setModel("Subaru Impereza");
        String body = jackson.writeValueAsString(car);
        mockMvc.perform(put("/cars/0")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        )
        .andExpectAll(
                jsonPath("$.model").value("Subaru Impereza"),
                jsonPath("$.owner.id").value(12),
                        jsonPath("$.owner.name").value("Vasya")
        );
    }
    
    @Test void deleteACar() throws Exception {
        int sizeBefore = cc.getAll().size();
        mockMvc.perform(delete("/cars/0"));
        int sizeAfter = cc.getAll().size();
        assertTrue(sizeAfter == sizeBefore - 1);
    }
}

