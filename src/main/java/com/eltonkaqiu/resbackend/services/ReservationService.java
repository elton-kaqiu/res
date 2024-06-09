package com.eltonkaqiu.resbackend.services;

import com.eltonkaqiu.resbackend.dtos.ReservationDto;
import com.eltonkaqiu.resbackend.models.Reservation;

import java.util.List;

public interface ReservationService {
    ReservationDto addReservation(Reservation reservation);

    ReservationDto updateReservation(Reservation newReservation, Integer id);

    ReservationDto getReservationById(Integer id);

    List<ReservationDto> getAllReservations();

    void deleteReservationById(Integer id);
}
