package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;

import java.util.Date;

public interface SuperTradeService {

    /**
     * Create a stock in market
     *
     * @param stock Stock to be created
     * @throws InvalidSuperStockException
     */
    public boolean createStockInMarket(Stock stock) throws InvalidSuperStockException;

    /**
     * Trade stocks - Buy/Sell
     *
     * @param stockSymbol Symbol of the stock
     * @param quantity Quantity of stocks to buy/sell
     * @param buySellIndicator Buy/Sell Indicator
     * @param tradedPrice price per stock
     * @param timestamp Timestamp of the transaction
     * @return
     */
    public boolean tradeStockInMarket(String stockSymbol,
                                      int quantity,
                                      BuySellIndicator buySellIndicator,
                                      double tradedPrice,
                                      Date timestamp) throws InvalidSuperStockException;

    /**
     * Get Stock Data using stock symbol
     *
     * @param stockSymbol
     * @return
     * @throws InvalidSuperStockException
     */
    public Stock getStockData(String stockSymbol) throws InvalidSuperStockException;
}
