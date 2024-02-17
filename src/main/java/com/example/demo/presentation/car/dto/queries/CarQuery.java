package com.example.demo.presentation.car.dto.queries;

import com.example.demo.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CarQuery {
    
    private Integer id;
    private String model;
    private User owner;
    
}
