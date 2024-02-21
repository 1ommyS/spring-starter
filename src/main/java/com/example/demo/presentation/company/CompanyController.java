package com.example.demo.presentation.company;

import com.example.demo.application.company.service.CompanyService;
import com.example.demo.domain.entity.Company;
import com.example.demo.domain.valueobj.BaseInfo;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("company")
public class CompanyController {
    private CompanyService service;

    @GetMapping
    public List<BaseInfo> getMainInformation() {
        return service.getMainInformation();
    }

    @PostMapping
    public Company create(@RequestBody  BaseInfo baseInfo) {
        return service.create(baseInfo);
    }
}
