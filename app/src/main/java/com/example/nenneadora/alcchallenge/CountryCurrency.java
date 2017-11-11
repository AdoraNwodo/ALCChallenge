package com.example.nenneadora.alcchallenge;

/**
 * Created by NenneAdora on 10/31/2017.
 */

public class CountryCurrency {

    private int ID;
    private int flag;
    private String CountryName;
    private String Currency;
    private String CryptoCurrency;
    private String CurrencyAbbreviation;
    private String Symbol;

    public CountryCurrency(){}

    public CountryCurrency(String countryName, String currency, String currencyAbbreviation, int Flag, String cryptoCurrency, String symbol) {

        CountryName = countryName;
        Currency = currency;
        CurrencyAbbreviation = currencyAbbreviation;
        flag = Flag;
        CryptoCurrency = cryptoCurrency;
        Symbol = symbol;

    }

    public String getCryptoCurrency() {
        return CryptoCurrency;
    }

    public void setCryptoCurrency(String cryptoCurrency) {
        CryptoCurrency = cryptoCurrency;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getCurrencyAbbreviation() {
        return CurrencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        CurrencyAbbreviation = currencyAbbreviation;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }
}
