package com.example.demo.services;

import com.example.demo.domain.CommonStock;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.types.StockType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperSimpleStockMarketServiceTest {
    @Mock
    private SuperTradeService superTradeService;
    @Mock
    private StockDataService stockDataService;
    private SuperSimpleStockMarketService superSimpleStockMarketService;

    @Test
    public void testcalculateDividendYield() throws InvalidSuperStockException {
        superSimpleStockMarketService = new SuperSimpleStockMarketServiceImpl(superTradeService, stockDataService);
        String stockSymbol = "TFA";
        double price = 10.5;
        StockType st = StockType.COMMON;
        Stock stock = new CommonStock();
        when(superTradeService.getStockData(any(String.class))).thenReturn(stock);
        when(stockDataService.getStockData(any(String.class))).thenReturn(stock);
        double results = superSimpleStockMarketService.calculateDividendYield(stockSymbol, price);
        Assertions.assertNotNull(results);
    }

}
