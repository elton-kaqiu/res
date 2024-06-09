package com.eltonkaqiu.resbackend.mappers;

import com.eltonkaqiu.resbackend.dtos.ReservationDto;
import com.eltonkaqiu.resbackend.models.Reservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {
    private final ModelMapper modelMapper;

    public Reservation toEntity(ReservationDto reservationDto) {
        return modelMapper.map(reservationDto, Reservation.class);
    }

    public ReservationDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }
}
