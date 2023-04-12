package com.example.oauth.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PutUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
}
