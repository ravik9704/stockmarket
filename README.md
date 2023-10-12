# stockmarket
**Requerment :**
SuperSimpleStockMarket Project
Overview
This Project contains source code and classes for Super Simple Stock Market which does the below:

a. For a given stock, i. Given any price as input, calculate the dividend yield ii. Given any price as input, calculate the P/E Ratio iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and traded price iv. Calculate Volume Weighted Stock Price based on trades in past 15 minutes

b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

For this requerment I have created few RESTful services by usign JAVA/SpringBoot/gradle.
1. superSimpleStockMarket/calculateDividendYield : this endpoint resposible to calculate the dividend yield for given stock/price
2. superSimpleStockMarket/priceOverDividendRatio : this endpoint resposible to calculate the P/E Ratio for given stock/price
3. superSimpleStockMarket/buyStock : Buy the trade
4. superSimpleStockMarket/sellStock : Sell the trade
5. superSimpleStockMarket/volumeWeightedStockPrice :Calculate Volume Weighted Stock Price based on trades in past 15 minutes
6. superSimpleStockMarket/geometricMeanOfPrices : Calculate the GBCE All Share Index using the geometric mean of prices for all stocks


