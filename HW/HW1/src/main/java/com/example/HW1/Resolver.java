package com.example.HW1;

import java.io.IOException;

import com.example.HW1.Models.CovObject;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Resolver {

    @Autowired
    Client httpClient;

    public CovObject getDataByCountry(String country) throws IOException, ParseException, InterruptedException {
        String response = null;
        response = this.httpClient
                .GetData("https://covid-19-statistics.p.rapidapi.com/reports?region_name=" + country
                        + "&date=2020-04-16");
        System.out.println(response);

        return JsonTransformCountry(response);
    }

    public CovObject JsonTransformCountry(String json) throws ParseException {

        JSONObject obj = new JSONObject(json);
        JSONArray content = obj.getJSONArray("data");
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

        JSONObject obj = new JSONObject(json);
        JSONObject content = obj.getJSONObject("data");

        return new CovObject(content.getString("date"), content.getInt("confirmed_diff"));
    }
}
