package com.example.HW1;

import java.io.IOException;

import com.example.HW1.Models.CovObject;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Resolver {

    private static final Logger log = LoggerFactory.getLogger(Resolver.class);

    @Autowired
    Client httpClient;

    public CovObject getDataByCountry(String country) throws IOException, ParseException, InterruptedException {
        String response = null;
        response = this.httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports?region_name=" + country);

        return JsonTransformCountry(response);
    }

    public CovObject JsonTransformCountry(String json) throws ParseException {
        log.info("--------------------- Initiating response processing ---------------------");

        JSONObject obj = new JSONObject(json);
        JSONArray content = obj.getJSONArray("data");
        if (content.length() < 1) {
            log.info(
                    "--------------------- ATTENTION: Response successfully processed, but no Country was found with this name ---------------------");
            return new CovObject();
        }
        JSONObject region = content.getJSONObject(0).getJSONObject("region");

        return new CovObject(content.getJSONObject(0).getString("date"), region.getString("name"),
                content.getJSONObject(0).getInt("confirmed_diff"));
    }

    public CovObject getDataByDay(String date) throws IOException, InterruptedException, ParseException {
        String response = null;
        response = this.httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports/total?date=" + date);
        System.out.println(response);

        return JsonTransformData(response);
    }

    public CovObject JsonTransformData(String json) throws ParseException {
        log.info("--------------------- Initiating response processing ---------------------");

        JSONObject obj = new JSONObject(json);
        JSONObject content = obj.getJSONObject("data");
        if (content.length() < 1) {
            log.info(
                    "--------------------- ATTENTION: Response successfully processed, but no Country was found with this name ---------------------");
            return new CovObject();
        }

        return new CovObject(content.getString("date"), content.getInt("confirmed_diff"));
    }
}
