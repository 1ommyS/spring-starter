package com.example.demo.presentation.car.dto.commands;

import com.example.demo.domain.entity.User;
import lombok.Data;

@Data
public class UpdateCarCommand {
    
    String model;
    User owner;
    
}
