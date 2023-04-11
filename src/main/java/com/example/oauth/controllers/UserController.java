package com.example.oauth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/users", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
public class UserController {

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>("teste", HttpStatus.OK);
    }

}
