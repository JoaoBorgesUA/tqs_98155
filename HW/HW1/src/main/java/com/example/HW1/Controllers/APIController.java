package com.example.HW1.Controllers;

import java.io.IOException;

import com.example.HW1.Cache;
import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;
import com.example.HW1.Service.APIService;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HW1")
public class APIController {

    private static final Logger log = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private APIService service;

    @Autowired
    private CovRepository covRepository;

    @Autowired
    private Cache cache;

    @GetMapping("/get_country/{country}")
    public CovObject getCountry(@PathVariable(value = "country") String country)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting Country statistics of {}", country);
        return service.serviceGetCountry(country);
    }

    @GetMapping("/reportDay/{date}")
    public CovObject getDateReport(@PathVariable(value = "date") String date)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting statistics of the day {}", date);
        return service.serviceGetDay(date);

    }

    @GetMapping("/cacheStats/getHits")
    public int getCacheStatsHits()
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting Cache statistics of the amount of hits");
        return cache.getHits();
    }

    @GetMapping("/cacheStats/getMisses")
    public int getCacheStatsMisses()
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting Cache statistics of the amount of misses");
        return cache.getMisses();
    }

    @GetMapping("/cacheStats/getRequests")
    public int getCacheStatsRequests()
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        log.info("Requesting Cache statistics of the amount of Requests");
        return cache.getRequest();
    }
}
