package com.marijana.library1223.controllers;

import com.marijana.library1223.helpers.HandleBindingErrors;
import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Object> createNewReservation(
            @Valid @RequestBody ReservationDto reservationDto,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return HandleBindingErrors.handleBindingErrors(bindingResult);

        } else {

            reservationService.createReservation(reservationDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + reservationDto.getId())
                    .toUriString());
            return ResponseEntity.created(uri).body(reservationDto);

        }
    }



        @GetMapping
        public ResponseEntity<List<ReservationDto>> getAllReservations () {
            List<ReservationDto> reservationDtoList = reservationService.getAllReservations();
            return ResponseEntity.ok(reservationDtoList);
        }


        @GetMapping("/dates")
        public ResponseEntity<List<ReservationDto>> getAllReservationsPerDate (@RequestParam LocalDate reservationDate){
            return ResponseEntity.ok(reservationService.showAllReservationsByReservationDate(reservationDate));
        }


    @GetMapping("/{idReservation}")
    public ResponseEntity<ReservationDto> getSingleReservation (
            @PathVariable Long idReservation) {

            ReservationDto reservationDto = reservationService.getSingleReservation(idReservation);
            return ResponseEntity.ok(reservationDto);
        }



    @PutMapping("/{idReservation}/books/{idBook}")
    public ResponseEntity<Object> assignBookToReservation (
            @PathVariable Long idBook,
            @PathVariable Long idReservation) {

        reservationService.assignBookToReservation(idBook, idReservation);
        return ResponseEntity.noContent().build();

    }



        @PutMapping("/{idReservation}/accounts/{idAccount}")
        public ResponseEntity<Object> assignAccountToReservation (
                @PathVariable Long idAccount,
                @PathVariable Long idReservation) {

                reservationService.assignAccountToReservation(idAccount, idReservation);
                return ResponseEntity.noContent().build();

        }


        @PutMapping("/{idReservation}")
        public ResponseEntity<ReservationDto> fullUpdateReservation (
                @PathVariable Long idReservation,
                @Valid @RequestBody ReservationDto reservationDto) {

                ReservationDto reservationDto1 = reservationService.fullUpdateReservation(idReservation, reservationDto);
                return ResponseEntity.ok().body(reservationDto1);

        }


        @DeleteMapping("/{idReservation}")
        public ResponseEntity<Object> deleteReservation (
                @PathVariable Long idReservation) {
                reservationService.deleteReservation(idReservation);
                return ResponseEntity.noContent().build();
        }



}




