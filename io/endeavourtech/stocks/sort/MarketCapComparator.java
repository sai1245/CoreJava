package io.endeavourtech.stocks.sort;

import io.endeavourtech.stocks.vo.StockFundamentalsVo;

import java.util.Comparator;

public class MarketCapComparator implements Comparator<StockFundamentalsVo> {


    @Override
    public int compare(StockFundamentalsVo o1, StockFundamentalsVo o2) {
        return o2.getMarketCap().compareTo(o1.getMarketCap());
    }
}
