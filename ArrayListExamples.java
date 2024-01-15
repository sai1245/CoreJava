import io.endeavourtech.inheritance.CheckingAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayListExamples {
    public static void main(String[] args) {
        ArrayList<String> myFirstArrayList = new ArrayList<>();
        myFirstArrayList.add("AAPL");
        myFirstArrayList.add("MSFT");
        myFirstArrayList.add("GOOGL");
        myFirstArrayList.add("TSLA");

        System.out.println(myFirstArrayList);
        System.out.println("Size of the arrayList is "+myFirstArrayList.size());

        myFirstArrayList.remove(1);
        myFirstArrayList.remove("TSLA");

        if (myFirstArrayList.contains("AAPL")) {
            System.out.println("myFirstArray list contais Apple");

            }

        List<String> someOtehrList = new ArrayList<>();
        if (someOtehrList.isEmpty()){
            System.out.println("someOtherList is Empty");
        }

        if (!myFirstArrayList.isEmpty()){
            System.out.println("someOtherList is not empty");
        }

        myFirstArrayList.add("NVDA");
        myFirstArrayList.add("AMD");
        myFirstArrayList.add("V");

        for (int i=0; i<myFirstArrayList.size();i++){
            System.out.println(myFirstArrayList.get(i));
        }

        for (String eachTicker : myFirstArrayList){
            System.out.println("each Ticker from enhanced for each loop is "+eachTicker);
        }


        Collections.sort(myFirstArrayList);



        //Things not to do with the arrayLists
        ArrayList junkArrayList = new ArrayList();
        junkArrayList.add("some Juncking");
        junkArrayList.add(new BigDecimal("500"));
        junkArrayList.add(new CheckingAccount("Account 1",new BigDecimal("1209")));


    }
}
