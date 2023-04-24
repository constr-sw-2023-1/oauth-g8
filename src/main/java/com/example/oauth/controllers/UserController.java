package com.example.oauth.controllers;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.Entity.PasswordDTO;
import com.example.oauth.Entity.PutUserDTO;
import com.example.oauth.Entity.UserDTO;
import com.example.oauth.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Recuperação dos dados de todos os usuários cadastrados
     * 
     * @param bearerToken Token de autenticação
     * @return
     */
    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> getUsers(@RequestHeader("Authorization") String bearerToken) {
        try {
            HttpResponse<?> response = userService.getUsers(bearerToken);

            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = (String) response.body();
            JsonNode json = objectMapper.readValue(responseBody, JsonNode.class);

            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (IOException e) {
            // 400 - Bad Request
            return new ResponseEntity<>("Erro na estrutura da chamada", HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            // 401 - Unauthorized
            return new ResponseEntity<>("username e/ou password inválidos", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // 404 - Not found
            return new ResponseEntity<>("o objeto requisitado não foi localizado", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> createUser(@RequestHeader("Authorization") String bearerToken,
            @RequestBody UserDTO userDTO) {
        try {
            HttpResponse<?> response = userService.createUser(bearerToken, userDTO);

            if (response.statusCode() == HttpStatus.CREATED.value()) {
                return ResponseEntity.ok(response.body());
            } else {
                return ResponseEntity.status(response.statusCode()).body(response.body());
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na estrutura da chamada");
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access token inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não possui permissão");
        }
    }

    /**
     * Recuperação de um único usuário
     * 
     * @param bearerToken Token de autenticação
     * @param id          Id do usuário
     * @return
     */
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String bearerToken, @PathVariable String id) {
        try {
            HttpResponse<?> response = userService.getUserById(bearerToken, id);

            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = (String) response.body();
            JsonNode json = objectMapper.readValue(responseBody, JsonNode.class);

            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (IOException e) {
            // 400 - Bad Request
            return new ResponseEntity<>("Erro na estrutura da chamada", HttpStatus.BAD_REQUEST);
        } catch (InterruptedException e) {
            // 401 - Unauthorized
            return new ResponseEntity<>("username e/ou password inválidos", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // 404 - Not found
            return new ResponseEntity<>("o objeto requisitado não foi localizado", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deleção de um usuário
     * 
     * @param bearerToken
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String bearerToken, @PathVariable String id) {
        try {
            HttpResponse<?> response = userService.getUserById(bearerToken, id);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String bearerToken, @PathVariable String id,
            @RequestBody PutUserDTO user) {
        try {
            HttpResponse<?> response = userService.updateUser(bearerToken, id, user);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String bearerToken, @PathVariable String id,
            @RequestBody PasswordDTO password) {
        try {
            HttpResponse<?> response = userService.updatePassword(bearerToken, id, password);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
