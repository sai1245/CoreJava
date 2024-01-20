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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//        Collections.sort(allSubSectorslist);
//        Collections.sort(allSubSectorslist, new SubSectorComparator());

        Map<Integer, String>  subsectorIdandNamesMap = allSubSectorslist.stream()
                .collect(Collectors.toMap(SubSectorVo::getSubSectorId,
                        SubSectorVo::getSubSectorName));

        System.out.println(subsectorIdandNamesMap);
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
//        System.out.println(allStocksCloseAndVolumes);
        allStocksCloseAndVolumes.sort(Comparator.comparing(StockPriceHistoryVo::getClosePrice));
        return allStocksCloseAndVolumes;
    }

    public List<StockPriceHistoryVo> getStockVolumeAndClosePriceandDates(String tickerSymbol, LocalDate fromDate, LocalDate toDate) {
        List<StockPriceHistoryVo> allStocksCloseAndVolumesDates = stockPriceHistoryDao.getStockVolumeAndClosePriceandDate(tickerSymbol,fromDate,toDate);
        allStocksCloseAndVolumesDates.sort(Comparator.comparing(StockPriceHistoryVo::getClosePrice).reversed().thenComparing(
                Comparator.comparing(StockPriceHistoryVo::getTradingDate)
        ));
        return allStocksCloseAndVolumesDates;
    }

    public void processHealthCareStocks(){
        List<StockFundamentalsVo> healthCareList = new ArrayList<>();
        List<StockFundamentalsVo> allStockFundamentslsDetailsList = stockFundamentalsDao.getAllStockDetails();
        allStockFundamentslsDetailsList.forEach(stockFundamentalsVo -> {
            if (stockFundamentalsVo.getSectorId()==10){
//                System.out.println(stockFundamentalsVo);
                healthCareList.add(stockFundamentalsVo);

            }
        });
        System.out.println(healthCareList);

        List<StockFundamentalsVo> healthStream = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId()==10)
                .sorted(Comparator.comparing(StockFundamentalsVo::getCurrentRatio,
                        Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                .collect(Collectors.toList());

        System.out.println("");


        //get the count of stocks form the health care sector
        long healthCareStocksCount = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .count();
                // Teeminal functions decide the output of the stream
        System.out.println("count of heatl care stocks is : "+healthCareStocksCount);


        //get teh lsit of all ticker names form the health care sector
        List<String> healthCareTickerNamesList = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .sorted(Comparator.comparing(StockFundamentalsVo::getTickerName))       //order by ticker name ascending
                .map(stockFundamentalsVo -> stockFundamentalsVo.getTickerName())    //Converting the Stockfundamentsals vo into String
                .sorted(Comparator.comparing(String::toString).reversed())
                .collect(Collectors.toList());
        System.out.println(healthCareTickerNamesList);



        //get the total market cap of health care stocks
        Optional<Long> totalMarketCap = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce((a,b)->a+b);
//        totalMarketCap.ifPresent( totalMarketCap -> {
//            System.out.println("Total Market Cap is :" + totalMarketCap);
//        }
        System.out.println("Sum of Total MarketCap is: "+totalMarketCap);


        //for each subsector of the healthcare sector, get their stocks
        Map<Integer, List<StockFundamentalsVo>> subSectorStocksByIdMap = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSubsectorId));




    }
}
