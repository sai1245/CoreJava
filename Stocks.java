import java.util.Objects;

/**
 * This class represent unique stocks in the US
 */
public class Stocks {

    public static final String someValue="somevalue";
    /**
     * Constructor that forces you to enter ticker symbol and ticker name
     */
    public Stocks(String outsideTickerSymbol, String outsideTickerName) {
        this.tickerSymbol = outsideTickerSymbol;
        this.tickerName = outsideTickerName;
    }

    public static String someJunkMethod(String junkString){
        return someValue+junkString;
    }

    static {
        System.out.println("Printing the static block statement");
    }

    private String tickerSymbol;    //instance variable
    private String tickerName;      //instance variable
    private int sectorID;           //instance variable
    private long marketCap;       //instance variable

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getTickerName() {
        return tickerName;
    }

    public int getSectorID() {
        return sectorID;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public static void newMethod(Stocks stockName) {
        System.out.println("Ticker Symbol is " + stockName.getTickerSymbol());
        System.out.println("Ticker Name is " + stockName.getTickerName());
        System.out.println("Ticker Market Cap is " + stockName.getMarketCap());
        System.out.println("Ticker ID is " + stockName.getSectorID());
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tickerName='" + tickerName + '\'' +
                ", sectorID=" + sectorID +
                ", marketCap=" + marketCap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stocks stocks = (Stocks) o;
        return Objects.equals(tickerSymbol, stocks.tickerSymbol) && Objects.equals(tickerName, stocks.tickerName);
    }

//    @Override
//    public boolean equals(Object otherobject){
//        boolean isEqual = false;
//        Stocks otherStockObj = null;
//        if(otherStockObj!= null && otherobject instanceof Stocks){
//            otherStockObj = (Stocks)otherStockObj;
//            if (otherStockObj.getTickerSymbol().equals(this.tickerSymbol) && otherStockObj.getTickerName().equals(this.tickerName)){
//                isEqual=false;
//            }
//        }
//
//        return isEqual;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(tickerSymbol, tickerName);
    }
}

