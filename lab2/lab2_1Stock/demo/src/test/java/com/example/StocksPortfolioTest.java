package com.example;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    @Mock
    private IStockmarketService stockM;

    @InjectMocks
    private StockPortfolio stockP;

    @Test
    public void getTotalValueTest(){
        stockP.addStock(new Stock("s", 5));
        stockP.addStock(new Stock("s2", 3));
        stockP.addStock(new Stock("s3", 1));

        Mockito.when(stockM.lookUpPrice("s")).thenReturn(5.0);
        Mockito.when(stockM.lookUpPrice("s2")).thenReturn(3.0);
        Mockito.when(stockM.lookUpPrice("s3")).thenReturn(2.0);

        assertEquals(stockP.getTotalValue(), 5.0*5+3.0*3+2.0*1);
        assertThat(stockP.getTotalValue(), is(5.0*5+3.0*3+2.0*1));

        Mockito.verify(stockM, times(2)).lookUpPrice(new String());
        Mockito.verify(stockM).lookUpPrice("s");


    }
}
