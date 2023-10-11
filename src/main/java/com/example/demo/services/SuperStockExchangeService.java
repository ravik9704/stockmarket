package com.example.demo.services;

import com.example.demo.domain.Stock;
import com.example.demo.exception.InvalidSuperStockException;

public interface SuperStockExchangeService {

    /**
     *
     * @param stock
     * @return
     * @throws InvalidSuperStockException
     */
     void createStockInMarket(Stock stock) throws InvalidSuperStockException;

    void sellStock(String stockSymbol, int quantity, double price) throws InvalidSuperStockException;

    /**
     *
     * @param stockSymbol
     * @param quantity
     * @param price
     * @return
     * @throws InvalidSuperStockException
     */
    void buyStock(String stockSymbol, int quantity, double price) throws InvalidSuperStockException;

}
