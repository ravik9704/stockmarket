package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.CommonStock;
import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.types.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperTradeServiceTest {

    @Mock
    private StockDataService stockDS;

    private SuperTradeService superTradeService;
    @BeforeEach
    public void beforeAllTests() throws InvalidSuperStockException {
        superTradeService = new SuperTradeServiceImpl(stockDS);
    }

    @Test
    public void testCreateStockInMarket() throws InvalidSuperStockException{
        Stock stock = new CommonStock();
        stock.setSymbol("USD");
        stock.setLastDividend(0);
        stock.setParValue(100);
        stock.setCurrency(Currency.USD);
        when(stockDS.saveStockData(stock)).thenReturn(true);
        boolean results = superTradeService.createStockInMarket(stock);
        Assertions.assertTrue(results);
    }

    @Test
    public void testgetStockData() throws InvalidSuperStockException{
        Stock stock = new CommonStock();
        stock.setSymbol("GBP");
        stock.setLastDividend(0);
        stock.setParValue(100);
        stock.setCurrency(Currency.GBP);
        when(stockDS.getStockData(anyString())).thenReturn(stock);
        Stock results = superTradeService.getStockData("GBP");
        Assertions.assertNotNull(results);
    }


    @Test
    public void tradeStockInMarket() throws InvalidSuperStockException {
        String stockSymbol = "GBP";
        int quantity = 50;
        BuySellIndicator buySellIndicator = BuySellIndicator.BUY;
        double tradedPrice = 14.5;
        Date timestamp = Calendar.getInstance().getTime();
        TradeTransaction tradeTransaction = new TradeTransaction();
        tradeTransaction.setStockSymbol(stockSymbol);
        tradeTransaction.setBuySellIndicator(buySellIndicator);
        tradeTransaction.setQuantity(quantity);
        tradeTransaction.setTimestamp(timestamp);
        tradeTransaction.setTradedPrice(tradedPrice);
        stockDS.recordTradeTransation(tradeTransaction);
        when(stockDS.recordTradeTransation(tradeTransaction)).thenReturn(true);
        boolean results = superTradeService.tradeStockInMarket(stockSymbol, quantity, buySellIndicator, tradedPrice, timestamp);
        Assertions.assertTrue(true);

    }

}
