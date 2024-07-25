package com.iraianbu.observer;

public class StockInvestor implements Investor{
    private String stockName;
    private double stockPrice;

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    @Override
    public void update(String stockName , double stockPrice ) {
        this.setStockPrice(stockPrice);
        this.setStockName(stockName);
    }

    @Override
    public String toString() {
        return "StockInvestor = [" +
                "stockName='" + stockName + '\'' +
                ", stockPrice=" + stockPrice +
                ']';
    }
}
