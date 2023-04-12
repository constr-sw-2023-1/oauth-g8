package com.example.oauth.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.example.oauth.Entity.User;

@Service
public class UserService {

    /**
     * Retorna todos os usuários cadastrados
     * 
     * @param bearerToken Token de autenticação
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
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

    /**
     * Retorna um usuário de acordo com o id enviado por parâmetro
     * 
     * @param bearerToken Token de autenticação
     * @param id          Id do usuário
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<?> getUserById(String bearerToken, String id)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://keycloak:8080/auth/admin/realms/constr-sw-2023-1/users/" + id))
                .setHeader("Authorization", bearerToken)
                .GET()
                .build();

        // TODO adicionar tratamento de resposta
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    /**
     * Deleta um usuário de acordo com o id enviado por parâmetro
     * 
     * @param bearerToken Token de autenticação
     * @param id          Id do usuário
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<?> deleteUser(String bearerToken, String id)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://keycloak:8080/auth/admin/realms/constr-sw-2023-1/users/" + id))
                .setHeader("Authorization", bearerToken)
                .DELETE()
                .build();

        // TODO adicionar tratamento de resposta
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    /**
     * Atualiza os dados de um usuário
     * 
     * @param bearerToken Token de autenticação
     * @param id          Id do usuário
     * @param user        Dados do usuário
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public HttpResponse<?> updateUser(String bearerToken, String id, User user)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8090/auth/admin/realms/constr-sw-2023-1/users/" + id))
                .setHeader("Authorization", bearerToken)
                // .PUT(HttpRequest.BodyPublishers.ofString(user, StandardCharsets.UTF_8))
                // Alterar a forma com que é enviado o body da requisição
                .build();

        // TODO adicionar tratamento de resposta
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

}
