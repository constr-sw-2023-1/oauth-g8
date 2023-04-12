package com.example.oauth.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {
    private String type;
    private String value;
    private boolean temporary;
}
