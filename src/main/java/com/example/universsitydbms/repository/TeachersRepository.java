package com.example.universsitydbms.repository;

import com.example.universsitydbms.model.Marks;
import com.example.universsitydbms.model.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Integer> {
    Optional<Teachers> findByIdAndDeletedAtIsNull(Integer id);

    @Query("""
            select  t from Teachers as t
            """)
    List<Teachers> getAllTeachers();

    @Query("""
            select  t from Teachers  as t inner join Students  as s on t.id=s.teacherId
            """)
    List<Teachers> getTeacherByStudent();

    Page<Teachers>findAllByDeletedAtIsNull(Pageable pageable);
}
