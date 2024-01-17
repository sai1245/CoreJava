package io.endeavourtech.stocks;

public class StockException extends RuntimeException {
    public StockException(String message) {
        super(message);
    }
    public StockException(String message, Throwable cause) {
        super(message, cause);
    }
}
