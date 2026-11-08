package com.demo.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.demo.dto.SalaryDto;
import com.demo.entity.Salary;

@Component
public class SalaryMapper implements Function<Salary, SalaryDto> {

    @Override
    public SalaryDto apply(Salary salary) {
        return toDto(salary);
    }

    public SalaryDto toDto(Salary salary) {
        return new SalaryDto(
                salary.getEmpNo(),
                salary.getSalary(),
                salary.getFromDate(),
                salary.getToDate());
    }

    public Salary toEntity(SalaryDto salaryDto) {
        return new Salary(
                salaryDto.empNo(),
                salaryDto.salary(),
                salaryDto.fromDate(),
                salaryDto.toDate());
    }
}
