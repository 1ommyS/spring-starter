package com.example.demo.domain.entity;

import com.example.demo.domain.valueobj.BaseClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseClass {

    private String name;

}
