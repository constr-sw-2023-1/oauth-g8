package com.example.oauth.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private boolean enabled;
}
