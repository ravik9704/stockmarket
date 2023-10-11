package com.example.demo.types;

public enum Currency {
    GBP, USD;

    /**
     *
     * @param currency
     * @return
     */
    public Currency toCurrencyEnum(String currency) {

        for (Currency currEnum :  Currency.values()) {
            if(currEnum.equals(currency)) {
                return currEnum;
            }
        }

        return null;
    }
}
