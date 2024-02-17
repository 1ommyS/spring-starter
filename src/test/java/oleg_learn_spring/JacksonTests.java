package oleg_learn_spring;

import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JacksonTests {
    
    @Test void boo() throws Exception {
        var cs = new CarService();
        Car car = cs.getCar(0);
        var jackson = new ObjectMapper();
        String carToString = jackson.writeValueAsString(car);
        System.out.println("jackson.readValue();");
    }
    
}
