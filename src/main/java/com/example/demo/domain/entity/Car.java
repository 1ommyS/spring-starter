package com.example.demo.domain.entity;

import java.util.Date;

/*
 * Класс описывает машину
 * Необходимо реализовать контроллер, сервис, которые отдают все машины, конкретную, создают, обновляют, удаляют
 */
public class Car {
    private Integer id;

    private String title;

    private Integer price;

    private Date createdAt;
    private Date selledAt;
    private Integer amountOfSelled;

    // как правильно обрабатывать владельца в комманде/квери, подсказка: Jackson поможет
    private User owner;
}
