package com.example.universsitydbms.repository;

import com.example.universsitydbms.model.Groups;
import com.example.universsitydbms.model.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {

    Optional<Groups> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            """
                    select g from Groups as g
                    """
    )
    List<Groups> getAllGroups();

    Page<Groups> findAllByDeletedAtIsNull(Pageable pageable);
}
