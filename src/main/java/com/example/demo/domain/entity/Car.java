package com.example.demo.domain.entity;

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
public class Car {
    private Integer id;

    private String title;

    private Integer price;

    private Date createdAt;
    private Date selledAt;
    private Integer amountOfSelled;

    // как правильно обрабатывать владельца в комманде/квери, подсказка: Jackson поможет
    private User owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(title, car.title) && Objects.equals(price, car.price) && Objects.equals(createdAt, car.createdAt) && Objects.equals(selledAt, car.selledAt) && Objects.equals(amountOfSelled, car.amountOfSelled) && Objects.equals(owner, car.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, createdAt, selledAt, amountOfSelled, owner);
    }
}
