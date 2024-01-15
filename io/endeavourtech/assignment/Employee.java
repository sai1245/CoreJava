package io.endeavourtech.assignment;

import java.math.BigDecimal;

public class Employee {
    private String employeeName;

    private String jobTitle;

    private String jobDuty;
    private BigDecimal salary;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee(String employeeName, String jobTitle, String jobDuty, BigDecimal salary){
        this.employeeName=employeeName;
        this.jobTitle=jobTitle;
        this.jobDuty=jobDuty;
        this.salary=salary;
    }

    public BigDecimal calculateBonus(){
        return new BigDecimal("0");
    }
}
