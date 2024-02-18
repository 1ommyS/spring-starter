package com.example.demo.infrastructure.repository;

import com.example.demo.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
