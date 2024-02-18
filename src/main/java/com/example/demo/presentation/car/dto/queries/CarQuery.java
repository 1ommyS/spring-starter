package com.example.demo.presentation.car.dto.queries;

import com.example.demo.presentation.user.dto.queries.UserQuery;
import lombok.Data;

@Data
public class CarQuery {
    private Integer id;
    private String title;
    private UserQuery owner;
}
