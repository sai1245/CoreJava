/**
 * This class represent unique stocks in the US
 */
public class Stocks {
    /**
     * Constructor that forces you to enter ticker symbol and ticker name
     */
    public Stocks(String outsideTickerSymbol, String outsideTickerName) {
        this.tickerSymbol = outsideTickerSymbol;
        this.tickerName = outsideTickerName;
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
}