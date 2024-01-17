import org.postgresql.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {
    private static final String jdbcURL = "jdbc:postgresql://endeavourtech.ddns.net:31240/StocksDB";

    private static final String userName = "evr_java_app";

    private static final String password = "mqF59Px7FNqwK4JfYTNRqoRX9D";

    public static void main(String[] args) throws SQLException {
//        DriverManager.registerDriver(new OracleDriver);  //need for oracle
        Connection connection = DriverManager.getConnection(jdbcURL, userName, password);

        getAllSectors(connection);
        getSectorID(connection, 21);

        List<String> tickerSymbolsList = new ArrayList<>();
        tickerSymbolsList.add("APPL");
        tickerSymbolsList.add("GOOGL");
        tickerSymbolsList.add("NVDA");
        tickerSymbolsList.add("MSFT");
        tickerSymbolsList.add("AMD");

//        System.out.println(tickerSymbolsList);
        getMultipleStockFundamentals(connection, tickerSymbolsList);

    }

    private static void getMultipleStockFundamentals(Connection connection, List<String> tickerSymbolsList) throws SQLException {
        String sqlQuery = """
                  select
                  	sf.ticker_symbol ,
                  	sf.market_cap ,
                  	sf.current_ratio
                  	from
                  	endeavour.stock_fundamentals sf
                where
                	sf.ticker_symbol in (?,?,?,?,?);
                                
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        for (int i=0;i<tickerSymbolsList.size();i++) {
            preparedStatement.setString(i+1, tickerSymbolsList.get(i));
        }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tickerSymbol = resultSet.getString("ticker_symbol");
                Long marketCap = resultSet.getLong("market_cap");
                BigDecimal currentRatio = resultSet.getBigDecimal("current_ratio");
                System.out.println("Ticker Symbol : " + tickerSymbol + ", sector Name : " + marketCap + ", Current Ratio : " + currentRatio);
            }

    }

    private static void getSectorID(Connection connection, int sectorID) throws SQLException {
        String sqlQuery = """
                select\s
                  	*
                  	from\s
                  	endeavour.sector_lookup sl
                where\s
                    sl.sector_id =?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, sectorID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int sectorId = resultSet.getInt("sector_id");
            String sectorName = resultSet.getString("sector_Name");
            System.out.println("sector ID : " + sectorID + ", sector Name : " + sectorName);
        }

    }

    private static void getAllSectors(Connection connection) throws SQLException {
        String sqlQuery = """
                  select\s
                  	*
                  from\s
                  	endeavour.sector_lookup sl
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int sectorID = resultSet.getInt("sector_id");
            String sectorName = resultSet.getString("sector_name");
            System.out.println("SectorID : " + sectorID + ", Sector_Name :" + sectorName);
        }


    }
}
