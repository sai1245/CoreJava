import io.endeavourtech.inheritance.Account;
import io.endeavourtech.inheritance.CheckingAccount;
import io.endeavourtech.inheritance.SavingAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GenericsPlayground {
    public static void main(String[] args) {
        List<Account> checkingAccountList1 = new ArrayList<>();
        checkingAccountList1.add(new CheckingAccount("CHK1",new BigDecimal("150")));
        checkingAccountList1.add(new CheckingAccount("CHK2",new BigDecimal("250")));

        List<Account> savingsAccountList1 = new ArrayList<>();
        savingsAccountList1.add(new SavingAccount("SAV1",new BigDecimal("1000")));
        savingsAccountList1.add(new SavingAccount("SAV2",new BigDecimal("2000")));

        calculateTotalAccountBalance(checkingAccountList1);
        calculateTotalAccountBalance(savingsAccountList1);

        System.out.println(checkingAccountList1);
        System.out.println(savingsAccountList1);

        if (savingsAccountList1.contains(new SavingAccount("SAV1",new BigDecimal("1500")))){
            System.out.println("Savings account is already exists");
        }

        checkingAccountList1.addAll(savingsAccountList1);

        calculateTotalAccountBalance(checkingAccountList1);

        List<CheckingAccount> checkingAccountList2 = new ArrayList<>();
        checkingAccountList2.add(new CheckingAccount("CHK3",new BigDecimal("300")));
        checkingAccountList2.add(new CheckingAccount("CHK4",new BigDecimal("350")));



        List<SavingAccount> savingAccountList2 = new ArrayList<>();
        savingAccountList2.add(new SavingAccount("SAV3",new BigDecimal("2500")));
        savingAccountList2.add(new SavingAccount("SAV4",new BigDecimal("4000")));

        anotherCalculateTotalAccountBalance(checkingAccountList2);
        anotherCalculateTotalAccountBalance(savingAccountList2);

//        checkingAccountList2.addAll(savingAccountList2);

    }

    private static void anotherCalculateTotalAccountBalance(List<? extends Account> accountsList) {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account eachAccount : accountsList){
            totalBalance = totalBalance.add(eachAccount.getAccountBalance());
        }
        System.out.println("Total Account Balance"+totalBalance);
    }

    private static void calculateTotalAccountBalance(List<Account> accountList) {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Account eachAccount : accountList){
            totalBalance = totalBalance.add(eachAccount.getAccountBalance());
        }
        System.out.println("Total Account Balance"+totalBalance);
    }
}
