package com.example.demo.services;

import com.example.demo.domain.CommonStock;
import com.example.demo.domain.FixedDividendStock;
import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.types.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class StockDataServiceImplTest  {

    private StockDataService stockDataService;

    @BeforeEach
    public void beforeAllTests() throws InvalidSuperStockException {
        LinkedHashMap<String, Stock> stocks = new LinkedHashMap<>();
        LinkedList<TradeTransaction> tradeTransactions = new LinkedList<TradeTransaction>();
        stockDataService = new StockDataServiceImpl(stocks, tradeTransactions);
        dataPreparation();
    }
    @Test
    public void testlistStockSymbols(){
        List<Stock> results =  stockDataService.listAllStocks();
        Assertions.assertNotNull(results);
    }

    @Test
    public void testlistAllStocks(){
        List<String> results = stockDataService.listStockSymbols();
        Assertions.assertNotNull(results);

    }

    @Test
    public void testsaveStockData() throws InvalidSuperStockException{
        Stock stock = new CommonStock();
        stock.setSymbol("INR");
        stock.setLastDividend(0);
        stock.setParValue(100);
        stock.setCurrency(Currency.USD);
        stockDataService.saveStockData(stock);
        Assertions.assertNotNull(stockDataService.listAllStocks());
    }

    @Test
    public void testgetStockData() throws InvalidSuperStockException{
        Stock stock = stockDataService.getStockData("TEA");
        Assertions.assertNotNull(stock);

    }
    @Test
    public void testrecordTradeTransation() throws InvalidSuperStockException {
        TradeTransaction tradeTransaction = new TradeTransaction();
        tradeTransaction.setQuantity(150);
        tradeTransaction.setTradedPrice(10.5);
        tradeTransaction.setStockSymbol("TEA");
        tradeTransaction.setTimestamp(Calendar.getInstance().getTime());
        stockDataService.recordTradeTransation(tradeTransaction);
        Assertions.assertNotNull(stockDataService.getAllTransactionRecords());
    }

    @Test
    public void testgetTransactionRecordsByDuration() {
        Date currTime = Calendar.getInstance().getTime();
        List<TradeTransaction> results = stockDataService.getTransactionRecordsByDuration("TEA", currTime, 15);
        Assertions.assertNotNull(results);
    }

    @Test
    public void testgetAllTransactionRecords(){
        Assertions.assertNotNull(stockDataService.getAllTransactionRecords());
    }

    public void dataPreparation()  throws InvalidSuperStockException {
        Stock stock = new CommonStock();
        stock.setSymbol("TEA");
        stock.setLastDividend(0);
        stock.setParValue(100);
        stock.setCurrency(Currency.USD);
        stockDataService.saveStockData(stock);

        stock = new CommonStock();
        stock.setSymbol("POP");
        stock.setParValue(100);
        stock.setLastDividend(8);
        stock.setCurrency(Currency.USD);
        stockDataService.saveStockData(stock);

        stock = new CommonStock();
        stock.setSymbol("ALE");
        stock.setLastDividend(23);
        stock.setParValue(60);
        stock.setCurrency(Currency.USD);
        stockDataService.saveStockData(stock);

        stock = new CommonStock();
        stock.setSymbol("JOE");
        stock.setLastDividend(13);
        stock.setParValue(250);
        stock.setLastDividend(23);
        stock.setCurrency(Currency.USD);
        stockDataService.saveStockData(stock);

        FixedDividendStock stock1 = new FixedDividendStock();
        stock1.setSymbol("GIN");
        stock1.setParValue(100);
        stock1.setCurrency(Currency.USD);
        stock1.setLastDividend(8);
        stock1.setFixedDividendPercentage(2);

        stockDataService.saveStockData(stock1);
    }

}
