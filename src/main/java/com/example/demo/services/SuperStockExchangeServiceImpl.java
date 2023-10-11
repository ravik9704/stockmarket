package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
@Service
public class SuperStockExchangeServiceImpl implements SuperStockExchangeService{

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
        Date date = Calendar.getInstance().getTime();
        superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.SELL, price, date);
    }

    @Override
    public void buyStock(String stockSymbol, int quantity, double price) throws InvalidSuperStockException {
        Date date = Calendar.getInstance().getTime();
        superTradeService.tradeStockInMarket(stockSymbol, quantity, BuySellIndicator.BUY, price, date);
    }
}
