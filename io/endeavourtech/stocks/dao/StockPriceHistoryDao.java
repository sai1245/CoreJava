package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.StockException;
import io.endeavourtech.stocks.vo.StockPriceHistoryVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class StockPriceHistoryDao extends BaseDao  {

    public List<StockPriceHistoryVo> getStockVolumeAndClosePrice(){
        String sqlQuery = """
                select\s
                	sph.ticker_symbol ,
                	sph.close_price ,
                	sph.volume\s
                from\s
                	endeavour.stocks_price_history sph\s
                limit 5
                """;
        List<StockPriceHistoryVo> stockPriceHistoryVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                StockPriceHistoryVo stockPriceHistoryVo = new StockPriceHistoryVo();
                stockPriceHistoryVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
                stockPriceHistoryVo.setClosePrice(resultSet.getBigDecimal("close_price"));
                stockPriceHistoryVo.setVolume(resultSet.getBigDecimal("volume"));
                stockPriceHistoryVoList.add(stockPriceHistoryVo);
            }

        } catch (SQLException e) {
            throw new StockException("Exception in StockPriceHistory getStockVolumeAndClosePrice",e);
        }
        return stockPriceHistoryVoList;
    }
}
