package com.example.oauth.controllers;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.Entity.User;
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
            @RequestBody User user) {
        try {
            HttpResponse<?> response = userService.updateUser(bearerToken, id, user);

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
