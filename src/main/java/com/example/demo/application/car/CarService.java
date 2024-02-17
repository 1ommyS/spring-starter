package com.example.demo.application.car;

import com.example.demo.application.user.UserService;
import com.example.demo.domain.entity.Car;
import com.example.demo.domain.entity.User;
import com.example.demo.presentation.user.dto.commands.CreateCarCommand;
import com.example.demo.presentation.user.dto.commands.UpdateCarCommand;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private static Set<Car> cars = new HashSet<>();

    static {
        cars.add(Car.builder()
                .id(123)
                .title("VAZ")
                .price(1244421)
                .createdAt(Date.from(Instant.now()))
                .selledAt(Date.from(Instant.now()))
                .amountOfSelled(1236)
                .owner(UserService.users.get(0))
                .build());

        cars.add(Car.builder()
                .id(653)
                .title("BMW")
                .price(1255121212)
                .createdAt(Date.from(Instant.now()))
                .selledAt(Date.from(Instant.now()))
                .amountOfSelled(25533)
                .owner(UserService.users.get(1))
                .build());

        cars.add(Car.builder()
                .id(123)
                .title("KIA")
                .price(5522146)
                .createdAt(Date.from(Instant.now()))
                .selledAt(Date.from(Instant.now()))
                .amountOfSelled(9852)
                .owner(UserService.users.get(2))
                .build());
    }

    public Set<Car> getAll() {
        return cars;
    }

    public Car getById(Integer id) {
        return cars.stream().filter(elem -> elem.getId().equals(id)).findFirst().orElse(null);
    }

    public Car create(CreateCarCommand createCarCommand) {
        User byId = userService.getById(createCarCommand.getUserId());
        if (byId == null) {
            return null;
        }

        Car newCar = modelMapper.map(createCarCommand, Car.class);
        newCar.setId((int) (Math.random() * 1000));
        newCar.setOwner(byId);
        cars.add(newCar);
        return newCar;
    }

    public Car update(Integer id, UpdateCarCommand updateCarCommand) {

        Car foundCar = cars.stream()
                .filter(elem -> elem.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Авто с таким \\\"id\\\" не найдено\""));
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
        if (!foundCar.getOwner().equals(byId) ) {
            foundCar.setOwner(byId);
        }

        return foundCar;
    }

    public void delete(Integer id) {
        Car findedCar = cars.stream().filter(elem -> elem.getId().equals(id)).findFirst().orElse(null);
        if (findedCar == null) {
            return;
        }

        cars.remove(findedCar);
    }

}
