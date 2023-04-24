package com.example.oauth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

        private final String clientId;
        private final String clientSecret;
        private final String grantType;

        public AuthService(@Value("${spring.security.oauth2.client.registration.keycloak.client-id}") String clientId,
                        @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}") String clientSecret,
                        @Value("${spring.security.oauth2.client.registration.keycloak.authorization-grant-type}") String grantType) {
                this.clientId = clientId;
                this.clientSecret = clientSecret;
                this.grantType = grantType;
        }

        public HttpResponse<?> login(String username, String password)
                        throws URISyntaxException, IOException, InterruptedException {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("client_id", clientId);
                parameters.put("client_secret", clientSecret);
                parameters.put("grant_type", grantType);
                parameters.put("username", username);
                parameters.put("password", password);

                String form = parameters.entrySet()
                                .stream()
                                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                                .collect(Collectors.joining("&"));

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest
                                .newBuilder()
                                .uri(URI.create("http://keycloak:8080/auth/realms/constr-sw-2023-1/protocol/openid-connect/token"))
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .POST(HttpRequest.BodyPublishers.ofString(form, StandardCharsets.UTF_8))
                                .build();

                HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return response;
        }

}