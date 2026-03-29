package com.hms.patientservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {

        // Load .env file and push each variable into System properties
        // so Spring can read them as ${DB_URL}, ${DB_USERNAME}, etc.
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing() // won't crash if .env doesn't exist (e.g. on a server)
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(PatientServiceApplication.class, args);
    }
}
