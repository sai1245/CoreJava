import java.math.BigDecimal;
import java.util.function.*;

public class FunctionalProgrammingExamples {
    public static void main(String[] args) {

        //Example of Unary Operator
        calculateSquare(10);
        UnaryOperator<Integer> calculateSquareUO= (num) -> num*num;
        System.out.println("Square of number using Unary Operator is : "+calculateSquareUO.apply(10));


        //Example of Binary operator
        concatTwoStrings("Endeavour","Technologies");
        BinaryOperator<String> concatTwoStringsBO=(str1, str2) -> str1+str2;
        System.out.println("Concatinated strings are : "+concatTwoStringsBO.apply("Endeavour ","Technologies"));




        //example of supplier
        generateRandomValues();
        Supplier<Double> generateRandomValuesSP = () -> Math.random();
        System.out.println("Generated Random Values is : "+generateRandomValuesSP.get());


        //example of consumer
        consumeRandomValue("Sai Krishna");
        Consumer<String> consumeRandomValueCO = str -> System.out.println("The Consumer Operator "+str);
        consumeRandomValueCO.accept("Sai Krishna");



        Consumer<String> printSomeValueCS_FI = System.out::println;
        printSomeValueCS_FI.accept("USING FUNCTIONAL INTERFACE");

        //BiConsumer Example
        BiConsumer<String,Integer> biConsumeRandomValueCO = (str1, int1) -> System.out.println("The BiConsumer Operator Result is: "+(str1+int1));
        biConsumeRandomValueCO.accept("Sai Krishna ",07);

        //Example of Predicate

        inString("Sai");
        Predicate<String> inStringPD = str -> str.length()>10;
        System.out.println("IS Sai A long string : "+inStringPD.test("Sai Krishna Kamatham"));


        someJunkLogic("Srikar",new BigDecimal("100"));
        BiPredicate<String,BigDecimal> someJunkLogicBPD= (str1,bd1) -> {
            BigDecimal stringLengthBd = new BigDecimal(str1.length());
            return stringLengthBd.multiply(bd1).compareTo(new BigDecimal("1000"))>0;
        };
        System.out.println(someJunkLogicBPD.test("sai",new BigDecimal("100")));


        //example of function

        returnStringlength("Sai krishna");
        Function<String,Integer> returnStringlengthFN = str -> str.length()+10;
        System.out.println("The length is : "+returnStringlengthFN.apply("Sai Krishna"));

        //Example of BiFunction

        Function<String,Integer> returnStringlengthFN1 = String::length;        //Functional interface equivalent
        Function<String,Integer> returnStringlengthFN2 = returnStringlengthFN1.andThen(integer -> integer+10);  //Chaining functions using lambda
        System.out.println("String length using Chaining is : "+returnStringlengthFN2.apply("Sai Krishna"));


    }

    //method equivalent of function
    private static int returnStringlength(String str1) {
        return str1.length()+10;
    }


    //method equivalent of Bipredicate
    private static boolean someJunkLogic(String str1, BigDecimal int1) {
        BigDecimal stringLengthBD = new BigDecimal(str1.length());
        return (stringLengthBD.multiply(int1).compareTo(new BigDecimal("1000")))>0;
    }

    //method equivalent of predicate
    private static boolean inString(String str1) {
        return str1.length()>10;
    }


    //method equivalent of consumer function
    private static void consumeRandomValue(String str1) {
        System.out.println("Printed From the method :"+str1);
    }


    //method equivalent of supplier
    private static double generateRandomValues() {
        return Math.random();
    }

    //Method equivalent of Binary oeprator
    private static String concatTwoStrings(String str1, String str2) {
        return str1+str2;
    }

    //Method equivalent of Unary Operator
    private static int calculateSquare(int number) {
        return number*number;
    }
}
