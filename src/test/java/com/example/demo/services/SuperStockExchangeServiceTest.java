package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.CommonStock;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperStockExchangeServiceTest {

    @Mock
    private SuperTradeService superTradeService;
    private SuperStockExchangeService superStockExchangeService;

    @BeforeEach
    public void beforeAllTests() throws InvalidSuperStockException {
        superStockExchangeService = new SuperStockExchangeServiceImpl(superTradeService);
    }

    @Test
    public void testcreateStockInMarket() throws InvalidSuperStockException{
        Stock stock = new CommonStock();
        superTradeService.createStockInMarket(stock);
        when(superTradeService.createStockInMarket(stock)).thenReturn(true);
        superStockExchangeService.createStockInMarket(stock);
    }

    @Test
   public void sellStock() throws InvalidSuperStockException{
        String stockSymbol= "GBP";
        int quantity = 55;
        double price = 13.5;
        Date date = Calendar.getInstance().getTime();
        when(superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.SELL, price, date)).thenReturn(true);
        superStockExchangeService.sellStock(stockSymbol, quantity,price);
   }

    @Test
    public void buyStock() throws InvalidSuperStockException{
        String stockSymbol= "US";
        int quantity = 15;
        double price = 33.5;
        Date date = Calendar.getInstance().getTime();
        when(superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.BUY, price, date)).thenReturn(true);
        superStockExchangeService.sellStock(stockSymbol, quantity,price);
    }
}
