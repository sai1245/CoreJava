package io.endeavourtech.stocks;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.service.MarketAnalyticsService;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.SQLException;
import java.util.List;

public class StockAnalytics {
    public static void main(String[] args) throws SQLException {
        LookUpDao lookUpDao = new LookUpDao();
        MarketAnalyticsService marketAnalyticsService= new MarketAnalyticsService(lookUpDao);
        List<SectorVo> allSectorslist = marketAnalyticsService.getAllSectorEconomy();
        System.out.println(allSectorslist);

        List<SubSectorVo> allSubSectors = marketAnalyticsService.getAllSubSectors();
        System.out.println(allSubSectors);
    }
}
