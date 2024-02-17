package com.example.demo.presentation.user;

import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.example.demo.presentation.user.dto.commands.CreateCarCommand;
import com.example.demo.presentation.user.dto.commands.UpdateCarCommand;
import com.example.demo.presentation.user.dto.queries.CarQuery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CarQuery> getAll() {
        return carService.getAll().parallelStream().map(elem -> modelMapper.map(elem, CarQuery.class)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CarQuery getById(@PathVariable Integer id) {
        if (id == null) {
            return new CarQuery();
        }

        Car byId = carService.getById(id);

        if (byId == null) {
            return new CarQuery();
        }

        return modelMapper.map(byId, CarQuery.class);
    }

    @PostMapping
    public CarQuery createCar(@RequestBody CreateCarCommand createCarCommand) {
        Car car = carService.create(createCarCommand);
        if (car == null) {
            return new CarQuery();
        }

        return modelMapper.map(car, CarQuery.class);
    }

    @PutMapping("{id}")
    public CarQuery changeCar(@PathVariable Integer id, @RequestBody UpdateCarCommand updateCarCommand) {
        Car updatedCar = carService.update(id, updateCarCommand);
        if (updatedCar == null) {
            return new CarQuery();
        }
        return modelMapper.map(updatedCar, CarQuery.class);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.delete(id);
    }

}
