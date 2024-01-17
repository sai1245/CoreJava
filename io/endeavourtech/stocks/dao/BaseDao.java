package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.StockException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao implements AutoCloseable{
    protected static final String JDBC_URL = "jdbc:postgresql://endeavourtech.ddns.net:31240/StocksDB";
    protected static final String USERNAME = "evr_java_app";
    protected static final String PASSWORD = "mqF59Px7FNqwK4JfYTNRqoRX9D";
    protected final Connection connection;

    public BaseDao(){
        System.out.println("Initializing database connection");
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StockException("Could not obtain database connection", e);
        }
    }

    @Override
    public void close() throws StockException {
        if(connection != null){
            System.out.println("Closing Connection");
            try{
                connection.close();
            }catch (SQLException e){
                throw new StockException("Error closing connection", e);
            }
        }
    }
}