package org.example.repository;

import org.example.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findByGroupsId(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "Delete from Post g WHERE g.id=:id")
    void deleteById(Long id);
}
