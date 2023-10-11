package com.example.demo.services;

import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;

import java.util.Date;
import java.util.List;

public interface StockDataService {

    /**
     * List All stock symbols in the market
     *
     * @return List of symbols of all stocks in the market
     */
    public List<String> listStockSymbols();


    /**
     * List All stocks in the market
     *
     * @return List of all stocks in the market
     */
    public List<Stock> listAllStocks();

    /**
     * Save Stock Data
     *
     * @param stock Stock object containing the stock details
     * @return
     */
    public boolean saveStockData(Stock stock) throws InvalidSuperStockException;

    /**
     * Update stock details
     *
     * @param stockSymbol Stock object containing the stock details
     *
     * @return Stock object containing the stock details
     */
    public Stock getStockData(String stockSymbol) throws InvalidSuperStockException;

    /**
     * Record Trade Transaction
     *
     * @param tradeTransaction TradeTransaction object encapsulating Trade done
     * @return
     */
    public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidSuperStockException;

    /**
     * Get Transaction Records with given duration
     *
     * @param stockSymbol
     * @param currentTime
     * @param minutes
     * @return
     */
    public List<TradeTransaction> getTransactionRecordsByDuration(String stockSymbol, Date currentTime, int minutes);

    /**
     * Get all Transaction Records
     *
     * @return list of all transaction records
     */
    public List<TradeTransaction> getAllTransactionRecords();
}
