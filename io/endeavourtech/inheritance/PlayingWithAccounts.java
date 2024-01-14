package io.endeavourtech.inheritance;

import java.math.BigDecimal;

public class PlayingWithAccounts {
    public static void main(String[] args) {
        CheckingAccount checkingAccount1= new CheckingAccount("CHK1",new BigDecimal(100));
        checkingAccount1.debit(new BigDecimal(20));
        checkingAccount1.credit(new BigDecimal(15));

        SavingAccount savingAccount1 = new SavingAccount("SAV1",new BigDecimal(100));
        savingAccount1.debit(new BigDecimal(20));
        savingAccount1.credit(new BigDecimal(15));

        System.out.println("Account Balance of checkingAccount is "+checkingAccount1.getAccountBalance());
        System.out.println("Account Balance of savingsAccount is "+savingAccount1.getAccountBalance());

        //Reference variable of type Account can refer to objects of type Account or its subclasses- Polymorphism
        Account checkingAccount2= new CheckingAccount("CHK2",new BigDecimal(200));
        checkingAccount2.debit(new BigDecimal(100));//the object is of checkingAccount so CheckingAccount's debit method is called
        checkingAccount2.credit(new BigDecimal(100));


        Account savingsAccount2= new SavingAccount("SAV2",new BigDecimal(200));
        savingsAccount2.debit(new BigDecimal(100));
        savingsAccount2.credit(new BigDecimal(100));

        System.out.println("Account Balance of checkingAccount2 is "+checkingAccount2.getAccountBalance());
        System.out.println("Account Balance of savingAccount2 is "+savingsAccount2.getAccountBalance());

        System.out.println("Is checkingAccount1 an instance of Account?: "+ (checkingAccount1 instanceof Account));
        System.out.println("Is checkingAccount1 an instance of CheckingAccount?: "+ (checkingAccount1 instanceof CheckingAccount));
        System.out.println("Is savingAccount1 an instance of Account?: "+ (savingAccount1 instanceof Account));
//      System.out.println("Is savingAccount1 an instance of CheckingAccount?: "+ (savingAccount1 instanceof CheckingAccount)); //Does not even compile

        Account checkingAccount3=new CheckingAccount("CH3",new BigDecimal(250));
        Account savingsAccount3= new SavingAccount("SAV3",new BigDecimal(300));

        checkingAccount3.debit(new BigDecimal(50));
        checkingAccount3.credit(new BigDecimal(75));

        savingsAccount3.debit(new BigDecimal(75));
        savingsAccount3.credit(new BigDecimal(25));

        Account[] arrayOfAllAccounts= new Account[]{checkingAccount1,checkingAccount2,checkingAccount3,savingAccount1,savingsAccount2,savingsAccount3};

        calculateAccountBalanceTotal(arrayOfAllAccounts);


    }

    private static void calculateAccountBalanceTotal(Account[] accountsArray) {
        BigDecimal totalBalance=BigDecimal.ZERO;
        for (Account eachAccount : accountsArray) {
            totalBalance=totalBalance.add(eachAccount.getAccountBalance());
        }
        System.out.println("Total Balance: "+totalBalance);
    }
}

