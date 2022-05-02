package com.example.HW1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;
import com.example.HW1.Service.APIService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
        CovObject MockObj;

        @Mock(lenient = true)
        public CovRepository repository;

        @Mock(lenient = true)
        public Cache cache;

        @Mock(lenient = true)
        public Resolver resolver;

        @InjectMocks
        public APIService service;

        @Test
        public void whenValidCountry_returnCachedCases()
                        throws IOException, org.json.simple.parser.ParseException, InterruptedException,
                        ParseException {
                when(cache.checkCachedCountry("Portugal"))
                                .thenReturn(new CovObject("2020-04-03", "Portugal", 2231));
                CovObject result = service.serviceGetCountry("Portugal");
                assertEquals("Portugal", result.getCountry());
                Mockito.verify(cache, VerificationModeFactory.times(1)).checkCachedCountry(Mockito.anyString());
        }

        @Test
        public void whenValidCountry_returnFromResolver()
                        throws IOException, org.json.simple.parser.ParseException, InterruptedException,
                        ParseException {
                when(resolver.getDataByCountry("Albania"))
                                .thenReturn(new CovObject("2020-04-03", "Albania", 2231));
                CovObject result = service.serviceGetCountry("Albania");
                assertEquals("Albania", result.getCountry());
                Mockito.verify(cache, VerificationModeFactory.times(1)).checkCachedCountry(Mockito.anyString());
                Mockito.verify(resolver, VerificationModeFactory.times(1)).getDataByCountry(Mockito.anyString());
        }

        @Test
        public void whenInValidCountry_returnNone()
                        throws IOException, org.json.simple.parser.ParseException, InterruptedException,
                        ParseException {
                when(resolver.getDataByCountry("oiahsdoia"))
                                .thenReturn(new CovObject());
                CovObject result = service.serviceGetCountry("oiahsdoia");
                System.out.println(result);
                assertEquals(null, result.getCountry());
                Mockito.verify(cache, VerificationModeFactory.times(1)).checkCachedCountry(Mockito.anyString());
                Mockito.verify(resolver, VerificationModeFactory.times(1)).getDataByCountry(Mockito.anyString());
        }

}
