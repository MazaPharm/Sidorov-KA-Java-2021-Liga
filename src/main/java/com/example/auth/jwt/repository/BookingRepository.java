package com.example.auth.jwt.repository;

import com.example.auth.jwt.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long id);

    Booking findByDate(Date date);

    Optional<Booking> findById(Long id);

    @Modifying(clearAutomatically=true)
    @Query(value="DELETE from Booking b where b.id=:id")
    void deleteById(Long id);
}
