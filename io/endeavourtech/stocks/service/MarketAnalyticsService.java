package io.endeavourtech.stocks.service;

import io.endeavourtech.stocks.dao.LookUpDao;
import io.endeavourtech.stocks.dao.StockFundamentalsDao;
import io.endeavourtech.stocks.dao.StockPriceHistoryDao;
import io.endeavourtech.stocks.sort.MarketCapComparator;
import io.endeavourtech.stocks.sort.StockFundamentalSort;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.StockFundamentalsVo;
import io.endeavourtech.stocks.vo.StockPriceHistoryVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class MarketAnalyticsService {
    LookUpDao lookUpDao;

//Dependacy Injectoin - MarketAnalyticsService needs lookupDao to function ad
// hence the lookup object is created and injected into the constructor

    StockFundamentalsDao stockFundamentalsDao;

    StockPriceHistoryDao stockPriceHistoryDao;


    public MarketAnalyticsService(LookUpDao lookUpDao,
                                  StockFundamentalsDao stockFundamentalsDao,
                                  StockPriceHistoryDao stockPriceHistoryDao) {
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

    public List<StockPriceHistoryVo> getStockVolumeAndClosePriceandDates(String tickerSymbol,
                                                                         LocalDate fromDate,
                                                                         LocalDate toDate) {
        List<StockPriceHistoryVo> allStocksCloseAndVolumesDates = stockPriceHistoryDao.getStockVolumeAndClosePriceandDate(tickerSymbol,fromDate,toDate);
        allStocksCloseAndVolumesDates.sort(Comparator.comparing(StockPriceHistoryVo::getClosePrice).reversed().thenComparing(
                Comparator.comparing(StockPriceHistoryVo::getTradingDate)
        ));
        return allStocksCloseAndVolumesDates;
    }

    public void processHealthCareStocks(){
        List<StockFundamentalsVo> healthCareList = new ArrayList<>();
        List<StockFundamentalsVo> allStockFundamentslsDetailsList = stockFundamentalsDao.getAllStockDetails();

        Long x=100000000000L;
        Optional<Long> highMarketCapStocks = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap() > x)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce(Long::sum);

//        System.out.println(highMarketCapStocks);


        Long i = 10000000000L;
        Long j= 50000000L;
        Optional<Long> lowMarketCapStocks = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap() > j
                        && stockFundamentalsVo.getMarketCap()<i)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce(Long::sum);

//                .collect(Collectors.toList());


//        highestMarketCapsStocksAll
//        System.out.println(lowMarketCapStocks);

//        "Print sector names and number of stocks in each sector:
//        Healthcare: 510
//        Basic Materials: 108
//        Financial Services: 486
//        Industrials: 391
//        Technology: 368
//
//        Sector lookup data is available from the lookupsDao
//        Stock fundamentals data is available from the stockFundamentsDao"



//        allStockFundamentslsDetailsList.stream()
//                .collect(Collectors.groupingBy(StockFundamentalsVo::getSectorId), Collectors.counting());
//
//
//
//        System.out.println("The grouped list is : ");
//        System.out.println(sectorGroupedList);
//
//        List<SectorVo> allSectorslist = lookUpDao.getAllSectors();







        allStockFundamentslsDetailsList.forEach(stockFundamentalsVo -> {
            if (stockFundamentalsVo.getSectorId()==10){
//                System.out.println(stockFundamentalsVo);
                healthCareList.add(stockFundamentalsVo);

            }
        });
//        System.out.println(healthCareList);

        List<StockFundamentalsVo> healthStream = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId()==10)
                .sorted(Comparator.comparing(StockFundamentalsVo::getCurrentRatio,
                        Comparator.nullsFirst(BigDecimal::compareTo)).reversed())
                .collect(Collectors.toList());

//        System.out.println("");


        //get the count of stocks form the health care sector
        long healthCareStocksCount = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .count();
                // Teeminal functions decide the output of the stream
//        System.out.println("count of heatl care stocks is : "+healthCareStocksCount);


        //get teh lsit of all ticker names form the health care sector
        List<String> healthCareTickerNamesList = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .sorted(Comparator.comparing(StockFundamentalsVo::getTickerName))       //order by ticker name ascending
                .map(stockFundamentalsVo -> stockFundamentalsVo.getTickerName())
                //Converting the Stockfundamentsals vo into String
                .sorted(Comparator.comparing(String::toString).reversed())
                .collect(Collectors.toList());
//        System.out.println(healthCareTickerNamesList);



        //get the total market cap of health care stocks
        Optional<Long> totalMarketCap = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce((a,b)->a+b);
