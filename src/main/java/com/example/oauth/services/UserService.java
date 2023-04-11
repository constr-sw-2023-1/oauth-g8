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

    public HttpResponse<?> getUsers(String bearerToken) throws URISyntaxException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://keycloak:8080/auth/admin/realms/constr-sw-2023-1/users"))
                .setHeader("Authorization", bearerToken)
                .GET()
                .build();

        // TODO adicionar tratamento de resposta
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

}
