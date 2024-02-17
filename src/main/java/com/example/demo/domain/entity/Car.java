package com.example.demo.domain.entity;

import lombok.*;

import java.util.Date;

/*
 * Класс описывает машину
 * Необходимо реализовать контроллер, сервис, которые отдают все машины, конкретную, создают, обновляют, удаляют
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class Car {
    
    private Integer id;
    private String model;
    private Integer price;
    private Date dateManufactured;
    private Date dateSold;
    private Integer quantitySold;

    // как правильно обрабатывать владельца в комманде/квери, подсказка: Jackson поможет
    private User owner;
}
