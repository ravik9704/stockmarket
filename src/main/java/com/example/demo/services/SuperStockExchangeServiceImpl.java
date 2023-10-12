package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.exception.SuperStackErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@Service
public class SuperStockExchangeServiceImpl implements SuperStockExchangeService{
    Logger logger = LoggerFactory.getLogger(SuperStockExchangeServiceImpl.class);
    private SuperTradeService superTradeService;

    public SuperStockExchangeServiceImpl(SuperTradeService superTradeService){
        this.superTradeService = superTradeService;
    }

    @Override
    public void createStockInMarket(Stock stock) throws InvalidSuperStockException {
        superTradeService.createStockInMarket(stock);
    }

    @Override
    public void sellStock(String stockSymbol, int quantity, double price) throws InvalidSuperStockException {
        logger.debug(" Selling stock quantity :"+quantity);
        logger.debug(" Selling stock price :"+price);
        try {
            Date date = Calendar.getInstance().getTime();
            superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.SELL, price, date);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Exception coming while selling stock :"+e.getMessage());
            throw new InvalidSuperStockException(SuperStackErrorCode.INVALID_STOCK_PRICE);
        }
    }

    @Override
    public void buyStock(String stockSymbol, int quantity, double price) throws InvalidSuperStockException {
        Date date = Calendar.getInstance().getTime();
        superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.BUY, price, date);
    }
}
