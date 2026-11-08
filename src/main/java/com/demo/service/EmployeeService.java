package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.dto.EmployeeDto;

public interface EmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto getById(int empNo);

    void add(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto);

    void delete(EmployeeDto employeeDto);

    List<EmployeeDto> getByFirstName(String firstName);

    List<String> getDistinctNames();

    Map<String, Long> groupByDistinctName();
}
