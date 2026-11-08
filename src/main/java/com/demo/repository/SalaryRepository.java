package com.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Salary;
import com.demo.entity.SalaryId;

public interface SalaryRepository extends JpaRepository<Salary, SalaryId> {

    Optional<Salary> findByEmpNoAndFromDate(Integer empNo, LocalDate fromDate);
}
