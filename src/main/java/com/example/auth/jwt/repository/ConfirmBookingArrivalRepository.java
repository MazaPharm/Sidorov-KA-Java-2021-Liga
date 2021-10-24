package com.example.auth.jwt.repository;

import com.example.auth.jwt.entity.ConfirmBookingArrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ConfirmBookingArrivalRepository extends JpaRepository<ConfirmBookingArrival, Long> {

    ConfirmBookingArrival findByDate (Date date);

    @Modifying(clearAutomatically=true)
    @Query(value="DELETE from ConfirmBookingArrival c where c.id=:id")
    void deleteById(Long id);
}
