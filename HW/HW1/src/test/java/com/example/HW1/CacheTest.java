package com.example.HW1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Optional;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CacheTest {

    @Mock(lenient = true)
    private CovRepository repo;

    @InjectMocks
    private Cache cache = new Cache(10);

    @AfterEach
    void clean() {

        cache = new Cache(10);
    }

    @Test
    public void getTest() throws ParseException {
        when(repo.findByCountry("Portugal")).thenReturn(Optional.of(new CovObject("2021-04-02", "Portugal", 120)));
        assertEquals(120, cache.checkCachedCountry("Portugal").getNew_cases());
        assertEquals(1, cache.getHits());
        assertEquals(0, cache.getMisses());
    }

    public void getWhenNoneInCache() throws ParseException {
        when(repo.findByCountry("Portugal")).thenReturn(Optional.of(null));
        assertEquals(null, cache.checkCachedCountry("Portugal"));
        assertEquals(0, cache.getHits());
        assertEquals(1, cache.getMisses());
    }

}
