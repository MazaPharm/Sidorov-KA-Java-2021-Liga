package com.example.auth.jwt.repository;

import com.example.auth.jwt.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long id);

    List<Booking> findByStatus(String status);

    Booking findByDate(Date date);

    Optional<Booking> findById(Long id);

    @Modifying(clearAutomatically=true)
    @Query(value="DELETE from Booking b where b.id=:id")
    void deleteById(Long id);
}
