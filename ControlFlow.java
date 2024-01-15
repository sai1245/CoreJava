public class ControlFlow {
    public static void main(String[] args) {
        int i =8;  //= is used to assign the value

        boolean sample = i==15;     //== means checking the equality
        System.out.println("Is i equal to 15 ? "+sample);

        if (i%2 == 0){
            System.out.println("i is Even");
        }
        else {
            System.out.println("i is Odd");
        }


        if (i%2 ==0 || i>10){
            System.out.println("i is even or greater than 10");
        }
        else {
            System.out.println("i is odd or less than 10");
        }


        String str1="Sai Krishna";
        String str2="Sai Krishna";

        if (str1==str2){
            System.out.println("Both are same using ==");
        }
        else{
            System.out.println("Both are different");
        }


        Stocks appleStock1=new Stocks("AAPL","APPLE");
        Stocks appleStocks2=new Stocks("AAPL","APPLE");
        if (appleStock1==appleStocks2){
            System.out.println("Both the stocks are same");
        }
        else {
            System.out.println("Both the stocks are different");
        }

        if (appleStock1.equals(appleStocks2)){
            System.out.println("Both the stocks are same using equals");
        }
        else {
            System.out.println("Both the stocks are different using equals");
        }

        if (appleStock1.equals(null)){
            System.out.println("Both the stocks are same using equals");
        }
        else {
            System.out.println("Both the stocks are different using equals");
        }

        int k=10;
        while (k>0){
            System.out.println("value of k is "+k);
            k--;
        }


        double randomNumber=Math.random();
        String str = null;

        String anotherString = null;
        if(randomNumber>0.5)
            str="Greater than 0.5";
        else
            str ="less than 0.5";

        System.out.println(str);
        System.out.println("generated random number is "+randomNumber);

        anotherString=(randomNumber>0.5)?"Greater than 0.5":"less than 0.5";
        System.out.println(anotherString);


        Stocks appleStock = new Stocks("AAPL","APPLE");
        Stocks microsoftStock = new Stocks("MSFT","Microsoft");
        Stocks teslaStock = new Stocks("TSLA","TESLA");
        Stocks googleStock = new Stocks("GOOGL","GOOGLE");

        stupidInvestmentMethod(appleStock);


        for (int m=0;m<=10;m++) {
            if (m == 3) {
                continue;
            } else {
                System.out.println(m);
            }
        }

    }

    private static void stupidInvestmentMethod(Stocks inputStocks) {
        if (inputStocks.getTickerSymbol().equals("AAPL")){
            System.out.println("Sell the Stock");
        } else if (inputStocks.getTickerSymbol().equals("MSFT")) {
            System.out.println("Hold the stock");
        } else if (inputStocks.getTickerSymbol().equals("TSLA")) {
            System.out.println("Buy the Stock");
        }else {
            System.out.println("whatever");
        }

        switch (inputStocks.getTickerSymbol()){
            case("AAPL")->System.out.println("Sell the Stock using switch");
            case("MSFT")->System.out.println("Hold the stock using switch");
            case ("TSLA")->System.out.println("Buy the Stock using switch");

        }

    }

}
