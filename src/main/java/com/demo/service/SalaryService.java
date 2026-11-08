package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.demo.dto.SalaryDto;

public interface SalaryService {

    void add(SalaryDto salaryDto);

    void update(SalaryDto salaryDto);

    void delete(SalaryDto salaryDto);

    SalaryDto getById(int empNo, LocalDate fromDate);

    List<SalaryDto> getAll();
}
