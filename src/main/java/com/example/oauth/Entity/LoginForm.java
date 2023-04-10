package com.example.oauth.Entity;

import lombok.Getter;
import lombok.Setter;

public class LoginForm {

    @Getter
    @Setter
    private String clientId;

    @Getter
    @Setter
    private String clientSecret;

    @Getter
    @Setter
    private String grantType;
}
