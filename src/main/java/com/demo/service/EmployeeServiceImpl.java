package com.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.EmployeeDto;
import com.demo.entity.Employee;
import com.demo.exception.ResourceNotFoundException;
import com.demo.mapper.EmployeeMapper;
import com.demo.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper)
                .toList();
    }

    @Override
    public EmployeeDto getById(int empNo) {
        Employee employee = employeeRepository.findById(empNo)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + empNo));
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName)
                .stream()
                .map(employeeMapper)
                .toList();
    }

    @Override
    public List<String> getDistinctNames() {
        return employeeRepository.findDistinctFirstNames();
    }

    @Override
    public Map<String, Long> groupByDistinctName() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper)
                .collect(Collectors.groupingBy(EmployeeDto::firstName, Collectors.counting()));
    }

    @Override
    @Transactional
    public void add(EmployeeDto employeeDto) {
        employeeRepository.save(employeeMapper.toEntity(employeeDto));
    }

    @Override
    @Transactional
    public void update(EmployeeDto employeeDto) {
        if (!employeeRepository.existsById(employeeDto.empNo())) {
            throw new ResourceNotFoundException("Employee not found: " + employeeDto.empNo());
        }
        employeeRepository.save(employeeMapper.toEntity(employeeDto));
    }

    @Override
    @Transactional
    public void delete(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.empNo())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + employeeDto.empNo()));
        employeeRepository.delete(employee);
    }
}
