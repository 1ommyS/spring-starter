package com.example.demo.infrastructure.repository;

import com.example.demo.domain.entity.Company;
import com.example.demo.domain.valueobj.BaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // написать метод, который будет смотреть, находится ли ID компании в каком-то множестве
    @Query(nativeQuery = true, value = "select * from company where id in :ids")
    List<Company> findByIdIn(List<Long> ids);

    //удалить только те записи, где айдишник больше 10
    void deleteByIdGreaterThan(long x);

    // напишите метод, который позволяет выбрать только name и address компании (без айдишника)
    @Query(value = "select c.baseInfo from Company c")
    List<BaseInfo> findCompaniesBaseInfo();
}
