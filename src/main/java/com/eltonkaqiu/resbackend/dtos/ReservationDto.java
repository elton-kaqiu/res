package com.eltonkaqiu.resbackend.dtos;

import com.eltonkaqiu.resbackend.models.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Short guests;
    private Status status;
}
