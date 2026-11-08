package com.demo.service;

import java.util.List;

import com.demo.dto.DepartmentDto;

public interface DepartmentService {

    void add(DepartmentDto departmentDto);

    void update(DepartmentDto departmentDto);

    void delete(DepartmentDto departmentDto);

    DepartmentDto getById(String deptNo);

    List<DepartmentDto> getAll();
}
