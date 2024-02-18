package com.example.demo.infrastructure.repository;

import com.example.demo.domain.entity.Car;
import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByOwner(User owner);

    List<Car> findByOwner_NameAllIgnoreCase(String name);
}
