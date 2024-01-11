public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");

        int i;  //variable declaration
        i=54;   //variable assignment
        int j=25;   //variable declaration and assignment

        int l=sumOfNumbers(i,j);
        System.out.println("sum of numbers using method is "+l);
        int k=i+j;
        System.out.println("Value in K is "+k);


        float f1=15.456f;
        float f2=13.643f;
        System.out.println("Sum of float is "+(f1+f2));

        caluculateFloatingSum(i,j,f1);

        double d1=16.34;
        double d2=19.66;

        float df1 = (float) d1;     //typecasting : changing the data type of a variable

        //System.out.println("sum of doubles is "+(d1+d2));
        printSumOfDoubles(d1,d2);

        boolean sample=true;
        if (sample){
            System.out.println("Sample boolean value is True");
        } else {
            System.out.println("Sample boolean value is False");
        }

        char chraSample = 'G'; //single quote for character
        String sampleString = "Sai Krishna Kamatham";
        System.out.println(sampleString);

        float v = caluculateFloatingsum(i, j, f1);
        System.out.println("output "+v);
        objectCreation();
        }
    public static void objectCreation() {
        Stocks appleStock = new Stocks("AAPL" ,"APPLE");
        appleStock.setSectorID(10);
        appleStock.setMarketCap(15.246);
        Stocks microsoftStock = new Stocks("MSFT","Microsoft");
        microsoftStock.setSectorID(10);
        microsoftStock.setMarketCap(12.246);
        Stocks newOne = new Stocks("TSLA","Tesla");
        microsoftStock.setSectorID(13);
        microsoftStock.setMarketCap(13.579);

        playingWithObjects(appleStock, appleStock.getMarketCap());
        System.out.println("The Ticker symbol is "+appleStock.getTickerSymbol());
        System.out.println("The marketcap is"+appleStock.getMarketCap());


    }

    private static void playingWithObjects(Stocks someStock, double marketCap) {
        System.out.println("The Ticker symbol is"+someStock.getTickerSymbol());
        System.out.println("The marketcap is "+someStock.getMarketCap());
        System.out.println("Local market Cap "+marketCap);
        marketCap=256;
        System.out.println("market Cap "+marketCap);

        someStock = new Stocks("V","Visa");

    }

    static int sumOfNumbers(int n1, int n2){   //method signature (return type, method name, inputs)
        return n1+n2;
    }
    static void printSumOfDoubles(double num1, double num2){
        double num3=10.55;
        System.out.println("Sum of given doubles is "+(num1+num2+num3));
    }
    static void caluculateFloatingSum(int n1, int n2,float f){
        System.out.println("Sum of the give parameters is "+(n1+n2+f));
    }
    static float caluculateFloatingsum(int n1, int n2, float f){
        float n11 = n1;
        float n22 = n2;
        return (n11+n22+f);
    }
    }



