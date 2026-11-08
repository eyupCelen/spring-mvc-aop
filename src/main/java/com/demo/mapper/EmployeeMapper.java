package com.demo.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.demo.dto.EmployeeDto;
import com.demo.entity.Employee;

@Component
public class EmployeeMapper implements Function<Employee, EmployeeDto> {

    @Override
    public EmployeeDto apply(Employee employee) {
        return toDto(employee);
    }

    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getEmpNo(),
                employee.getBirthDate(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getHireDate());
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.empNo(),
                employeeDto.birthDate(),
                employeeDto.firstName(),
                employeeDto.lastName(),
                employeeDto.gender(),
                employeeDto.hireDate());
    }
}
