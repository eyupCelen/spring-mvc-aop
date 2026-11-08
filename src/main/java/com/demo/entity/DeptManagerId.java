package com.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class DeptManagerId implements Serializable {

    private Integer empNo;
    private String deptNo;

    public DeptManagerId() {
    }

    public DeptManagerId(Integer empNo, String deptNo) {
        this.empNo = empNo;
        this.deptNo = deptNo;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeptManagerId deptManagerId)) {
            return false;
        }
        return Objects.equals(empNo, deptManagerId.empNo) && Objects.equals(deptNo, deptManagerId.deptNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, deptNo);
    }
}
