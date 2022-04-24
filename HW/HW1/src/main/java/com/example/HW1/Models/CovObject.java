package com.example.HW1.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CovObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String country;
    private int new_cases;
    private String date;

    public CovObject(String date, String country, int new_cases) {
        this.country = country;
        this.new_cases = new_cases;
        this.date = date;
    }

    public CovObject(String date, int new_cases) {
        this.new_cases = new_cases;
        this.date = date;
    }

    public CovObject() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNew_cases() {
        return this.new_cases;
    }

    public void setNew_cases(int new_cases) {
        this.new_cases = new_cases;
    }

}