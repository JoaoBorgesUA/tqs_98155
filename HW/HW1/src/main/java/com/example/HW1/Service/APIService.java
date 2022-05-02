package com.example.HW1.Service;

import java.io.IOException;

import com.example.HW1.Cache;
import com.example.HW1.Resolver;
import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APIService {

    private static final Logger log = LoggerFactory.getLogger(APIService.class);

    @Autowired
    Resolver res;

    @Autowired
    CovRepository covRepository;

    @Autowired
    Cache cache;

    public CovObject serviceGetCountry(String country)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("--------------------- Requesting data from cache ---------------------");
        CovObject result = cache.checkCachedCountry(country);
        if (result == null) {
            log.info("--------------------- ATTENTION: No cache data found!!  ---------------------");
            log.info("--------------------- Requesting data from the API  ---------------------");
            result = res.getDataByCountry(country);
            log.info("--------------------- Data requested to the API successfully  ---------------------");
            log.info("--------------------- Saving on the repository  ---------------------");
            covRepository.save(result);
            log.info("--------------------- Data saved in the repository successfully  ---------------------");
        }

        return result;
    }

    public CovObject serviceGetDay(String date)
            throws IOException, InterruptedException, ParseException, java.text.ParseException {
        log.info("--------------------- Requesting data from cache ---------------------");
        CovObject result = cache.checkCachedDate(date);
        if (result == null) {
            log.info("--------------------- ATTENTION: No cache data found!!  ---------------------");
            log.info("--------------------- Requesting data from the API  ---------------------");
            result = res.getDataByDay(date);
            log.info("--------------------- Data requested to the API successfully  ---------------------");
            log.info("--------------------- Saving on the repository  ---------------------");
            covRepository.save(result);
            log.info("--------------------- Data saved on the repository successfully  ---------------------");
        }
        return result;
    }

}
