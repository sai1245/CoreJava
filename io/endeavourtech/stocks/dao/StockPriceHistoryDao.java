package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.StockException;
import io.endeavourtech.stocks.vo.StockPriceHistoryVo;

import java.sql.*;
import java.time.LocalDate;
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
//
//    Inputs for the below method call
//    String tickerSymbol, LocalDate fromDate, LocalDate toDate
    public List<StockPriceHistoryVo> getStockVolumeAndClosePriceandDate(String tickerSymbol, LocalDate fromDate, LocalDate toDate){
        String sqlQuery = """
                select\s
                	sph.ticker_symbol ,
                	sph.close_price ,
                	sph.volume,
                	sph.trading_date\s
                from\s
                	endeavour.stocks_price_history sph\s
                where\s
                	sph.ticker_symbol = ?
                	and sph.trading_date between ? and ?
                """;
        List<StockPriceHistoryVo> stockPriceHistoryVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            //setting input values into the prepared statement
            preparedStatement.setString(1,tickerSymbol);
            preparedStatement.setDate(2, Date.valueOf(fromDate));
            preparedStatement.setDate(3,Date.valueOf(toDate));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StockPriceHistoryVo stockPriceHistoryVo = new StockPriceHistoryVo();
                stockPriceHistoryVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
                stockPriceHistoryVo.setTradingDate(resultSet.getDate("trading_date").toLocalDate());
                stockPriceHistoryVo.setClosePrice(resultSet.getBigDecimal("close_price"));
                stockPriceHistoryVo.setVolume(resultSet.getBigDecimal("volume"));

                stockPriceHistoryVoList.add(stockPriceHistoryVo);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stockPriceHistoryVoList;

    }

    public List<StockPriceHistoryVo> getStockClosePriceAndDate(){
        String sqlQuery = """
                select\s
                	sph.ticker_symbol ,
                	sph.close_price ,
                	sph.volume,
                	sph.trading_date\s
                from\s
                	endeavour.stocks_price_history sph
                	limit 5000000
                """;
        List<StockPriceHistoryVo> stockPriceHistoryVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StockPriceHistoryVo stockPriceHistoryVo = new StockPriceHistoryVo();
                stockPriceHistoryVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
                stockPriceHistoryVo.setTradingDate(resultSet.getDate("trading_date").toLocalDate());
                stockPriceHistoryVo.setClosePrice(resultSet.getBigDecimal("close_price"));
                stockPriceHistoryVo.setVolume(resultSet.getBigDecimal("volume"));

                stockPriceHistoryVoList.add(stockPriceHistoryVo);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stockPriceHistoryVoList;

    }
}
