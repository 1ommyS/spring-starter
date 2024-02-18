package com.example.demo.application.car;

import com.example.demo.application.user.UserService;
import com.example.demo.domain.entity.Car;
import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.CarRepository;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.presentation.car.dto.commands.CreateCarCommand;
import com.example.demo.presentation.car.dto.commands.UpdateCarCommand;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    private final Set<Car> cars = new HashSet<>();

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Integer id) {
        return carRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Car create(CreateCarCommand createCarCommand) {
        User byId = userService.getById(createCarCommand.getUserId());
        if (byId == null) {
            return null;
        }

        Car newCar = modelMapper.map(createCarCommand, Car.class);
        newCar.setId((int) (Math.random() * 1000));
        newCar.setOwner(byId);
        carRepository.save(newCar);
        return newCar;
    }

    public Car update(Integer id, UpdateCarCommand updateCarCommand) {

        Car foundCar = carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Авто с таким \"id\" не найдено\""));
        User byId = userService.getById(updateCarCommand.getUserId());

        if (updateCarCommand.getTitle() != null && !updateCarCommand.getTitle().equals(foundCar.getTitle())) {
            foundCar.setTitle(updateCarCommand.getTitle());
        }
        if (updateCarCommand.getPrice() != null && !updateCarCommand.getPrice().equals(foundCar.getPrice())) {
            foundCar.setPrice(updateCarCommand.getPrice());
        }
        if (updateCarCommand.getCreatedAt() != null && !updateCarCommand.getCreatedAt().equals(foundCar.getCreatedAt())) {
            foundCar.setCreatedAt(updateCarCommand.getCreatedAt());
        }
        if (updateCarCommand.getSelledAt() != null && !updateCarCommand.getSelledAt().equals(foundCar.getSelledAt())) {
            foundCar.setSelledAt(updateCarCommand.getSelledAt());
        }
        if (updateCarCommand.getAmountOfSelled() != null && !updateCarCommand.getAmountOfSelled().equals(foundCar.getAmountOfSelled())) {
            foundCar.setAmountOfSelled(updateCarCommand.getAmountOfSelled());
        }
        if (!foundCar.getOwner().equals(byId)) {
            foundCar.setOwner(byId);
        }
        Car savedCar = carRepository.save(foundCar);
        return savedCar;
    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

}
