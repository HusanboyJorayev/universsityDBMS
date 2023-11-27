package com.example.universsitydbms.dto;

import com.example.universsitydbms.model.Marks;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
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
public class StudentsDto {
    private Integer id;
    private Integer groupId;
    private Integer teacherId;

    @NotBlank(message = "lastname cannot be null or empty")
    private String lastname;
    @NotBlank(message = "firstname cannot be null or empty")
    private String firstname;

    private List<MarksDto> marks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
