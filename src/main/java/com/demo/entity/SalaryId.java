package com.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SalaryId implements Serializable {

    private Integer empNo;
    private LocalDate fromDate;

    public SalaryId() {
    }

    public SalaryId(Integer empNo, LocalDate fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SalaryId salaryId)) {
            return false;
        }
        return Objects.equals(empNo, salaryId.empNo) && Objects.equals(fromDate, salaryId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}
