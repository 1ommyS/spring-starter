package com.example.demo.presentation.car.dto.commands;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class UpdateCarCommand {
    private String title;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime selledAt;
    private Integer amountOfSelled;
    private Integer userId;
}
