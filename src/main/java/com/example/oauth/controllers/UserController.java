package com.example.oauth.controllers;

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
import com.example.oauth.services.UserService;

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
    public ResponseEntity<?> getUsers(@RequestHeader("Authorization") String bearerToken) {
        try {
            HttpResponse<?> response = userService.getUsers(bearerToken);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestHeader("Authorization") String bearerToken,
            @RequestBody String userJson) {
        try {
            HttpResponse<?> response = userService.createUser(bearerToken, userJson);

            if (response.statusCode() == HttpStatus.CREATED.value()) {
                return ResponseEntity.ok(response.body());
            } else {
                return ResponseEntity.status(response.statusCode()).body(response.body());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String bearerToken, @PathVariable String id) {
        try {
            HttpResponse<?> response = userService.getUserById(bearerToken, id);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
