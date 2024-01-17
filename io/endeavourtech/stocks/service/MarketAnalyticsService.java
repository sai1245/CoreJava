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

//Dependacy Injectoin - MarketAnalyticsService needs lookupDao to function ad hence the lookup object is created and injected into the constructor

    StockFundamentalsDao stockFundamentalsDao;

    public MarketAnalyticsService(LookUpDao lookUpDao, StockFundamentalsDao stockFundamentalsDao) {
        this.lookUpDao = lookUpDao;
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


    public List<SubSectorVo> getAllSubSectorsOfEconomyByID(List<Integer> subSectorIds) throws SQLException {
        return lookUpDao.getAllSubSectorID( subSectorIds);
    }


}
