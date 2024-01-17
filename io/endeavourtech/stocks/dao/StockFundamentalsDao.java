package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.vo.StockFundamentalsVo;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockFundamentalsDao extends BaseDao{
//
//    public List<StockFundamentalsVo> getStockFundamentals() throws SQLException {
//        String sqlQuery = """
//                select
//                    sf.ticker_symbol ,
//                    sf.market_cap ,
//                    sf.current_ratio ,
//                    sl.ticker_name
//                from
//                    endeavour.stock_fundamentals sf ,
//                    endeavour.stocks_lookup sl
//                where
//                    sf.ticker_symbol = sl.ticker_symbol
//                """;
//        List<StockFundamentalsVo> stockFundamentalsVoList = new ArrayList<>();
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()){
//            StockFundamentalsVo stockFundamentalsVo = new StockFundamentalsVo();
//            stockFundamentalsVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
//            stockFundamentalsVo.setTickerName(resultSet.getString("ticker_name"));
//            stockFundamentalsVo.setMarketCap(resultSet.getLong("market_cap"));
//            stockFundamentalsVo.setCurrentRatio(resultSet.getLong("current_ratio"));
//
//            stockFundamentalsVoList.add(stockFundamentalsVo);
//
//        }
//
//        return stockFundamentalsVoList;
//    }

    public List<StockFundamentalsVo> getAllStockDetails() throws SQLException {
        String sqlQuery = """
                select
                    sf.ticker_symbol ,
                    sf.market_cap ,
                    sf.current_ratio ,
                    sl.ticker_name
                from
                    endeavour.stock_fundamentals sf ,
                    endeavour.stocks_lookup sl
                where
                    sf.ticker_symbol = sl.ticker_symbol
                """;
        List<StockFundamentalsVo> stockFundamentalsVoList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            StockFundamentalsVo stockFundamentalsVo = new StockFundamentalsVo();
            stockFundamentalsVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
            stockFundamentalsVo.setTickerName(resultSet.getString("ticker_name"));
            stockFundamentalsVo.setMarketCap(resultSet.getLong("market_cap"));
            stockFundamentalsVo.setCurrentRatio(resultSet.getBigDecimal("current_ratio"));

            stockFundamentalsVoList.add(stockFundamentalsVo);

        }
        return stockFundamentalsVoList;
    }
}
