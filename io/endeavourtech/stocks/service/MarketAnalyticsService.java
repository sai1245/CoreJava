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
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class MarketAnalyticsService {
    LookUpDao lookUpDao = new LookUpDao();

//Dependacy Injectoin - MarketAnalyticsService needs lookupDao to function ad
// hence the lookup object is created and injected into the constructor

    StockFundamentalsDao stockFundamentalsDao = new StockFundamentalsDao();

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
        List<StockPriceHistoryVo> allStocksCloseAndVolumesDates = stockPriceHistoryDao.
                getStockVolumeAndClosePriceandDate(tickerSymbol,fromDate,toDate);
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

    public void getStockForLastYears(String tickerName){
        Map<Integer,BigDecimal> finalOutputMap = new HashMap<>();
        List<StockPriceHistoryVo> allPriceHistoryDetails= stockPriceHistoryDao.getStockClosePriceAndDate();

        Map<Integer, List<StockPriceHistoryVo>> closePriceWithDate = allPriceHistoryDetails.stream()
                .filter(stockPriceHistoryVo -> stockPriceHistoryVo.getTickerSymbol().equals(tickerName))
                .filter(stockPriceHistoryVo -> stockPriceHistoryVo.getTradingDate().getYear()< Year.now().getValue()-5)
                .collect(Collectors.groupingBy(year -> year.getTradingDate().getYear()));

        closePriceWithDate.forEach(((year,stocksList)->{
            stocksList.stream()
                    .map(stockPriceHistoryVo -> stockPriceHistoryVo.getClosePrice())
                    .min(BigDecimal::compareTo)
                    .ifPresent(minPrice->finalOutputMap.put(year,minPrice));
                }));

        System.out.println(finalOutputMap);


    }


    public void getStockForLastYears1(String tickerSymbol, LocalDate fromDate, LocalDate toDate){
        Map<Integer,BigDecimal> finalOutputMap = new HashMap<>();
        List<StockPriceHistoryVo> allPriceHistoryDetails= stockPriceHistoryDao.
                getStockVolumeAndClosePriceandDate(tickerSymbol,fromDate,toDate);

        Map<Integer, List<StockPriceHistoryVo>> closePriceWithDate = allPriceHistoryDetails.stream()
                .filter(stockPriceHistoryVo -> stockPriceHistoryVo.getTickerSymbol().equals(tickerSymbol))
                .collect(Collectors.groupingBy(year -> year.getTradingDate().getYear()));

        closePriceWithDate.forEach(((year,stocksList)->{
            stocksList.stream()
                    .map(stockPriceHistoryVo -> stockPriceHistoryVo.getClosePrice())
                    .min(BigDecimal::compareTo)
//     .min(Comparator.comparing(StockPriceHistoryVo::getClosePrice))   //we can use this function also insted of the above two lines,
                    .ifPresent(minPrice->finalOutputMap.put(year,minPrice));
        }));

        System.out.println(finalOutputMap);


    }

    public void calculateTotalmarketCapBySector() {
        List<SectorVo> allSectorlist = lookUpDao.getAllSectors();
        List<StockFundamentalsVo> allStockFundamentalsList = stockFundamentalsDao.getAllStockDetails();

        Map<String, Double> finalOutputMap1 = new HashMap<>();

        Map<Integer, String> sectorIdByName = allSectorlist.stream().collect(
                Collectors.toMap(SectorVo::getSectorID, SectorVo::getSectorName));

        Map<Integer, List<StockFundamentalsVo>> stockListBySectorIdMap = allStockFundamentalsList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSectorId));

        stockListBySectorIdMap.forEach((sectorId, stocksList) -> {
            OptionalDouble totalMarketCapForSectorOptional = stocksList.stream()
                    .mapToDouble(stockFundamentalsVo -> (stockFundamentalsVo.getMarketCap())/1000000000).average();

            totalMarketCapForSectorOptional.ifPresent(totalMarketCap -> {
                finalOutputMap1.put(sectorIdByName.get(sectorId),totalMarketCap);
            });
        });
        System.out.println(finalOutputMap1);
    }

