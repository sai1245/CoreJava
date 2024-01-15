package io.endeavourtech.assignment;

import java.math.BigDecimal;

public class Developer extends Employee{
    public Developer(String employeeName,String jobtitle,String jobduty, BigDecimal salary) {
        super(employeeName,jobtitle,jobduty, salary);
    }

    public BigDecimal calculateBonus(){
        return (getSalary().multiply(new BigDecimal("10")).divide(new BigDecimal("100"))).multiply(new BigDecimal("12"));
    }
}
