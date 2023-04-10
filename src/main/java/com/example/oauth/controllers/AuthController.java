package com.example.oauth.controllers;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.Entity.LoginForm;
import com.example.oauth.services.AuthService;

@CrossOrigin
@RestController
@RequestMapping(value = "/login", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
public class AuthController {

    @Autowired
    private AuthService authService;

    private LoginForm loginForm;

    @PostMapping()
    public ResponseEntity<?> login(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("grant_type") String grantType) {

        // loginForm.setClientId(clientId);
        // loginForm.setClientSecret(clientSecret);
        // loginForm.setGrantType(grantType);

        try {
            HttpResponse<?> response = authService.login(clientId, clientSecret, grantType);
            return new ResponseEntity<>(response.body(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
