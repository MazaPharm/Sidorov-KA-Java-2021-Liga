package com.example.demo.repository;

import com.example.demo.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FriendRepo extends JpaRepository<Friend, Long> {

    Friend findByFriendIdAndUserId(Long friendId, Long userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Friend f WHERE f.id=:id")
    void deleteById(@Param("id") Long id);
}
