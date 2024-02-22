package com.heshanthenura.cocktails.services;

import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {

    public String getJsonString(String url) throws IOException, URISyntaxException {
        URI uri = new URI(url);
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println("done fetch");
        return response.toString();
    }


    public Task<String> APITask(String url) {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                return getJsonString(url);
            }
        };
    }


}
