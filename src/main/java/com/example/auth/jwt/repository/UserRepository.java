package com.example.auth.jwt.repository;

import com.example.auth.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findById(Long id);


    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from User u WHERE u.id=:id")
    void deleteById(@Param("id") Long id);
}
