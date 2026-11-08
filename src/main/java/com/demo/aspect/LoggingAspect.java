package com.demo.aspect;

import java.time.LocalDate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.demo.dto.DepartmentDto;
import com.demo.dto.EmployeeDto;
import com.demo.dto.SalaryDto;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(
            pointcut = "execution(* com.demo.controller.EmployeeController.getById(..)) && args(empNo)",
            returning = "employeeDto")
    public void logEmployeeGetById(int empNo, EmployeeDto employeeDto) {
        log.info("Employee information returned for empNo: {}", empNo);
        log.info("{}", employeeDto);
    }

    @AfterReturning(
            pointcut = "execution(* com.demo.controller.SalaryController.getById(..)) && args(empNo, fromDate)",
            returning = "salaryDto")
    public void logSalaryGetById(int empNo, LocalDate fromDate, SalaryDto salaryDto) {
        log.info("Salary information returned for empNo: {}, fromDate: {}", empNo, fromDate);
        log.info("{}", salaryDto);
    }

    @AfterReturning(
            pointcut = "execution(* com.demo.controller.DepartmentController.getById(..)) && args(deptNo)",
            returning = "departmentDto")
    public void logDepartmentGetById(String deptNo, DepartmentDto departmentDto) {
        log.info("Department information returned for deptNo: {}", deptNo);
        log.info("{}", departmentDto);
    }

    @Around("execution(* com.demo.controller.EmployeeController.getAll(..))")
    public Object logGetAllEmployees(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Request received for employee list.");
        long startedAt = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("Returned the employee list in {} ms.", System.currentTimeMillis() - startedAt);
        return result;
    }

    @AfterThrowing("execution(* com.demo.controller.EmployeeController.getById(..)) && args(empNo)")
    public void logInvalidId(int empNo) {
        log.error("Employee not found for empNo: {}", empNo);
    }

    @Before("execution(* com.demo.controller.EmployeeController.groupByDistinctName(..))")
    public void logGroupByRequest() {
        log.info("Group by request has been received.");
    }
}
