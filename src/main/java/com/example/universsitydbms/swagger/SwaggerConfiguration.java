package com.example.universsitydbms.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi apiGroup(){
        return GroupedOpenApi.builder()
                .group("Group")
                .pathsToMatch("/groups/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiMArks(){
        return GroupedOpenApi.builder()
                .group("Marks")
                .pathsToMatch("/marks/**")
                .build();
    }
    @Bean
    public GroupedOpenApi apiStudents(){
        return GroupedOpenApi.builder()
                .group("Students")
                .pathsToMatch("/students/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiSubject(){
        return GroupedOpenApi.builder()
                .group("Subject")
                .pathsToMatch("/subject/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiTeacher(){
        return GroupedOpenApi.builder()
                .group("Teacher")
                .pathsToMatch("/teacher/**")
                .build();
    }

}
