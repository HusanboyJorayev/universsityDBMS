package com.example.universsitydbms.repository;

import com.example.universsitydbms.model.Marks;
import com.example.universsitydbms.model.Students;
import com.example.universsitydbms.model.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Optional<Students> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  s from Students as s
            """)
    List<Students> getAllStudents();

    Page<Students> findAllByDeletedAtIsNull(Pageable pageable);
}
