package com.example.demo.services;

import com.example.demo.controllers.SuperSimpleStockMarketController;
import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.exception.SuperStackErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockDataServiceImpl implements StockDataService {

    Logger logger = LoggerFactory.getLogger(StockDataServiceImpl.class);


    private LinkedHashMap<String, Stock> stocks;

    private LinkedList<TradeTransaction> tradeTransactions;

    public StockDataServiceImpl(LinkedHashMap<String, Stock> stocks, LinkedList<TradeTransaction> tradeTransactions) {
        this.stocks = stocks;
        this.tradeTransactions = tradeTransactions;
    }

    @Override
    public List<String> listStockSymbols() {
        List<String>  allStocks = stocks.keySet().stream().sorted().collect(Collectors.toList());
        return allStocks;
    }

    @Override
    public List<Stock> listAllStocks() {
        List<Stock> allStocks = new ArrayList<Stock>();
        Iterator<String> stockIterator = stocks.keySet().iterator();
        while (stockIterator.hasNext()) {
            allStocks.add(stocks.get(stockIterator.next()));
        }
        return allStocks;
    }

    @Override
    public boolean saveStockData(Stock stock) throws InvalidSuperStockException {
        if(null!=stocks) {
            stocks.put(stock.getSymbol(), stock);
        }
        return true;
    }

    @Override
    public Stock getStockData(String stockSymbol) throws InvalidSuperStockException {
        if (!stocks.containsKey(stockSymbol))
            throw new InvalidSuperStockException(SuperStackErrorCode.INVALID_STOCK_SYMBOL);
        return stocks.get(stockSymbol);
    }

    @Override
    public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidSuperStockException {
        validate(tradeTransaction);
        tradeTransactions.add(tradeTransaction);
        return true;
    }

    private void validate(TradeTransaction tradeTransaction) throws InvalidSuperStockException {
        if (!stocks.containsKey(tradeTransaction.getStockSymbol()))
            throw new InvalidSuperStockException(SuperStackErrorCode.INVALID_STOCK_SYMBOL);

        if (tradeTransaction.getQuantity() <= 0)
            throw new InvalidSuperStockException(SuperStackErrorCode.INVALID_STOCK_QTY);

        if (tradeTransaction.getTradedPrice() <= 0.0)
            throw new InvalidSuperStockException(SuperStackErrorCode.INVALID_STOCK_PRICE);

        return;

    }
    @Override
    public List<TradeTransaction> getTransactionRecordsByDuration(String stockSymbol, Date currentTime, int minutes) {
        List<TradeTransaction> transactionList = new ArrayList<TradeTransaction>();

        Date now = currentTime;

        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Iterator<TradeTransaction> tradeIterator = tradeTransactions.descendingIterator();

        Calendar prev = Calendar.getInstance();
        prev.setTime(now);

        prev.add(Calendar.MINUTE, - minutes);
        prev.add(Calendar.SECOND, -1);

        // TODO Steams
        while (tradeIterator.hasNext()) {
            TradeTransaction tradeTransaction = tradeIterator.next();

            if (tradeTransaction.getTimestamp().after(prev.getTime())
                    && stockSymbol.equals(tradeTransaction.getStockSymbol())) {
                System.out.println(tradeTransaction);
                transactionList.add(tradeTransaction);
            }
        }
        return transactionList;
    }

    @Override
    public List<TradeTransaction> getAllTransactionRecords() {
        List<TradeTransaction> transactionList = null;
        if (null != tradeTransactions) {
            transactionList = new ArrayList<TradeTransaction>(tradeTransactions);
        }
        return transactionList;
    }
}
