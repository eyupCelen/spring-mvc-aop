package com.demo.dto;

import java.time.LocalDate;

public record SalaryDto(
        Integer empNo,
        Integer salary,
        LocalDate fromDate,
        LocalDate toDate) {
}
