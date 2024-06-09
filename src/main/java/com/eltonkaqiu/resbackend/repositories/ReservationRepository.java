package com.eltonkaqiu.resbackend.repositories;

import com.eltonkaqiu.resbackend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
