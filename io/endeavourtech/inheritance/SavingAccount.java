package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class SavingAccount extends Account{
    public SavingAccount(String accountNumber, BigDecimal accountBalance) {
        super(accountNumber, accountBalance);
    }

    @Override
    public void printDetails() {
        System.out.println("Savings Account");
    }

    @Override
    public String toString() {
        return "SavingAccount{} Account Number is: "+getAccountNumber()+" , Account Balance: "+getAccountBalance();
    }
}
