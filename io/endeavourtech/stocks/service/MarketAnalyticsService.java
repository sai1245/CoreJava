package io.endeavourtech.stocks.service;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.dao.StockFundamentalsDao;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.SQLException;
import java.util.List;

public class MarketAnalyticsService {
    LookUpDao lookUpDao;


    public MarketAnalyticsService(LookUpDao lookUpDao) {
        this.lookUpDao = lookUpDao;
    }
    StockFundamentalsDao stockFundamentalsDao;

    public MarketAnalyticsService(StockFundamentalsDao stockFundamentalsDao) {
        this.stockFundamentalsDao = stockFundamentalsDao;
    }

    public List<StockFundamentalsVo> getAllStockDetails() throws SQLException {
        return stockFundamentalsDao.getAllStockDetails();
    }

    public List<SectorVo> getAllSectorEconomy() throws SQLException {
        return lookUpDao.getAllSectors();
    }

    public List<SubSectorVo> getAllSubSectors() throws SQLException {
        return lookUpDao.getAllSubSectors();
    }


}
