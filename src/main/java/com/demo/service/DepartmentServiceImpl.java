package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.DepartmentDto;
import com.demo.entity.Department;
import com.demo.exception.ResourceNotFoundException;
import com.demo.mapper.DepartmentMapper;
import com.demo.repository.DepartmentRepository;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    @Transactional
    public void add(DepartmentDto departmentDto) {
        departmentRepository.save(departmentMapper.toEntity(departmentDto));
    }

    @Override
    @Transactional
    public void update(DepartmentDto departmentDto) {
        if (!departmentRepository.existsById(departmentDto.deptNo())) {
            throw new ResourceNotFoundException("Department not found: " + departmentDto.deptNo());
        }
        departmentRepository.save(departmentMapper.toEntity(departmentDto));
    }

    @Override
    @Transactional
    public void delete(DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentDto.deptNo())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + departmentDto.deptNo()));
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentDto getById(String deptNo) {
        Department department = departmentRepository.findById(deptNo)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + deptNo));
        return departmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper)
                .toList();
    }
}
