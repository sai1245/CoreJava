package io.endeavourtech.stocks.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class StockFundamentalsVo implements Comparable<StockFundamentalsVo>{

    private String tickerName;

    private int sectorId;

    private int subsectorId;

    private String tickerSymbol;

    private Long marketCap;
    private BigDecimal currentRatio;

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public int getSubsectorId() {
        return subsectorId;
    }

    public void setSubsectorId(int subsectorId) {
        this.subsectorId = subsectorId;
    }

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
                ", sectorId=" + sectorId +
                ", subsectorId=" + subsectorId +
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

    @Override
    public int compareTo(StockFundamentalsVo o) {
        return this.getTickerSymbol().compareTo(o.getTickerSymbol());
    }
}
