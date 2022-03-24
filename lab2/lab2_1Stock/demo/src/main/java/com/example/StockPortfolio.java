package com.example;

import java.util.List;

public class StockPortfolio {
    private List<Stock> stocks;
    private IStockmarketService stockmarket;

    public StockPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }


    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public double getTotalValue() {
        int result=0;
        for(Stock s: stocks){
            result+= s.getQuantity() * stockmarket.lookUpPrice(s.getLabel());
        }
        return result;
    }

    
}
