package com.iraianbu.observer;

import java.util.ArrayList;
import java.util.List;

public class StockAgency {
    private String stockName;
    private double stockPrice;
    private final List<Investor> investors = new ArrayList<>();

    public void addInvestors(Investor investor){
        this.investors.add(investor);
    }

    public void removeInvestors(Investor investor){
        this.investors.remove(investor);
    }

    public void setStockDetails(String stockName , double stockPrice){
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        for(Investor investors : this.investors){
            investors.update(this.stockName, this.stockPrice);
        }
    }
}
