package com.example.demo.application.company.service;

import com.example.demo.domain.entity.Company;
import com.example.demo.domain.valueobj.BaseInfo;
import com.example.demo.infrastructure.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {
    private CompanyRepository companyRepository;

    public List<BaseInfo> getMainInformation() {
        log.info("CompanyService.getMainInformation | started method");

        return companyRepository.findCompaniesBaseInfo();
    }

    public Company create(BaseInfo baseInfo) {
        log.info("CompanyService.create | started method. BaseInfo: " + baseInfo);
        var company = new Company(baseInfo);
        log.info(company.toString());
        return companyRepository.save(company);

    }
}
