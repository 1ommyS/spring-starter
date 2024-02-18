package com.example.demo.presentation.car.dto.commands;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UpdateCarCommand {
    private String title;
    private Integer price;
    private Date createdAt;
    private Date selledAt;
    private Integer amountOfSelled;
    private Integer userId;
}
