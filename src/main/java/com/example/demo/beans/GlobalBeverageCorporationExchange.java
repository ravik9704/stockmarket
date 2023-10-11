package com.example.demo.beans;

import com.example.demo.domain.CommonStock;
import com.example.demo.domain.FixedDividendStock;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.services.SuperStockExchangeService;
import com.example.demo.types.Currency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalBeverageCorporationExchange {

  private SuperStockExchangeService superStockExchangeService;

    @Bean
    public GlobalBeverageCorporationExchange createData() throws InvalidSuperStockException {
        return new GlobalBeverageCorporationExchange(superStockExchangeService);
    }
    public GlobalBeverageCorporationExchange(SuperStockExchangeService superStockExchangeService) throws InvalidSuperStockException {
        this.superStockExchangeService = superStockExchangeService;
        dataPopulateGlobalBeverageCorporationExchange();
    }

   private void dataPopulateGlobalBeverageCorporationExchange() throws InvalidSuperStockException {
        Stock stock = new CommonStock();
        stock.setSymbol("TEA");
        stock.setLastDividend(0);
        stock.setParValue(100);
        stock.setCurrency(Currency.USD);
       superStockExchangeService.createStockInMarket(stock);

        stock = new CommonStock();
        stock.setSymbol("POP");
        stock.setParValue(100);
        stock.setLastDividend(8);
        stock.setCurrency(Currency.USD);
        superStockExchangeService.createStockInMarket(stock);

        stock = new CommonStock();
        stock.setSymbol("ALE");
        stock.setLastDividend(23);
        stock.setParValue(60);
        stock.setCurrency(Currency.USD);
        superStockExchangeService.createStockInMarket(stock);

        stock = new CommonStock();
        stock.setSymbol("JOE");
        stock.setLastDividend(13);
        stock.setParValue(250);
        stock.setLastDividend(23);
        stock.setCurrency(Currency.USD);
        superStockExchangeService.createStockInMarket(stock);

        FixedDividendStock stock1 = new FixedDividendStock();
        stock1.setSymbol("GIN");
        stock1.setParValue(100);
        stock1.setCurrency(Currency.USD);
        stock1.setLastDividend(8);
        stock1.setFixedDividendPercentage(2);
        superStockExchangeService.createStockInMarket(stock1);
    }
}
