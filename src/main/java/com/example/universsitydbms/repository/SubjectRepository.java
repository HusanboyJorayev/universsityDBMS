package com.example.universsitydbms.repository;

import com.example.universsitydbms.model.Marks;
import com.example.universsitydbms.model.Subject;
import com.example.universsitydbms.model.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  s from Subject as s
            """)
    List<Subject> getAllSubject();

    Page<Subject> findAllByDeletedAtIsNull(Pageable pageable);
}
