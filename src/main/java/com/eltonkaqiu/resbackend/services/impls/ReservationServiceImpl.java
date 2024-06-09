package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.ReservationDto;
import com.eltonkaqiu.resbackend.dtos.UserDto;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.mappers.ReservationMapper;
import com.eltonkaqiu.resbackend.models.Reservation;
import com.eltonkaqiu.resbackend.models.User;
import com.eltonkaqiu.resbackend.repositories.ReservationRepository;
import com.eltonkaqiu.resbackend.repositories.UserRepository;
import com.eltonkaqiu.resbackend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Override
    public ReservationDto addReservation(Reservation reservation) {
        return null;
    }

    @Override
    public ReservationDto updateReservation(Reservation newReservation, Integer id) {
        return null;
    }

    @Override
    public ReservationDto getReservationById(Integer id) {
        return null;
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return List.of();
    }

    @Override
    public void deleteReservationById(Integer id) {

    }

    private void updateReservationDetails(Reservation existingReservation, Reservation newReservation, Integer id) {
        existingReservation.setId(id);
        existingReservation.setReservationDate(newReservation.getReservationDate());
        existingReservation.setReservationTime(newReservation.getReservationTime());
        existingReservation.setGuests(newReservation.getGuests());
        existingReservation.setStatus(newReservation.getStatus());
        existingReservation.setCreatedAt(existingReservation.getCreatedAt());
        existingReservation.setUpdatedAt(LocalDateTime.now());
    }
}
