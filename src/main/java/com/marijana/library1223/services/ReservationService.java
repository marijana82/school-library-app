package com.marijana.library1223.services;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //get all reservations per date
    public List<ReservationDto> showAllReservationsByReservationDate(LocalDate reservationDate) {
        List<Reservation> reservationList = reservationRepository.findAllReservationsByReservationDate(reservationDate);
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservationList) {
            ReservationDto reservationDto = transferReservationToReservationDto(reservation);
            reservationDtoList.add(reservationDto);
        }
        return reservationDtoList;
    }

    //get single reservation - get mapping (id)
    public ReservationDto getSingleReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()) {
            Reservation reservationFound = optionalReservation.get();
            return transferReservationToReservationDto(reservationFound);

        } else {
            throw new RecordNotFoundException("Reservation with id number " + id + " has not been found.");
        }
    }

    //put

    public ReservationDto fullUpdateReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isEmpty()) {
            throw new RecordNotFoundException("Reservation with id number " + id + " has not been found.");
        } else {
            Reservation reservation = optionalReservation.get();
            Reservation updatedReservation = transferReservationDtoToReservation(reservationDto);
            updatedReservation.setId(reservation.getId());
            reservationRepository.save(updatedReservation);
            return transferReservationToReservationDto(updatedReservation);
        }
    }

    //patch
    //delete




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
