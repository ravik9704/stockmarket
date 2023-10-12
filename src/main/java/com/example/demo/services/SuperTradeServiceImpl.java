package com.example.demo.services;

import com.example.demo.domain.BuySellIndicator;
import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class SuperTradeServiceImpl implements SuperTradeService {
 Logger logger = LoggerFactory.getLogger(SuperTradeServiceImpl.class);
    private StockDataService stockDS;
    public SuperTradeServiceImpl(StockDataService stockDS) {
        super();
        this.stockDS = stockDS;
    }

    /* (non-Javadoc)
     * @see com.app.stockmarket.service.ITradeService#createStockInMarket(com.app.stockmarket.domain.Stock)
     */
    @Override
    public boolean createStockInMarket(Stock stock) throws InvalidSuperStockException {
        stockDS.saveStockData(stock);
        return true;
    }

    /* (non-Javadoc)
     * @see com.app.stockmarket.service.ITradeService#tradeStockInMarket(java.lang.String, int, com.app.stockmarket.service.ITradeService.BuySellIndicator, double, java.util.Date)
     */
    @Override
    public boolean tradeStockInMarket(String stockSymbol, int quantity, BuySellIndicator buySellIndicator,
                                      double tradedPrice, Date timestamp) throws InvalidSuperStockException {
        TradeTransaction tradeTransaction = new TradeTransaction();
        tradeTransaction.setStockSymbol(stockSymbol);
        tradeTransaction.setBuySellIndicator(buySellIndicator);
        tradeTransaction.setQuantity(quantity);
        tradeTransaction.setTimestamp(timestamp);
        tradeTransaction.setTradedPrice(tradedPrice);
        stockDS.recordTradeTransation(tradeTransaction);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        if (buySellIndicator == BuySellIndicator.BUY) {
            logger.debug("Transaction completed for BUY " + stockSymbol + " Stock for $ " +  tradedPrice + " at  "+ dt1.format(timestamp));
        } else {
            logger.debug("Transaction completed for SELL " + stockSymbol + " Stock for $ " +  tradedPrice + " at "+ dt1.format(timestamp));
        }
        return true;
    }

    @Override
    public Stock getStockData(String stockSymbol) throws InvalidSuperStockException {
        return stockDS.getStockData(stockSymbol);
    }
}
