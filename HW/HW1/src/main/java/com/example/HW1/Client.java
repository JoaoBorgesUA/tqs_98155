package com.example.HW1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;

@Component
public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public Client() {
    }

    public String GetData(String URINeeded) throws IOException, InterruptedException {

        HttpResponse<String> response = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URINeeded))
                    .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "b56d69fbd3msh2450f9252cbbf72p1e82d9jsn5699bdacc5dc")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            log.info("--------------------- Sending the request to the API ---------------------");

            response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            System.err.println(e);
            log.info("--------------------- Error while sending the request: {} ---------------------", e);
        }
        log.info("--------------------- Request Successful ---------------------");
        return response.body();
    }
}
