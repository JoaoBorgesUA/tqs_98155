package com.example.HW1;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;

import com.example.HW1.Cache;
import com.example.HW1.Resolver;
import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;
import com.example.HW1.Service.APIService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {
    CovObject MockObj;

    @Mock
    private Cache cache;

    @Mock
    private CovRepository repository;

    @Mock
    private Resolver resolver;

    @InjectMocks
    private APIService service;

    @BeforeEach
    void setUp() {
        MockObj = new CovObject();
        this.MockObj.setCountry("Portugal");
        this.MockObj.setNew_cases(750);
        this.MockObj.setDate("2022-04-16");

    }

    @Test
    public void getDataFromCache()
            throws ParseException, IOException, org.json.simple.parser.ParseException, InterruptedException {
        when(cache.checkCachedCountry("Portugal")).thenReturn(this.MockObj);

        CovObject result = service.serviceGetCountry("Portugal");
        verify(cache, times(1)).checkCachedCountry(anyString());
        assertEquals(result, this.MockObj);
    }

}
