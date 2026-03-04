package com.main.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class Loader implements CommandLineRunner {

    private static final String BAZAAR_URL = "https://api.hypixel.net/skyblock/bazaar";
    private static final String OUTPUT_FILE = "bazaar.json";

    @Override
    public void run(String... args) throws Exception {
        fetchAndSaveBazaarData();
    }

    private void fetchAndSaveBazaarData() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BAZAAR_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            Files.writeString(Path.of(OUTPUT_FILE), response.body());

            System.out.println("Bazaar data successfully downloaded.");
        } else {
            System.out.println("Failed to fetch Bazaar data. HTTP status: "
                    + response.statusCode());
        }
    }
}