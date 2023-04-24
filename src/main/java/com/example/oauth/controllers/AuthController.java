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

import com.example.oauth.services.AuthService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin
@RestController
@RequestMapping(value = "/login")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Rota de autenticação que gera o token de acesso
     * 
     * @param clientId
     * @param clientSecret
     * @param grantType
     * @return
     */
    @Operation(summary = "Autenticação do usuário", requestBody = @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)), responses = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao realizar login"),
            @ApiResponse(responseCode = "401", description = "username e/ou password inválidos"),
    })
    @PostMapping(consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    @ApiImplicitParam(name = "form", dataType = "string", paramType = "form")
    public ResponseEntity<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {

        try {
            HttpResponse<?> response = authService.login(username, password);
            return new ResponseEntity<>(response.body(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
