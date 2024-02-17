package com.example.demo.presentation.user.dto.queries;

import lombok.Data;

@Data
public class CarQuery {

    private Integer id;
    private String title;
    private UserQuery owner;

}
