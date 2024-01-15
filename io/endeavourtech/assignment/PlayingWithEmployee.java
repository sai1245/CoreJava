package io.endeavourtech.assignment;

import java.math.BigDecimal;

public class PlayingWithEmployee {
    public static void main(String[] args) {

        Manager manager= new Manager("Varun","Manager","Manages Application",new BigDecimal("10000"));
        System.out.println("Manager's Monthly Income is :"+manager.getSalary());
        System.out.println("Manager's Yearly Bonus is :"+manager.calculateBonus());
        Tester tester= new Tester("Sneha","Tester","Testing department",new BigDecimal("1000"));
        System.out.println("Tester's Monthly income is :"+tester.getSalary());
        System.out.println("Tester's Monthly Bonus is :"+tester.calculateBonus());
        Developer developer= new Developer("Krishna","Develoepr","Developes the Application",new BigDecimal("100"));
        System.out.println("Developer's Monthly income is :"+developer.getSalary());
        System.out.println("Developer's Monthly Bonus is :"+developer.calculateBonus());


        System.out.println("Total Bonus is :"+(manager.calculateBonus().add(tester.calculateBonus()).add(developer.calculateBonus())));
    }
}
