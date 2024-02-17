package com.example.demo.presentation.user.dto.commands;

import com.example.demo.presentation.user.dto.queries.UserQuery;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CreateCarCommand {

    private String title;
    private Integer price;
    private Date createdAt;
    private Date selledAt;
    private Integer amountOfSelled;
    private Integer userId;

}
