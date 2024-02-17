package com.example.demo.presentation.user.dto.queries;

import com.example.demo.domain.entity.User;
import lombok.Data;

@Data
public class UserQuery {
    private Integer id;
    private String name;
}
