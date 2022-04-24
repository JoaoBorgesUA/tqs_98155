package com.example.HW1;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Resolver;
import com.example.HW1.Client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ResolverTest {

    @MockBean
    Client httpClient;

    @InjectMocks
    Resolver resolver;

    @Test
    public void ApiAskTest() throws IOException, InterruptedException {
        when(httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports?region_name=Portugal&date=2020-04-16"))
                .thenReturn("{\"id\":1,\"country\":\"Portugal\",\"new_cases\":750,\"date\":\"2020-04-16\"");
    }

    @Test
    public void ApiResultTestCountry() throws IOException, ParseException, InterruptedException {
        CovObject testObj = resolver.getDataByCountry("Portugal");
        assertEquals("Portugal", testObj.getCountry());
        assertEquals("2020-04-16", testObj.getDate());
        assertEquals(750, testObj.getNew_cases());

    }

    @Test
    public void BadArgumentsTest() throws IOException, ParseException, InterruptedException {
        assertThrows(NullPointerException.class, () -> {
            CovObject testObj = resolver.getDataByCountry("hasoidhaosid");
        });
    }

    @Test
    public void ApiResultTestDate() throws IOException, ParseException, InterruptedException {
        CovObject testObj = resolver.getDataByDay("2020-04-03");
        assertEquals("null", testObj.getCountry());
        assertEquals("2020-04-03", testObj.getDate());
        assertEquals(82614, testObj.getNew_cases());

    }
}
