package com.eltonkaqiu.resbackend.services.impls;

import com.eltonkaqiu.resbackend.dtos.ReservationDto;
import com.eltonkaqiu.resbackend.infrastructure.exceptions.EntityNotFoundException;
import com.eltonkaqiu.resbackend.mappers.ReservationMapper;
import com.eltonkaqiu.resbackend.models.Reservation;
import com.eltonkaqiu.resbackend.repositories.ReservationRepository;
import com.eltonkaqiu.resbackend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationDto addReservation(Reservation reservation) {
        var newReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(newReservation);
    }

    @Override
    public ReservationDto updateReservation(Reservation newReservation, Integer id) {
        Reservation existingReservation = getExistingReservationById(id);
        updateReservationDetails(existingReservation, newReservation, id);
        var updatedReservation = reservationRepository.save(existingReservation);
        return reservationMapper.toDto(updatedReservation);
    }

    @Override
    public ReservationDto getReservationById(Integer id) {
        Reservation existingReservation = getExistingReservationById(id);
        return reservationMapper.toDto(existingReservation);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository
                .findAll()
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReservationById(Integer id) {
        this.getReservationById(id);
        reservationRepository.deleteById(id);
    }


    private Reservation getExistingReservationById(Integer id) {
        return reservationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Reservation with id " + id + " not found")
        );
    }

    private void updateReservationDetails(Reservation existingReservation, Reservation newReservation, Integer id) {
        existingReservation.setId(id);
        existingReservation.setFirstName(newReservation.getFirstName());
        existingReservation.setLastName(newReservation.getLastName());
        existingReservation.setEmail(newReservation.getEmail());
        existingReservation.setPhoneNumber(newReservation.getPhoneNumber());
        existingReservation.setReservationDate(newReservation.getReservationDate());
        existingReservation.setReservationTime(newReservation.getReservationTime());
        existingReservation.setGuests(newReservation.getGuests());
        existingReservation.setStatus(newReservation.getStatus());
        existingReservation.setCreatedAt(existingReservation.getCreatedAt());
        existingReservation.setUpdatedAt(LocalDateTime.now());
    }
}
