package com.example.demo.presentation.car.dto.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Builder
public class CreateCarCommand {
    @NotEmpty(message = "Название не может быть пустым")
    @Length(min = 3, max = 30, message = "Название должно быть от 3 до 30 символов")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Название должно состоять только из латинских букв")
    private String title;

    @Min(value = 100_000, message = "Цена должна быть больше 100.000Р")
    private Integer price;

    private Date createdAt;
    private Date selledAt;
    @Min(value = 0, message = "Количество должно быть больше 0")
    private Integer amountOfSelled;

    @Min(value = 1, message = "Пользователь должен быть больше 0")
    private Integer userId;
}
