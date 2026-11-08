package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.SalaryDto;
import com.demo.entity.Salary;
import com.demo.entity.SalaryId;
import com.demo.exception.ResourceNotFoundException;
import com.demo.mapper.SalaryMapper;
import com.demo.repository.SalaryRepository;

@Service
@Transactional(readOnly = true)
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final SalaryMapper salaryMapper;

    public SalaryServiceImpl(SalaryRepository salaryRepository, SalaryMapper salaryMapper) {
        this.salaryRepository = salaryRepository;
        this.salaryMapper = salaryMapper;
    }

    @Override
    @Transactional
    public void add(SalaryDto salaryDto) {
        salaryRepository.save(salaryMapper.toEntity(salaryDto));
    }

    @Override
    @Transactional
    public void update(SalaryDto salaryDto) {
        SalaryId salaryId = new SalaryId(salaryDto.empNo(), salaryDto.fromDate());
        if (!salaryRepository.existsById(salaryId)) {
            throw new ResourceNotFoundException(
                    "Salary not found: empNo=" + salaryDto.empNo() + ", fromDate=" + salaryDto.fromDate());
        }
        salaryRepository.save(salaryMapper.toEntity(salaryDto));
    }

    @Override
    @Transactional
    public void delete(SalaryDto salaryDto) {
        Salary salary = salaryRepository
                .findByEmpNoAndFromDate(salaryDto.empNo(), salaryDto.fromDate())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Salary not found: empNo=" + salaryDto.empNo() + ", fromDate=" + salaryDto.fromDate()));
        salaryRepository.delete(salary);
    }

    @Override
    public SalaryDto getById(int empNo, LocalDate fromDate) {
        Salary salary = salaryRepository.findByEmpNoAndFromDate(empNo, fromDate)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Salary not found: empNo=" + empNo + ", fromDate=" + fromDate));
        return salaryMapper.toDto(salary);
    }

    @Override
    public List<SalaryDto> getAll() {
        return salaryRepository.findAll()
                .stream()
                .map(salaryMapper)
                .toList();
    }
}
