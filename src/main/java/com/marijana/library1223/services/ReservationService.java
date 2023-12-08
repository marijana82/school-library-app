package com.marijana.library1223.services;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    //createReservation - post mapping
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setBookTitle(reservationDto.getBookTitle());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setNumberOfBooksReserved(reservationDto.getNumberOfBooksReserved());
        reservation.setSidenote(reservationDto.getSidenote());
        reservationRepository.save(reservation);
        reservationDto.setId(reservation.getId());
        return reservationDto;
    }


    //get all reservations - get mapping
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservationList) {
            ReservationDto reservationDto = transferReservationToReservationDto(reservation);
            reservationDtoList.add(reservationDto);
        }
        return reservationDtoList;
    }

    //helper methods.........................................

    //helper method - transferReservationToReservationDto
    public ReservationDto transferReservationToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setBookTitle(reservation.getBookTitle());
        reservationDto.setReservationDate(reservation.getReservationDate());
        reservationDto.setNumberOfBooksReserved(reservation.getNumberOfBooksReserved());
        reservationDto.setSidenote(reservation.getSidenote());
        return reservationDto;
    }

    //helper method - transferReservationDtoToReservation
    public Reservation transferReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setBookTitle(reservationDto.getBookTitle());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setNumberOfBooksReserved(reservationDto.getNumberOfBooksReserved());
        reservation.setSidenote(reservationDto.getSidenote());
        return reservation;
    }



}
