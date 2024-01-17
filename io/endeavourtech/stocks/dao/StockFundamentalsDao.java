package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.StockException;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockFundamentalsDao extends BaseDao{

    public List<StockFundamentalsVo> getAllStockDetails() {
        String sqlQuery = """
                select
                    sf.ticker_symbol ,
                    sf.market_cap ,
                    sf.current_ratio ,
                    sl.ticker_name,
                    sf.sector_id,
                    sf.subsector_id
                from
                    endeavour.stock_fundamentals sf ,
                    endeavour.stocks_lookup sl
                where
                    sf.ticker_symbol = sl.ticker_symbol
                """;
        List<StockFundamentalsVo> stockFundamentalsVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StockFundamentalsVo stockFundamentalsVo = new StockFundamentalsVo();
                stockFundamentalsVo.setTickerSymbol(resultSet.getString("ticker_symbol"));
                stockFundamentalsVo.setTickerName(resultSet.getString("ticker_name"));
                stockFundamentalsVo.setMarketCap(resultSet.getLong("market_cap"));
                stockFundamentalsVo.setCurrentRatio(resultSet.getBigDecimal("current_ratio"));
                stockFundamentalsVo.setSubsectorId(resultSet.getInt("subsector_id"));
                stockFundamentalsVo.setSectorId(resultSet.getInt("sector_id"));

                stockFundamentalsVoList.add(stockFundamentalsVo);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StockException("Exception in StockFundamentalsDao getAllStockDetails",e);
        }
        return stockFundamentalsVoList;
    }
}
