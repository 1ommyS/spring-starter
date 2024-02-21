package com.example.demo.domain.valueobj;


import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Embeddable
public class BaseInfo {
    private String name;
    private String address;
}
