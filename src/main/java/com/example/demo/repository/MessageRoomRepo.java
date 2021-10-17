package com.example.demo.repository;

import com.example.demo.entity.MessageRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRoomRepo extends JpaRepository<MessageRoom, Long> {

    Optional<MessageRoom> findById(Long id);

    List<MessageRoom> findByFirstUserId(Long id);

    List<MessageRoom> findBySecondUserId(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from MessageRoom m WHERE m.id=:id")
    void deleteById(@Param("id") Long id);
}
