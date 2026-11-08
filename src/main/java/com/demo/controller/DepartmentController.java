package com.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.demo.dto.DepartmentDto;
import com.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public void add(@RequestBody DepartmentDto departmentDto) {
        departmentService.add(departmentDto);
    }

    @PutMapping
    public void update(@RequestBody DepartmentDto departmentDto) {
        departmentService.update(departmentDto);
    }

    @DeleteMapping
    public void delete(@RequestBody DepartmentDto departmentDto) {
        departmentService.delete(departmentDto);
    }

    @GetMapping
    public List<DepartmentDto> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{deptNo}")
    public DepartmentDto getById(@PathVariable String deptNo) {
        return departmentService.getById(deptNo);
    }
}
