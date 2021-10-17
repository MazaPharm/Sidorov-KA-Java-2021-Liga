package com.example.demo.repository;

import com.example.demo.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepo extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findByFriendId(Long id);

    Optional<FriendRequest> findById(Long id);

}
