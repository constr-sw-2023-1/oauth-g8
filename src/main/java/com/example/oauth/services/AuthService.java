package com.example.oauth.services;

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

        /**
         * Obtém um token de acesso para autenticação
         * 
         * @param clientId
         * @param clientSecret
         * @param grantType
         * @return
         * @throws URISyntaxException
         * @throws IOException
         * @throws InterruptedException
         */
        public HttpResponse<?> login(String clientId, String clientSecret, String grantType)
                        throws URISyntaxException, IOException, InterruptedException {

                Map<String, String> parameters = new HashMap<>();
                parameters.put("client_id", clientId);
                parameters.put("client_secret", clientSecret);
                parameters.put("grant_type", grantType);

                String form = parameters.entrySet()
                                .stream()
                                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                                .collect(Collectors.joining("&"));

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest
                                .newBuilder()
                                .uri(new URI("http://keycloak:8080/auth/realms/constr-sw-2023-1/protocol/openid-connect/token"))
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .POST(HttpRequest.BodyPublishers.ofString(form, StandardCharsets.UTF_8))
                                .build();

                HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return response;
        }

}
