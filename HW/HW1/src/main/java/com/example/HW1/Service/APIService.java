package com.example.HW1.Service;

import java.io.IOException;

import com.example.HW1.Cache;
import com.example.HW1.Resolver;
import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIService {

    @Autowired
    Resolver res;

    @Autowired
    CovRepository covRepository;

    @Autowired
    Cache cache;

    public CovObject serviceGetCountry(String country)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        CovObject result = cache.checkCachedCountry(country);
        if (result == null) {
            result = res.getDataByCountry(country);
            covRepository.save(result);
        }

        return result;
    }

    public CovObject serviceGetDay(String date)
            throws IOException, InterruptedException, ParseException, java.text.ParseException {
        CovObject result = cache.checkCachedDate(date);
        result = res.getDataByDay(date);
        return result;
    }
}
