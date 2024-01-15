package io.endeavourtech.inheritance;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account {

    private String accountNumber;
    private BigDecimal accountBalance;

    public Account(String accountNumber, BigDecimal accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}


