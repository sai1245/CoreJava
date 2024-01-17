package io.endeavourtech.stocks;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.dao.StockFundamentalsDao;
import io.endeavourtech.stocks.service.MarketAnalyticsService;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockAnalytics {
    public static void main(String[] args) {
        try(LookUpDao lookUpDao = new LookUpDao();
            StockFundamentalsDao stockFundamentalsDao = new StockFundamentalsDao();) {

            MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDao,stockFundamentalsDao);
            List<SectorVo> allSectorslist = marketAnalyticsService.getAllSectorEconomy();
//            System.out.println(allSectorslist);


            List<StockFundamentalsVo> allStockDetails = marketAnalyticsService.getAllStockDetails();
//            System.out.println(allStockDetails);

            List<Integer> subSectorIds= new ArrayList<>();
            subSectorIds.add(189);
            subSectorIds.add(239);
            subSectorIds.add(269);
            List<SubSectorVo> allSubSectorsOfEconomyByID=marketAnalyticsService.getAllSubSectorsOfEconomyByID(subSectorIds);
            System.out.println(allSubSectorsOfEconomyByID);
        } catch (SQLException e) {
            e.printStackTrace();
        };

    }
}
