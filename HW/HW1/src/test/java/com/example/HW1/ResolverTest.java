package com.example.HW1;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.io.IOException;

import com.example.HW1.Models.CovObject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResolverTest {

    @Mock(lenient = true)
    Client httpClient;

    @InjectMocks
    Resolver resolver;

    @Mock
    Environment environments;

    @Test
    public void ApiResultTestCountry() throws IOException, ParseException, InterruptedException {
        when(httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports?region_name=Portugal"))
                .thenReturn(
                        "{\"data\":[{\"id\":1,\"confirmed_diff\":750,\"date\":\"2020-04-16\",\"region\":{\"name\":\"Portugal\"}}]}");
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
        when(httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports/total?date=2020-04-03"))
                .thenReturn(
                        "{\"data\":{\"id\":1,\"confirmed_diff\":82614,\"date\":\"2020-04-03\"}}");
        CovObject testObj = resolver.getDataByDay("2020-04-03");
        assertEquals("2020-04-03", testObj.getDate());
        assertEquals(82614, testObj.getNew_cases());

    }
}
