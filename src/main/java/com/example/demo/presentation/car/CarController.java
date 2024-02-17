package com.example.demo.presentation.car;

import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.example.demo.presentation.car.dto.commands.CreateCarCommand;
import com.example.demo.presentation.car.dto.commands.UpdateCarCommand;
import com.example.demo.presentation.car.dto.queries.CarQuery;
import com.example.demo.presentation.user.dto.commands.UpdateUserCommand;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    
    private CarService cs;
    private ModelMapper mm;

    @GetMapping
    public List <CarQuery> getAll() {
        return cs.getAll()
                .stream()
                .map(c -> mm.map(c,CarQuery.class))
                .toList();
    }
    
    @GetMapping("/{id}")
    public CarQuery getOneCar(@PathVariable Integer id) {
        Car gotACar = cs.getCar(id);
        CarQuery mapped = mm.map(gotACar, CarQuery.class);
        return mapped;
    }
    
    @PostMapping
    public CarQuery createCar(@RequestBody CreateCarCommand command) {
        var car = mm.map(command, Car.class);
        cs.addCar(car);
        var createdCar = cs.getCar(car.getId());
        return mm.map(createdCar, CarQuery.class);
    }
    
    @PutMapping("/{id}")
    public CarQuery updateCar(@PathVariable Integer id, @RequestBody UpdateCarCommand command) {
        Car carFrom = mm.map(command, Car.class);
        Car returned = cs.updateCar(id, carFrom);
        return mm.map(returned, CarQuery.class);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Integer id) {
        cs.deleteCar(id);
    }
}
