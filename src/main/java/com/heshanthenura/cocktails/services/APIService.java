package com.heshanthenura.cocktails.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class APIService {
    public String getJsonString(String url) throws IOException, URISyntaxException {
        URI uri = new URI(url);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

        // Set the request method to GET
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
        return response.toString();
    }
}
