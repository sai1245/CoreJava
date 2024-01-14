package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class CheckingAccount extends Account {

    private static double cashBackRate=2.0;
    /**
     * Constructor in the subclass that calls the super class constructor
     * @param accountNumber
     * @param accountBalance
     */
    public CheckingAccount(String accountNumber, BigDecimal accountBalance) {
        super(accountNumber, accountBalance);
    }

    @Override
    public void debit(BigDecimal debitAmount) {
        BigDecimal cashBackAmount = debitAmount.multiply(new BigDecimal(cashBackRate)).divide(new BigDecimal(100));
        super.debit(debitAmount);
        super.credit(cashBackAmount);
    }

    @Override
    public void printDetails() {
        System.out.println("Checking Account");
    }
}
