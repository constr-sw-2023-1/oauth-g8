package com.example.oauth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .components(new Components()
                                                .addSecuritySchemes("Authorization", new SecurityScheme()
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")))
                                .info(new Info()
                                                .title("API")
                                                .version("1.0.0"))
                                .addSecurityItem(new SecurityRequirement().addList("Authorization",
                                                Arrays.asList("read", "write")));
        }

}
