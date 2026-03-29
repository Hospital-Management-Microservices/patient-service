package com.hms.patientservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI patientServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HMS - Patient Service API")
                        .description("Hospital Management System — Patient Service. " +
                                     "Manages all patient records including registration, " +
                                     "updates, and retrieval.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("HMS Team - Member 1")
                                .email("member1@hms.com")));
    }
}