//        totalMarketCap.ifPresent( totalMarketCap -> {
//            System.out.println("Total Market Cap is :" + totalMarketCap);
//        }
//        System.out.println("Sum of Total MarketCap is: "+totalMarketCap);


        //for each subsector of the healthcare sector, get their stocks
        Map<Integer, List<StockFundamentalsVo>> subSectorStocksByIdMap = allStockFundamentslsDetailsList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 10)
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSubsectorId));

    }


    /**
     * method to find the total market cap of blue chip stocks and small cap stocks
     */

    public void identifyBlueChipStoclks(){
        List<StockFundamentalsVo> allStockFundamentalList = stockFundamentalsDao.getAllStockDetails();


        Long x=10000000000L;
        Optional<Long> highMarketCapStocks = allStockFundamentalList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap() > x)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce(Long::sum);

        System.out.println("Total Market cap of all blue chip stocks is :"+highMarketCapStocks);

        Long i = 2000000000L;
        Long j = 250000000L;
        Optional<Long> lowMarketCapStocks = allStockFundamentalList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap() > j
                        && stockFundamentalsVo.getMarketCap()<i)
                .map(StockFundamentalsVo::getMarketCap)
                .reduce(Long::sum);

        System.out.println("Total Market Cap of small stocks is :"+lowMarketCapStocks);

    }

    /**
     * "Print sector names and number of stocks in each sector:
     * Healthcare: 510
     * Basic Materials: 108
     * Financial Services: 486
     * Industrials: 391
     * Technology: 368
     * ...
     * Sector lookup data is available from the lookupsDao
     * Stock fundamentals data is available from the stockFundamentsDao"
     */
    public void getSectorStocksCount(){

        Map<String, Integer> finalOutputMap= new HashMap<>();
        List<StockFundamentalsVo> allStockFundamentalList = stockFundamentalsDao.getAllStockDetails();
        List<SectorVo> allSectorsList = lookUpDao.getAllSectors();


        //generating a map with sector id as key and sector name as value
        Map<Integer, String> sectorNameByIdmap = allSectorsList.stream()
                .collect(Collectors.toMap(SectorVo::getSectorID, SectorVo::getSectorName));



        //Group by teh sector id to generate a map with sector id and number of stocks in that sector
        Map<Integer, List<StockFundamentalsVo>> sectorStocksbyIdMap = allStockFundamentalList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSectorId));

//        //generate a map with sector id as key and count as value
//        Map<Integer,Integer> sectorCountsByIdMap=new HashMap<>();
//        sectorStocksbyIdMap.forEach((sectorId, stocksList)->{
//            sectorCountsByIdMap.put(sectorId,stocksList.size());
//        });
//
//        sectorCountsByIdMap.forEach((sectorId,stockCount)->{
//            finalOutputMap.put(sectorNameByIdmap.get(sectorId),stockCount);
//        });


        sectorStocksbyIdMap.forEach((sectoId,stockList)->{
            finalOutputMap.put(sectorNameByIdmap.get(sectoId),stockList.size());
        });
finalOutputMap.forEach((sectoName,stockCount)-> System.out.println(sectoName+" : "+stockCount));

    }

    public  void  anotherGetSectorStocksCount(){
        Map<String,Integer> finalOutputMap = new HashMap<>();
        List<StockFundamentalsVo> allStockFundamentalsList = stockFundamentalsDao.getAllStockDetails();
        List<SectorVo> allSectorsList = lookUpDao.getAllSectors();


        Map<Integer, String>  sectorNameByIdmap = allSectorsList.stream()
                .collect(Collectors.toMap(SectorVo::getSectorID,SectorVo::getSectorName));

        allStockFundamentalsList.forEach(stockFundamentalsVo -> {
            int count = finalOutputMap.computeIfAbsent(sectorNameByIdmap.get(stockFundamentalsVo.getSectorId()), key -> 0) + 1;
            finalOutputMap.put(sectorNameByIdmap.get(stockFundamentalsVo.getSectorId()),count);
        });

        finalOutputMap.forEach((sectorName,stockCount)-> System.out.println(sectorName+" : "+stockCount));


    }

    /**
     * Calculate the total market cap for each subsector of the economy
     * Example Output:
     * Airport Services : 1500000000
     * Healthcare Plans : 834443045
     */

    public void calculateTotalmarketCapBySubsector(){

        Map<String, Long> finalOutputMap = new HashMap<>();


        List<StockFundamentalsVo> allStockFundamentalsList = stockFundamentalsDao.getAllStockDetails();
        List<SubSectorVo> allSubSectorsList = lookUpDao.getAllSubSectors();

        Map<Integer, String> subSectorIdByName = allSubSectorsList.stream().collect(
                Collectors.toMap(SubSectorVo::getSubSectorId, SubSectorVo::getSubSectorName));

        Map<Integer, List<StockFundamentalsVo>> stockListBySubsectorIdMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSubsectorId));

        //Iterate the map and open a stream on the list to calculate total market cap for each subsector
        stockListBySubsectorIdMap.forEach((subSectorId, stocksList)->{
            Optional<Long> totalMarketCapOptional = stocksList.stream()
                    .map(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap()).reduce(Long::sum);

            totalMarketCapOptional.ifPresent(totalMarketCap->{
                finalOutputMap.put(subSectorIdByName.get(subSectorId),totalMarketCap);
            });
        });
        System.out.println(finalOutputMap);




    }

}
