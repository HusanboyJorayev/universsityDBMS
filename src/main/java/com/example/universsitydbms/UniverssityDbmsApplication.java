package com.example.universsitydbms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Tag(name = "Api Universe city")
public class UniverssityDbmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniverssityDbmsApplication.class, args);
    }

}
