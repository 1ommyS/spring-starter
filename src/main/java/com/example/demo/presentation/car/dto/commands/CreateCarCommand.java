package com.example.demo.presentation.car.dto.commands;

import com.example.demo.domain.entity.User;
import lombok.Data;

@Data
public class CreateCarCommand {
    public Integer id;
    public String model;
    public Integer quantitySold;
    User owner;
}




