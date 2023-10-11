package com.example.demo.services;

import com.example.demo.domain.FixedDividendStock;
import com.example.demo.domain.Stock;
import com.example.demo.domain.TradeTransaction;
import com.example.demo.exception.InvalidSuperStockException;
import com.example.demo.types.StockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SuperSimpleStockMarketServiceImpl implements SuperSimpleStockMarketService {
   Logger logger = LoggerFactory.getLogger(SuperSimpleStockMarketServiceImpl.class);
    private SuperTradeService superTradeService;
    private StockDataService stockDataService;
    public SuperSimpleStockMarketServiceImpl(SuperTradeService superTradeService, StockDataService stockDataService) {
        this.superTradeService = superTradeService;
        this.stockDataService = stockDataService;
    }

    @Override
    public double calculateDividendYield(String stockSymbol, double price) throws InvalidSuperStockException {
        StockType stockType = superTradeService.getStockData(stockSymbol).getStockType();
        Stock stock = null;
        double dividendYield = 0.0;
        try {
            if (price != 0.0) {
                if (StockType.COMMON.equals(stockType)) {
                    stock = stockDataService.getStockData(stockSymbol);
                    dividendYield = stock.getLastDividend() / price;
                } else if (StockType.PREFERRED.equals(stockType)) {
                    FixedDividendStock fixedDividendStock = (FixedDividendStock) stockDataService.getStockData(stockSymbol);
                    dividendYield = (fixedDividendStock.getFixedDividendPercentage() / 100.0) * fixedDividendStock.getParValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception coming while calculateDividendYield:"+e.getMessage());
        }
        return dividendYield;
    }
    @Override
    public double priceOverDividendRatio(String stockSymbol, double price) throws InvalidSuperStockException {
        try {
            Stock stock = stockDataService.getStockData(stockSymbol);
            double dividend = stock.getLastDividend();
            if (dividend != 0.0) {
                 double pbyE = price / dividend;
                return pbyE;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Exception coming while calculating priceOverDividendRatio : "+e.getMessage());
        }
        return 0;
    }

    @Override
    public double volumeWeightedStockPriceByTime(String stockSymbol, int minutes) throws InvalidSuperStockException {
        double volumeWeightedStockPrice = 0.0;
        Date currTime = Calendar.getInstance().getTime();
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        List<TradeTransaction> records = stockDataService.getTransactionRecordsByDuration(stockSymbol, currTime,
                minutes);
        double cumPrice = 0.0;
        int totalQty = 0;
        for (TradeTransaction trade : records) {
            double price = trade.getTradedPrice();

            int qty = trade.getQuantity();

            cumPrice += (price * qty);
            totalQty += qty;
        }
        logger.debug("Volume Weighted Stock Price   = Cumulative Price in last 15 min / Sum Of Qty ");
        logger.debug("                              = "+ cumPrice + " / " + totalQty);
        logger.debug("                              = "+ cumPrice + " / " + totalQty);
        if (totalQty != 0) {
            volumeWeightedStockPrice = cumPrice / totalQty;
            logger.debug("                              = " + volumeWeightedStockPrice + "\n");
        }
        return volumeWeightedStockPrice;
    }

    @Override
    public double calculateAllShareIndex() throws InvalidSuperStockException {
        double geometricMean = 0.0;
        List<TradeTransaction> records = stockDataService.getAllTransactionRecords();
        int size = records.size();
        double cumPrice = 0.0;
        for (TradeTransaction trade : records) {
            double price = trade.getTradedPrice();
            cumPrice += price;
        }
        geometricMean = Math.pow(cumPrice, 1.0 / size);
        logger.debug("Geometric mean   = nth root of Cumulative Price");
        logger.debug("                 = " + cumPrice + " ^  1.0 / " + size);
        logger.debug("                 = " + geometricMean + "\n");
        return geometricMean;
    }

    @Override
    public boolean recordTradeTransation(TradeTransaction tradeTransaction) throws InvalidSuperStockException {
        return stockDataService.recordTradeTransation(tradeTransaction);
    }
}
