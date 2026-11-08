package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entity.Employee;
import com.demo.entity.Gender;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByGender(Gender gender);

    @Query("select distinct e.firstName from Employee e order by e.firstName")
    List<String> findDistinctFirstNames();
}
