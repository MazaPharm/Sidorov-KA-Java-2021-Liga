package org.example.repository;

import org.example.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Groups, Long> {

    List<Groups> findAll();

    Optional<Groups> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Groups g WHERE g.id=:id")
    void deleteById(Long id);
}
