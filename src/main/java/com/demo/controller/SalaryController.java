package com.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.demo.dto.SalaryDto;
import com.demo.service.SalaryService;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping
    public void add(@RequestBody SalaryDto salaryDto) {
        salaryService.add(salaryDto);
    }

    @PutMapping
    public void update(@RequestBody SalaryDto salaryDto) {
        salaryService.update(salaryDto);
    }

    @DeleteMapping
    public void delete(@RequestBody SalaryDto salaryDto) {
        salaryService.delete(salaryDto);
    }

    @GetMapping
    public List<SalaryDto> getAll() {
        return salaryService.getAll();
    }

    @GetMapping("/{empNo}/{fromDate}")
    public SalaryDto getById(@PathVariable int empNo, @PathVariable LocalDate fromDate) {
        return salaryService.getById(empNo, fromDate);
    }
}
