package com.example.oauth.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public HttpResponse<?> getUsers() throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://keycloak:8080/auth/admin/realms/constr-sw-2023-1/users"))
                .GET()
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return response;

    }

}