/**
 * / Count of stocks with a null current ratio
 *         // map() function - get a list of all tickers (This map() is different from the data structure Map)
 *         // Using Collectors.joining
 *         // sorted() function - by current ratio and then by market cap
 *         // findFirst() - returns an optional
 *         // limit() - Top 5 by current ratio
 *         // toMap() - one-to-one mapping. Sector id, name
 *         // toMap with duplicates
 *         // groupingBy() - one-to-many mapping. SQL GROUP-BY's analogue. Group by sector id
 *         // reduce() - Total market cap of all Healthcare stocks
 */

    public void streamRecap(){
        List<StockFundamentalsVo> allStockFundamentalList = stockFundamentalsDao.getAllStockDetails();
        List<SectorVo> allSectorsList = lookUpDao.getAllSectors();


        /**
         *  Count of stocks with a null current ratio
         */
        long countOfNullCurrentRatios = allStockFundamentalList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getCurrentRatio() == null)
                .count();

        System.out.println("Stocks With Null Current Ratio is : "+countOfNullCurrentRatios);

        /**
         * map() function - get a list of all tickers (This map() is different from the data structure Map)
         */

        List<String> tickerNamesList = allStockFundamentalList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 14)
                .map(stockFundamentalsVo -> stockFundamentalsVo.getTickerName())
                .collect(Collectors.toList());

        int countOfTickerNamesInTheList = tickerNamesList.size();
        System.out.println("The List of Ticker names is : "+tickerNamesList+
                " and the count of these ticker names is : "+countOfTickerNamesInTheList);


        /**
         * Using Collectors.joining
         */


        String techTickerSymbolUisngJoining = allStockFundamentalList.stream()
                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 14)
                .map(stockFundamentalsVo -> stockFundamentalsVo.getTickerSymbol())
                .collect(Collectors.joining("|"));

        System.out.println(techTickerSymbolUisngJoining);

        /**
         * sorted() function - by current ratio and then by market cap
         */

        List<StockFundamentalsVo> currenRatioAndMarketCapUsingSorted = allStockFundamentalList.stream()
                .sorted(Comparator.comparing(StockFundamentalsVo::getMarketCap, Comparator.nullsLast(Long::compareTo))
                        .thenComparing(Comparator.comparing(StockFundamentalsVo::getMarketCap).reversed()))
                .collect(Collectors.toList());

        System.out.println(currenRatioAndMarketCapUsingSorted);

/**
 * findFirst() - returns an optional
 */
        Optional<StockFundamentalsVo> highMarektCapSfOptional = allStockFundamentalList.stream()
                .sorted(Comparator.comparing(StockFundamentalsVo::getMarketCap).reversed())
                .findFirst();
        highMarektCapSfOptional.ifPresent(sf->System.out.println(sf));


        /**
         * limit() - Top 5 by current ratio
         */

        List<StockFundamentalsVo> top5List = allStockFundamentalList.stream()
                .sorted(Comparator.comparing(StockFundamentalsVo::getMarketCap).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(top5List);


        /**
         * toMap() - one-to-one mapping. Sector id, name
         */
        Map<Integer, String> sectorNameByIdMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVo::getSectorID,
                        SectorVo::getSectorName
                ));
        System.out.println(sectorNameByIdMap);


        /**
         * toMap with duplicates
         */

        SectorVo whateverSector = new SectorVo();
        whateverSector.setSectorName("Whatever");
        whateverSector.setSectorID(17);


        allSectorsList.add(whateverSector);

        SectorVo mafiaSector = new SectorVo();
        mafiaSector.setSectorID(17);
        mafiaSector.setSectorName("Mafia Sector");

        allSectorsList.add(mafiaSector);

        Map<Integer, String> multipleSectorNameByIdMap = allSectorsList.stream()
                .collect(Collectors.toMap(
                        SectorVo::getSectorID,
                        SectorVo::getSectorName,
                        (a, b) -> b
                ));

        System.out.println(multipleSectorNameByIdMap);


        /**
         * groupingBy() - one-to-many mapping. SQL GROUP-BY's analogue. Group by sector id
         */

        Map<Integer, List<StockFundamentalsVo>> groupBySectorId = allStockFundamentalList.stream()
                .collect(Collectors.groupingBy(StockFundamentalsVo::getSectorId));

        System.out.println(groupBySectorId);

        /**
         * reduce() - Total market cap of all Healthcare stocks
         */
        Optional<Long> totalMarketCapOfTechnollogySector = allStockFundamentalList.stream()
//                .filter(stockFundamentalsVo -> stockFundamentalsVo.getSectorId() == 14)
                .map(stockFundamentalsVo -> stockFundamentalsVo.getMarketCap())
//                .reduce((a,b)->a+b);
                .reduce(Long::sum);

        System.out.println(totalMarketCapOfTechnollogySector);

    }


    /**
     * Identify Subsector with Highest Average ROE:
     * Implement a method that finds and returns the subsector name with the highest average Return on Equity (ROE) among its stocks.
     */

}
