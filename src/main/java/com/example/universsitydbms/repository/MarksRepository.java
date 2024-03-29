package com.example.universsitydbms.repository;

import com.example.universsitydbms.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
    Optional<Marks> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  m from Marks as m
            """)
    List<Marks> getAllMarks();
}
