package com.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "salaries")
@IdClass(SalaryId.class)
public class Salary {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "salary")
    private Integer salary;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    public Salary() {
    }

    public Salary(Integer empNo, Integer salary, LocalDate fromDate, LocalDate toDate) {
        this.empNo = empNo;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }
}
