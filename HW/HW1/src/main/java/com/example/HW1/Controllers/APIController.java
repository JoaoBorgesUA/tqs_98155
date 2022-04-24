package com.example.HW1.Controllers;

import java.io.IOException;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;
import com.example.HW1.Service.APIService;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HW1")
public class APIController {

    @Autowired
    private APIService service;

    @Autowired
    private CovRepository covRepository;

    // TWEETS

    // @GetMapping("/all_tweets")
    // public List<Datum> getAllTweets(@RequestParam(value = "trends", required =
    // false) String trends) {
    // return service.getAllTweets();

    @GetMapping("/get_country/{country}")
    public CovObject getCountry(@PathVariable(value = "country") String country)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        return service.serviceGetCountry(country);
    }

    @GetMapping("/reportDay/{date}")
    public CovObject getDateReport(@PathVariable(value = "date") String date)
            throws IOException, ParseException, InterruptedException, java.text.ParseException {
        return service.serviceGetDay(date);
    }
}
