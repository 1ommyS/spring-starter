package com.example.demo.application.car;

import com.example.demo.domain.entity.Car;
import com.example.demo.domain.entity.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CarService {
    
    
    private ArrayList <Car> cars;
    
    public CarService() {
        cars = new ArrayList <>();
        User user = User.builder().id(12).name("Vasya").build();
        cars.add(Car.builder()
                .id(0)
                .model("BMW5")
                .quantitySold(5)
                .owner(user)
                .build());
    }
    
    public List <Car> getAll() {
        return cars;
    }
    
    public void addCar(Car newCar) {
        cars.add(newCar);
    }
    
    public Car getCar(Integer id) {
        var car = cars.stream()
                .filter(c -> c.getId().intValue() == id.intValue())
                .findFirst();
        return car.isEmpty() ? null : car.get();
    }
    
    public Car updateCar(Integer id, Car newCar) {
        Car gotCar = getCar(0);
        gotCar.setModel(newCar.getModel());
        return gotCar;
    }
    
    public void deleteCar(Integer id) {
        cars.removeIf(car -> car.getId().intValue() == id.intValue());
    }
}
