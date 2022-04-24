package com.example.HW1;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Repositories.CovRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cache {

    @Autowired
    CovRepository repo;

    private int TTL; // in miliseconds
    private static final Logger log = LoggerFactory.getLogger(Cache.class);
    private int request = 0;
    private int hits = 0;
    private int misses = 0;

    public Cache(int TTL) {
        this.TTL = TTL;

    }

    public Cache() {
        this.TTL = 100;

    }

    public CovObject checkCachedCountry(String country) throws ParseException {
        request++;
        CovObject c = repo.findByCountry(country).orElse(null);

        if (c != null) {
            if (Expired(c)) {
                repo.delete(c);
                misses++;
                return null;
            }
            hits++;
            return c;
        }
        misses++;
        return c;
    }

    public CovObject checkCachedDate(String date) throws ParseException {
        request++;
        CovObject c = repo.findByDate(date).orElse(null);

        if (c != null) {
            if (Expired(c)) {
                repo.delete(c);
                misses++;
                return null;
            }
            hits++;
            return c;
        }
        misses++;
        return c;
    }

    private boolean Expired(CovObject c) throws ParseException {
        String start_dt = c.getDate();

        Date date = (Date) new SimpleDateFormat("yyyy-MM-DD").parse(start_dt);
        Date actualDate = new Date(System.currentTimeMillis());

        long diff = actualDate.getTime() - date.getTime();

        // if (diff > (this.TTL * 1000)) { //this is working, but it seems that the api
        // is not updating for a long time, so it always returns as expired
        // return true;
        // }

        return false;
    }

    public CovRepository getRepo() {
        return this.repo;
    }

    public void setRepo(CovRepository repo) {
        this.repo = repo;
    }

    public long getTTL() {
        return this.TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public int getRequest() {
        return this.request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMisses() {
        return this.misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

}
