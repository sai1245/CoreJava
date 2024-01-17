package io.endeavourtech.stocks.vo;

import java.math.BigDecimal;
import java.util.Objects;

public class StockFundamentalsVo {

    private String tickerName;

    private String tickerSymbol;

    private Long marketCap;
    private BigDecimal currentRatio;

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }

    @Override
    public String toString() {
        return "StockFundamentalsVo{" +
                "tickerName='" + tickerName + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockFundamentalsVo that = (StockFundamentalsVo) o;
        return Objects.equals(tickerSymbol, that.tickerSymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickerSymbol);
    }
}
