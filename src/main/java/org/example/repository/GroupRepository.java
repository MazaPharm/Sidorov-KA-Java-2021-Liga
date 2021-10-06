package org.example.repository;

import org.example.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Groups, Long> {

    List<Groups> findAll();
}
