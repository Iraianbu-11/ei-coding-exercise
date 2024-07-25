package com.iraianbu.observer;

public class Client {
    public static void main(String[] args) {
       // Observable
       StockAgency stockAgency = new StockAgency();
       // Observer
       StockInvestor angelInvestor = new StockInvestor();
       StockInvestor personalInvestor = new StockInvestor();
       stockAgency.addInvestors(angelInvestor);
       stockAgency.addInvestors(personalInvestor);
       stockAgency.setStockDetails("NVIDIA",100.00);
       System.out.println(angelInvestor);
       stockAgency.removeInvestors(angelInvestor);
       stockAgency.setStockDetails("NVIDIA",120.00);
       System.out.println(personalInvestor);
    }
}