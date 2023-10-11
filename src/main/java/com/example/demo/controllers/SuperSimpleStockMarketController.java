package com.example.demo.controllers;

import com.example.demo.Entity.CalculateDividendYieldResponse;
import com.example.demo.Entity.GeometricMeanOfPricesResponse;
import com.example.demo.Entity.PriceOverDividendRatioResponse;
import com.example.demo.Entity.VolumeWeightedStockPriceResponse;
import com.example.demo.domain.Trade;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.services.SuperSimpleStockMarketService;
import com.example.demo.services.SuperStockExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("superSimpleStockMarket")
public class SuperSimpleStockMarketController {
    Logger logger = LoggerFactory.getLogger(SuperSimpleStockMarketController.class);
    private SuperSimpleStockMarketService superSimpleStockMarketService;
    private SuperStockExchangeService superStockExchangeService;

    public SuperSimpleStockMarketController(SuperSimpleStockMarketService superSimpleStockMarketService, SuperStockExchangeService superStockExchangeService){
        this.superSimpleStockMarketService = superSimpleStockMarketService;
        this.superStockExchangeService = superStockExchangeService;
    }
    @GetMapping("/calculateDividendYield")
    public ResponseEntity<CalculateDividendYieldResponse> getCalculateDividendYield(@RequestParam String stockSymbol, @RequestParam double price) throws InvalidSuperStockException {
        double calculateDividendYield =  superSimpleStockMarketService.calculateDividendYield(stockSymbol, price);
        CalculateDividendYieldResponse calculateDividendYieldResponse = new CalculateDividendYieldResponse();
        calculateDividendYieldResponse.setCalculateDividendYield(String.valueOf(calculateDividendYield));
        logger.info("calculateDividendYield Results :"+calculateDividendYield);
        return new ResponseEntity<>(calculateDividendYieldResponse, HttpStatus.OK);
    }

    @GetMapping("/priceOverDividendRatio")
    public ResponseEntity<PriceOverDividendRatioResponse> getPriceOverDividendRatio(@RequestParam String stockSymbol, @RequestParam double price) throws InvalidSuperStockException {
        double priceOverDividendRatio =  superSimpleStockMarketService.priceOverDividendRatio(stockSymbol, price);
        return new ResponseEntity<>(new PriceOverDividendRatioResponse(String.valueOf(priceOverDividendRatio)), HttpStatus.OK);
    }

    @PostMapping("/buyStock")
    public ResponseEntity<?> buyStock(@RequestBody Trade trade) throws InvalidSuperStockException {
        superStockExchangeService.buyStock(trade.getStockSymbol(),trade.getQuantity(), trade.getPrice());
        return new ResponseEntity<>(HttpStatus.CREATED, HttpStatus.CREATED);
    }

    @PostMapping("/sellStock")
    public ResponseEntity<?> sellStock(@RequestBody Trade trade) throws InvalidSuperStockException {
        superStockExchangeService.sellStock(trade.getStockSymbol(),trade.getQuantity(), trade.getPrice());
        return new ResponseEntity<>(HttpStatus.CREATED, HttpStatus.CREATED);
    }

    @GetMapping("/volumeWeightedStockPrice")
    public ResponseEntity<VolumeWeightedStockPriceResponse> getVolumeWeightedStockPrice(@RequestParam String stockSymbol, @RequestParam int mints) throws InvalidSuperStockException {
        double results = superSimpleStockMarketService.volumeWeightedStockPriceByTime(stockSymbol, mints);
        return new ResponseEntity<>(new VolumeWeightedStockPriceResponse(String.valueOf(results)), HttpStatus.OK);
    }

    @GetMapping("/geometricMeanOfPrices")
    public ResponseEntity<GeometricMeanOfPricesResponse> geometricMeanOfPrices() throws InvalidSuperStockException {
        double results = superSimpleStockMarketService.calculateAllShareIndex();
        GeometricMeanOfPricesResponse geometricMeanOfPricesResponse = new GeometricMeanOfPricesResponse(String.valueOf(results));
        return new ResponseEntity<>(geometricMeanOfPricesResponse, HttpStatus.OK);
    }
}
