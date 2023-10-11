package com.example.demo.types;

public enum Country {
    IN ("India"), UK ("United Kingdom");

    private String countryName;
    private Country(String countryName) {
        this.setCountryName(countryName);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
