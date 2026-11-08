package com.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TitleId implements Serializable {

    private Integer empNo;
    private String title;
    private LocalDate fromDate;

    public TitleId() {
    }

    public TitleId(Integer empNo, String title, LocalDate fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(other instanceof TitleId titleId)) {
            return false;
        }
        return Objects.equals(empNo, titleId.empNo)
                && Objects.equals(title, titleId.title)
                && Objects.equals(fromDate, titleId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, title, fromDate);
    }
}
