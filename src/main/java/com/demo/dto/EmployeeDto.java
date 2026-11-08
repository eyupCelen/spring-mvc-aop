package com.demo.dto;

import java.time.LocalDate;

import com.demo.entity.Gender;

public record EmployeeDto(
        Integer empNo,
        LocalDate birthDate,
        String firstName,
        String lastName,
        Gender gender,
        LocalDate hireDate) {
}
