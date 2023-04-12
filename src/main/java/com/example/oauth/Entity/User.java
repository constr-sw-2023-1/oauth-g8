package com.example.oauth.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private String clientId;
    private String clientSecret;
    private String username;
    private String credentials;
    private String grant_type;

}
