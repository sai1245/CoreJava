package io.endeavourtech.stocks.sort;

import io.endeavourtech.stocks.vo.StockFundamentalsVo;

import java.util.Comparator;

public class StockFundamentalSort implements Comparator<StockFundamentalsVo> {


    @Override
    public int compare(StockFundamentalsVo o1, StockFundamentalsVo o2) {
        return o1.getTickerName().compareTo(o2.getTickerName());
    }
}
