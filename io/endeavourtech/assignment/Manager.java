package io.endeavourtech.assignment;

import java.math.BigDecimal;

public class Manager extends Employee{
    public Manager(String employeeName,String jobTitle,String jobDuty, BigDecimal salary) {
        super(employeeName,jobTitle,jobDuty, salary);
    }

    public BigDecimal calculateBonus(){
        return (((getSalary().multiply(new BigDecimal("20")).divide(new BigDecimal("100"))).multiply(new BigDecimal("12"))));
    }
}
