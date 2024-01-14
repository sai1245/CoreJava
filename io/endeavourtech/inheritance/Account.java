package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public abstract class Account {

    private String accountNumber;
    private BigDecimal accountBalance;

    public Account(String accountNumber, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * Method that debits the debit amount from the acount balance
     * @param debitAmount Amount to be debited
     */
    public void debit(BigDecimal debitAmount){
        accountBalance=accountBalance.subtract(debitAmount);
    }

    public  void  credit(BigDecimal creditAmount){
        accountBalance=accountBalance.add(creditAmount);
    }

    public abstract void printDetails();
}


