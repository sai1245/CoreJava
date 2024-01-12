import java.util.Arrays;

public class StringManipulations {
    public static void main(String[] args) {
        String sampleString1 = "Endeavor";
        String sampleString2 = new String("Technologies");

        String concastString = sampleString1+sampleString2;
        System.out.println("Concat String is :"+concastString);

        System.out.println("Length of teh string1 is :"+sampleString1.length());

        System.out.println("Converting a string into Uppercase :"+sampleString1.toUpperCase());

        System.out.println(sampleString1);

        System.out.println("Substring of the given string is :"+sampleString1.substring(3));

        System.out.println("Substring the given index in other method is"+sampleString1.substring(3,6));

        System.out.println("Character at position 3 of the string is : "+sampleString1.charAt(3));

        System.out.println("Does the String contains character sequence end "+sampleString1.contains("End"));

        System.out.println("Does the string character ends with end "+sampleString1.endsWith("end"));

        System.out.println("Is the string Blank? "+sampleString1.isBlank());

        String realWorldExample = "AAPL,GOOGL,MSFT,V,NVDA,TSLA";

        String[] splitArray = realWorldExample.split(",");
        System.out.println(Arrays.toString(splitArray));

        for (String s : splitArray) {
            System.out.println(s);
        }


    }
}
