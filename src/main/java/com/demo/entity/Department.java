package com.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", columnDefinition = "char")
    private String deptNo;

    @Column(name = "dept_name", unique = true)
    private String deptName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<DeptManager> deptManagers = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<DeptEmp> deptEmps = new ArrayList<>();

    public Department() {
    }

    public Department(String deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<DeptManager> getDeptManagers() {
        return deptManagers;
    }

    public List<DeptEmp> getDeptEmps() {
        return deptEmps;
    }
}
