import java.util.Arrays;

public class ArrayExamples {
    public static void main(String[] args) {
        int[] intArray= new int[]{1,2,3,4,5,6,7,8,9,10};       //Defining and asssigning vakues in the array
        int[] squareArray= new int[10];

        System.out.println("Element in teh fifth position is :"+intArray[4]);
        System.out.println("length of intArray is :"+intArray.length);

        for (int i=0;i<intArray.length;i++){
            System.out.println(intArray[i]);
//            squareArray[i] = intArray[i]*intArray[i];
        }

        for (int i : intArray) {
            System.out.println(i*i);
        }

        System.out.println("values in the int array are :"+ Arrays.toString(intArray));

        int[] unsortedArray = new int[]{6,0,8,2,5,9,3};
        Arrays.sort(unsortedArray);
        System.out.println("Sorted Array is : "+Arrays.toString(unsortedArray));

        String[] tickerArray=new String[]{"AAPL","NVDA","V","GOOGL","MSFT"};

        for (String eachTicker : tickerArray) {
            System.out.println(eachTicker);

        }
    }
}
