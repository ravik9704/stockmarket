package com.example.demo.domain;

public enum BuySellIndicator {
    BUY("B"), SELL("S");

    private String buySellIndicator;

    BuySellIndicator(String buySellIndicator) {
        this.setBuySellIndicator(buySellIndicator);
    }

    /**
     * @return the buySellIndicator
     */
    public String getBuySellIndicator() {
        return buySellIndicator;
    }

    /**
     * @param buySellIndicator the buySellIndicator to set
     */
    public void setBuySellIndicator(String buySellIndicator) {
        this.buySellIndicator = buySellIndicator;
    }
}
