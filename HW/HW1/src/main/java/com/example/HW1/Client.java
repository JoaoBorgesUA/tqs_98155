package com.example.HW1;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import java.net.http.HttpClient;

@Component
public class Client {
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

            response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            System.err.println(e);
        }
        return response.body();
    }
}
