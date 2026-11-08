package com.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.demo.dto.EmployeeDto;
import com.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(params = "firstName")
    public List<EmployeeDto> getByFirstName(@RequestParam String firstName) {
        return employeeService.getByFirstName(firstName);
    }

    @PostMapping
    public void add(@RequestBody EmployeeDto employeeDto) {
        employeeService.add(employeeDto);
    }

    @PutMapping
    public void update(@RequestBody EmployeeDto employeeDto) {
        employeeService.update(employeeDto);
    }

    @DeleteMapping
    public void delete(@RequestBody EmployeeDto employeeDto) {
        employeeService.delete(employeeDto);
    }

    @GetMapping("/{empNo}")
    public EmployeeDto getById(@PathVariable int empNo) {
        return employeeService.getById(empNo);
    }

    @GetMapping("/stats/grouped-names")
    public Map<String, Long> groupByDistinctName() {
        return employeeService.groupByDistinctName();
    }

    @GetMapping("/stats/distinct-names")
    public List<String> getDistinctNameList() {
        return employeeService.getDistinctNames();
    }
}
