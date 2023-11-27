package com.example.universsitydbms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer groupId;
    private Integer teacherId;
    private String lastname;
    private String firstname;

    @OneToMany(mappedBy = "studentId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Marks>marks;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
