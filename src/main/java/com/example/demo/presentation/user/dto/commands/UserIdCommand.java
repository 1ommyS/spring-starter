package com.example.demo.presentation.user.dto.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdCommand {
    private Integer id;
}
