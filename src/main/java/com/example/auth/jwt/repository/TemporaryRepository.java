package com.example.auth.jwt.repository;

import com.example.auth.jwt.entity.Temporary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface TemporaryRepository extends JpaRepository<Temporary, Long> {

    Optional<Temporary> findById(Long id);

    Temporary findByDate(Date date);

    @Modifying(clearAutomatically=true)
    @Query(value="DELETE from Temporary t where t.id=:id")
    void deleteById(Long id);
}
