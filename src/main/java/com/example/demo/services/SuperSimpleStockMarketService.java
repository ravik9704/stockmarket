package com.example.demo.services;

import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;

public interface SuperSimpleStockMarketService {

    /**
     * Calculate Dividend yield given price as input 
     *
     * @param stockSymbol Symbol of the stock
     * @param price Price of the stock
     * @return Dividend yield for a given stock
     * @throws InvalidSuperStockException When no stock is associated with the stock symbol
     */
    public double calculateDividendYield(String stockSymbol, double price) throws InvalidSuperStockException;

    /**
     * Calculate P/E ratio for a given stock, given price as input
     *
     * @param stockSymbol Symbol of the stock
     * @param price Price of the stock
     * @return P/E ratio for a given stock,
     * @throws InvalidSuperStockException When no stock is associated with the stock symbol
     */
    public double priceOverDividendRatio(String stockSymbol, double price) throws InvalidSuperStockException;

    /**
     * Calculate Volume Weighted Stock Price based on trades in past given input minutes
     *
     * @param stockSymbol Symbol of the stock
     * @param minutes Time frame in which volume weighted stock price has to be calculated 
     * @return Volume Weighted Stock price of a given stock
     *
     * @throws InvalidSuperStockException  When no stock is associated with the stock symbol
     */
    public double volumeWeightedStockPriceByTime(String stockSymbol, int minutes) throws InvalidSuperStockException;

    /**
     * Validate a stock symbol to be associated with a Stock in market
     * @param stockSymbol Symbol of the stock
     * @throws InvalidSuperStockException When no stock is associated with the stock symbol
     */

    /**
     * Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
     */
    public double calculateAllShareIndex() throws InvalidSuperStockException;

    public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidSuperStockException;

}
