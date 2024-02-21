package com.example.demo.domain.entity;

import com.example.demo.domain.valueobj.BaseClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/*
 * Класс описывает машину
 * Необходимо реализовать контроллер, сервис, которые отдают все машины, конкретную, создают, обновляют, удаляют
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseClass {
    private String title;

    private Integer price;

    private Integer amountOfSelled;

    @ManyToOne
    private Company company;

    // как правильно обрабатывать владельца в комманде/квери, подсказка: Jackson поможет
    @ManyToOne
    private User owner;


}
