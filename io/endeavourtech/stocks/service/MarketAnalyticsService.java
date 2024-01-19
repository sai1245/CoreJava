package io.endeavourtech.stocks.service;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.dao.StockFundamentalsDao;
import io.endeavourtech.stocks.dao.StockPriceHistoryDao;
import io.endeavourtech.stocks.sort.MarketCapComparator;
import io.endeavourtech.stocks.sort.StockFundamentalSort;
import io.endeavourtech.stocks.sort.SubSectorComparator;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;
import io.endeavourtech.stocks.vo.StockPriceHistoryVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarketAnalyticsService {
    LookUpDao lookUpDao;

//Dependacy Injectoin - MarketAnalyticsService needs lookupDao to function ad hence the lookup object is created and injected into the constructor

    StockFundamentalsDao stockFundamentalsDao;

    StockPriceHistoryDao stockPriceHistoryDao;

    public MarketAnalyticsService(LookUpDao lookUpDao, StockFundamentalsDao stockFundamentalsDao, StockPriceHistoryDao stockPriceHistoryDao) {
        this.lookUpDao = lookUpDao;
        this.stockFundamentalsDao = stockFundamentalsDao;
        this.stockPriceHistoryDao = stockPriceHistoryDao;
    }

    public List<SectorVo> getAllSectorEconomy() throws SQLException {
        List<SectorVo> allSectorslist = lookUpDao.getAllSectors();
        return allSectorslist;
    }

    public List<SubSectorVo> getAllSubSectors() throws SQLException {
        List<SubSectorVo> allSubSectorslist = lookUpDao.getAllSubSectors();
        Collections.sort(allSubSectorslist);
        Collections.sort(allSubSectorslist, new SubSectorComparator());

        return allSubSectorslist;
    }

    public List<StockFundamentalsVo> getAllStockFundamentals() {
        List<StockFundamentalsVo> allStockFundamentalsList = stockFundamentalsDao.getAllStockDetails();
        Collections.sort(allStockFundamentalsList);
        allStockFundamentalsList.sort(Comparator.comparing(StockFundamentalsVo::getTickerName));
        return allStockFundamentalsList;
    }

    public List<StockFundamentalsVo> getAllStockDetails() {
        List<StockFundamentalsVo> allStockDetailslist = stockFundamentalsDao.getAllStockDetails();
        allStockDetailslist.sort(Comparator.comparing(StockFundamentalsVo::getTickerSymbol));
        Collections.sort(allStockDetailslist);
        Collections.sort(allStockDetailslist, new StockFundamentalSort());
        Collections.sort(allStockDetailslist, new MarketCapComparator());
        allStockDetailslist.sort((o1, o2) -> o2.getMarketCap().compareTo(o1.getMarketCap()));
        return allStockDetailslist;
    }

    public List<StockPriceHistoryVo> getAllStockVolumesAndClosePrices() {
        List<StockPriceHistoryVo> allStocksCloseAndVolumes = stockPriceHistoryDao.getStockVolumeAndClosePrice();
        allStocksCloseAndVolumes.sort(((o1, o2) -> o2.getVolume().compareTo(o1.getVolume())));
        allStocksCloseAndVolumes.sort(Comparator.comparing(StockPriceHistoryVo::getClosePrice));
        return allStocksCloseAndVolumes;
    }
}
