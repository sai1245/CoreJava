package io.endeavourtech.stocks;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.dao.StockFundamentalsDao;
import io.endeavourtech.stocks.dao.StockPriceHistoryDao;
import io.endeavourtech.stocks.service.MarketAnalyticsService;
import io.endeavourtech.stocks.sort.MarketCapComparator;
import io.endeavourtech.stocks.sort.StockFundamentalSort;
import io.endeavourtech.stocks.sort.SubSectorComparator;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;
import io.endeavourtech.stocks.vo.StockPriceHistoryVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockAnalytics {
    public static void main(String[] args) {
        try(LookUpDao lookUpDao = new LookUpDao();
            StockFundamentalsDao stockFundamentalsDao = new StockFundamentalsDao();
            StockPriceHistoryDao stockPriceHistoryDao = new StockPriceHistoryDao()) {

            MarketAnalyticsService marketAnalyticsService = new MarketAnalyticsService(lookUpDao,stockFundamentalsDao,
                    stockPriceHistoryDao);

//            List<SectorVo> allSectorslist = marketAnalyticsService.getAllSectorEconomy();
//            System.out.println(allSectorslist);

//            List<SubSectorVo> allSubSectorlist = marketAnalyticsService.getAllSubSectors();
//            System.out.println(allSubSectorlist);


//            List<StockFundamentalsVo> allStockDetails = marketAnalyticsService.getAllStockDetails();
//           System.out.println(allStockDetails);


//           List<StockPriceHistoryVo> allStocksVolumeAndClosePrices = marketAnalyticsService.getAllStockVolumesAndClosePrices();
//           System.out.println(allStocksVolumeAndClosePrices);

//            LocalDate fromDate = LocalDate.parse("2023-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//           List<StockPriceHistoryVo> stockPriceHistoryVos;
//            stockPriceHistoryVos = marketAnalyticsService.getStockVolumeAndClosePriceandDates("MSFT",
//                    fromDate,
//                    fromDate.plusMonths(3));

//            System.out.println(stockPriceHistoryVos);


//            marketAnalyticsService.processHealthCareStocks();


//            marketAnalyticsService.identifyBlueChipStoclks();

//            marketAnalyticsService.getSectorStocksCount();

//            marketAnalyticsService.calculateTotalmarketCapBySubsector();

//           marketAnalyticsService.getStockForLastYears("V");

            marketAnalyticsService.calculateTotalmarketCapBySector();

            marketAnalyticsService.getStockForLastYears1("AAPL",LocalDate.of(2019,1,1),LocalDate.of(2024,12,31));

            List<Integer> subSectorIds= new ArrayList<>();
            subSectorIds.add(189);
            subSectorIds.add(239);
            subSectorIds.add(269);
//            List<SubSectorVo> allSubSectorsOfEconomyByID=marketAnalyticsService.getAllSubSectors();
//            System.out.println(allSubSectorsOfEconomyByID);
        }
        ;

    }
}
