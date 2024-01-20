import io.endeavourtech.inheritance.CheckingAccount;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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




        //First example of streams
        List<Integer> integerList=List.of(1,2,3,4,5,6,7,8,9);
        List<Integer> evenSquareCollectioList = integerList.stream()
                .filter(n -> n % 2 != 0) //Fileter intermediate function takes Predicate as input
                .map(n -> n * n)    //Map intermediate function takes a function as input
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .collect(Collectors.toList());//Terminal function


        System.out.println(evenSquareCollectioList);


        //optional
        String inputValue = "Sai Krishna";
//        String inputValue = null;
        String outputValue = null;
        Optional<String> exampleOptional = Optional.ofNullable(inputValue);
        if (exampleOptional.isPresent()){
            outputValue=exampleOptional.get();
        }
        else {
            outputValue="Whatever";
        }
        System.out.println(outputValue);

        String anotherOutputValue = Optional.ofNullable(inputValue).orElse("whatever");
        System.out.println(anotherOutputValue);

        exampleOptional.ifPresent(s-> System.out.println(s));   //using lamda function
        exampleOptional.ifPresent(System.out::println);     //functional interfaces

        //Example of reduce -- termianal function
        Optional<Integer> sumOptional = integerList.stream()
                .reduce((a,b)->a+b); //terminal function
        sumOptional.ifPresent(num -> System.out.println("Sum of values in the list :"+num));
    }
}
