package com.example.demo.controllers;

import com.example.demo.Entity.CalculateDividendYieldResponse;
import com.example.demo.Entity.GeometricMeanOfPricesResponse;
import com.example.demo.Entity.PriceOverDividendRatioResponse;
import com.example.demo.Entity.VolumeWeightedStockPriceResponse;
import com.example.demo.domain.Trade;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.services.SuperSimpleStockMarketService;
import com.example.demo.services.SuperStockExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperSimpleStockMarketControllerTest {

    private SuperSimpleStockMarketController superSimpleStockMarketController;
    @Mock
    private SuperSimpleStockMarketService superSimpleStockMarketService;
    @Mock
    private SuperStockExchangeService superStockExchangeService;

   /* @BeforeAll
    public void init(){
        System.out.println("BeforeAll init() method called");
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
    }*/

    @Test
    public void getCalculateDividendYield() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        double price = Double.parseDouble("123");
        when(superSimpleStockMarketService.calculateDividendYield(any(String.class), anyDouble())).thenReturn(price);
        ResponseEntity<CalculateDividendYieldResponse> responseEntity = superSimpleStockMarketController.getCalculateDividendYield("GIN", 123.54);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testgetPriceOverDividendRatio() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        double price = Double.parseDouble("123");
        when(superSimpleStockMarketService.priceOverDividendRatio(any(String.class), anyDouble())).thenReturn(price);
        ResponseEntity<PriceOverDividendRatioResponse> priceOverDividendRatioResponse= superSimpleStockMarketController.getPriceOverDividendRatio("GIN", 123.54);
        Assertions.assertNotNull(priceOverDividendRatioResponse);
        Assertions.assertEquals(priceOverDividendRatioResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testbuyStock() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        doNothing().when(superStockExchangeService).sellStock(any(String.class), anyInt(), anyDouble());
        Trade trade = new Trade();
        trade.setPrice(10.5);
        trade.setStockSymbol("POP");
        trade.setQuantity(120);
        ResponseEntity responseEntity = superSimpleStockMarketController.buyStock(trade);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testsellStock() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        doNothing().when(superStockExchangeService).sellStock(any(String.class), anyInt(), anyDouble());
        Trade trade = new Trade();
        trade.setPrice(10.5);
        trade.setStockSymbol("TFA");
        trade.setQuantity(120);
        ResponseEntity responseEntity = superSimpleStockMarketController.sellStock(trade);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testVolumeWeightedStockPriceByTime() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        double results = 123.45;
        when(superSimpleStockMarketService.volumeWeightedStockPriceByTime(any(String.class), anyInt())).thenReturn(results);
        ResponseEntity<VolumeWeightedStockPriceResponse> volumeWeightedStockPriceResponse = superSimpleStockMarketController.getVolumeWeightedStockPrice("GIN",  15);
        Assertions.assertNotNull(volumeWeightedStockPriceResponse);
        Assertions.assertEquals(volumeWeightedStockPriceResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testcalculateAllShareIndex() throws InvalidSuperStockException {
        superSimpleStockMarketController = new SuperSimpleStockMarketController(superSimpleStockMarketService, superStockExchangeService);
        double results = 123.45;
        when(superSimpleStockMarketService.calculateAllShareIndex()).thenReturn(results);
        ResponseEntity<GeometricMeanOfPricesResponse> geometricMeanOfPricesResponse = superSimpleStockMarketController.geometricMeanOfPrices();
        Assertions.assertNotNull(geometricMeanOfPricesResponse);
        Assertions.assertEquals(geometricMeanOfPricesResponse.getStatusCode(), HttpStatus.OK);
    }
}
