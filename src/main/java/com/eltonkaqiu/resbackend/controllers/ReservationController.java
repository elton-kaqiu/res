package com.eltonkaqiu.resbackend.controllers;

import com.eltonkaqiu.resbackend.dtos.ReservationDto;
import com.eltonkaqiu.resbackend.models.Reservation;
import com.eltonkaqiu.resbackend.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

/*    @PostMapping("/")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservation));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody Reservation newReservation, @PathVariable Integer id) {
        return ResponseEntity.ok(reservationService.updateReservation(newReservation, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }*/

}
