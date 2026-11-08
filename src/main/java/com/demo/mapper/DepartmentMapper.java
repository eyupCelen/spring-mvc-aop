package com.demo.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.demo.dto.DepartmentDto;
import com.demo.entity.Department;

@Component
public class DepartmentMapper implements Function<Department, DepartmentDto> {

    @Override
    public DepartmentDto apply(Department department) {
        return toDto(department);
    }

    public DepartmentDto toDto(Department department) {
        return new DepartmentDto(department.getDeptNo(), department.getDeptName());
    }

    public Department toEntity(DepartmentDto departmentDto) {
        return new Department(departmentDto.deptNo(), departmentDto.deptName());
    }
}
