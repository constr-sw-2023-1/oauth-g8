package com.example.oauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth.Entity.LoginForm;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class AuthController {

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        return new ResponseEntity<>("Opa", HttpStatus.OK);
    }

}
