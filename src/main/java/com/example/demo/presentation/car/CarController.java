package com.example.demo.presentation.car;

import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.example.demo.presentation.car.dto.commands.CreateCarCommand;
import com.example.demo.presentation.car.dto.commands.UpdateCarCommand;
import com.example.demo.presentation.car.dto.queries.CarQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Validated
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    /*1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
    [1->2], [3->4], [5->6], [7->8], [9->10]*/

    @GetMapping
    public List<CarQuery> getAll() {
        return carService.getAll()
                .stream()
                .map(elem -> modelMapper.map(elem, CarQuery.class)).toList();
    }

    @GetMapping("{id}")
    public CarQuery getById(@PathVariable Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("В запросе передан пустой параметр \"id\"");
        }

        Car byId = carService.getById(id);

        if (byId == null) {
            throw new IllegalArgumentException("Авто с таким \"id\" не существует в базе");
        }

        return modelMapper.map(byId, CarQuery.class);
    }

    @GetMapping("/user/{ownerId}")
    public List<CarQuery> getByOwnerId(@PathVariable Integer ownerId) {
        return carService.getByOwnerId(ownerId).stream()
                .map(car -> modelMapper.map(car, CarQuery.class))
                .toList();
    }

    @GetMapping("/user-name/{ownerName}")
    public List<CarQuery> getByOwnerName(@PathVariable String ownerName) {
        return carService.getByOwnerName(ownerName).stream()
                .map(car -> modelMapper.map(car, CarQuery.class))
                .toList();
    }

    @PostMapping
    public CarQuery createCar(@RequestBody @Valid CreateCarCommand createCarCommand) {
        Car car = carService.create(createCarCommand);
        if (car == null) {
            throw new RuntimeException("Произошла ошибка при создании авто");
        }

        return modelMapper.map(car, CarQuery.class);
    }

    @PutMapping("{id}")
    public CarQuery changeCar(@PathVariable Integer id, @RequestBody UpdateCarCommand updateCarCommand) {
        Car updatedCar = carService.update(id, updateCarCommand);
        return modelMapper.map(updatedCar, CarQuery.class);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable Integer id) {
        carService.delete(id);
    }

}
