package oleg_learn_spring;

import com.example.demo.DemoApplication;
import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = DemoApplication.class)
public class CarServiceTests {
    
    private CarService cs;

    @BeforeEach
    void setUp() {
        cs = new CarService();
    }
    
    @Test
    void newCarServiceIsNotNull() throws Exception {
        assertTrue(cs != null);
    }
    
    @Test void carsIsNotEmptyAtCreation() throws Exception {
        assertTrue(cs.getAll().isEmpty() == false);
    }
    
    @Test void addACar() throws Exception {
        var newCar = new Car();
        
        cs.addCar(newCar);
        
        assertTrue(cs.getAll().contains(newCar));
    }
    
    @Test void getACar() throws Exception {
        var gotACar = cs.getCar(0);
        assertTrue(gotACar != null);
        assertTrue(gotACar.getModel().contains("BMW"));
    }
    
    @Test void updateACar() {
        Car fromCar = Car.builder().model("Subaru").build();
        cs.updateCar(0, fromCar);
        assertTrue(cs.getCar(0).getModel().contains("Subaru"));
    }
    
    @Test void deleteACar() throws Exception {
        cs.deleteCar(0);
        Car car = cs.getCar(0);
        assertTrue(car == null);
    }
}
