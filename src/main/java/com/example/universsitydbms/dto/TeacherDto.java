package com.example.universsitydbms.dto;

import com.example.universsitydbms.model.Students;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto {
    private Integer id;
    private Double salary;

    @NotBlank(message = "firstname cannot be null or empty")
    private String firstname;
    @NotBlank(message = "lastname cannot be null or empty")
    private String lastname;

    private List<StudentsDto> students;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
