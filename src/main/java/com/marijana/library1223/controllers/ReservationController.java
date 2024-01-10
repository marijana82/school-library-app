package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    //TODO:CHECK IF THIS USAGE OF @AuthenticationPrincipal IS CORRECT
    @PostMapping
    public ResponseEntity<Object> createNewReservation(
            @Valid @RequestBody ReservationDto reservationDto, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails) {

        //
        if(!userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("LIBRARIAN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Reservations can only be created by librarians.");
        }

        if(bindingResult.hasFieldErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getField());
                stringBuilder.append(" : ");
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(("\n"));
            }
            return ResponseEntity.badRequest().body(stringBuilder.toString());
        }
        reservationService.createReservation(reservationDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + reservationDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(reservationDto);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservationDtoList = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationDtoList);
    }

    //get all per reservation date
    @GetMapping("/dates")
    public ResponseEntity<List<ReservationDto>> getAllReservationsPerDate(@RequestParam LocalDate reservationDate) {
        return ResponseEntity.ok(reservationService.showAllReservationsByReservationDate(reservationDate));
    }

    //get one
   /* @GetMapping("/{idReservation}")
    public ResponseEntity<ReservationDto> getSingleReservation(@PathVariable Long idReservation) {
        return ResponseEntity.ok(reservationService.getSingleReservation(idReservation));
    }*/

    //TODO:CHECK IF THIS USAGE OF @AuthenticationPrincipal IS CORRECT
    @GetMapping("/{idReservation}")
    public ResponseEntity<ReservationDto> getSingleReservation(
            @PathVariable Long idReservation,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        ReservationDto reservationDto = reservationService.getSingleReservation(idReservation, username);

        if (reservationDto != null) {
            return ResponseEntity.ok(reservationDto);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    //update reservation
    @PutMapping("/{idReservation}")
    public ResponseEntity<ReservationDto> fullUpdateReservation(@PathVariable Long idReservation, @Valid @RequestBody ReservationDto reservationDto) {
        ReservationDto reservationDto1 = reservationService.fullUpdateReservation(idReservation, reservationDto);
        return ResponseEntity.ok().body(reservationDto1);
    }

    //TODO: CREATE PATCH MAPPING
    //patch mapping - reservation

    //delete reservation
    @DeleteMapping("/{idReservation}")
    public ResponseEntity<Object> deleteReservation(@PathVariable Long idReservation) {
        reservationService.deleteReservation(idReservation);
        return ResponseEntity.noContent().build();
    }

    //add book to reservation
    @PutMapping("/{idReservation}/books/{idBook}")
    public ResponseEntity<Object> assignBookToReservation(@PathVariable Long idBook, @PathVariable Long idReservation) {
        reservationService.assignBookToReservation(idBook, idReservation);
        return ResponseEntity.noContent().build();
    }

    //add account to reservation
    @PutMapping("/{idReservation}/accounts/{idAccount}")
    public ResponseEntity<Object> assignAccountToReservation(@PathVariable Long idAccount, @PathVariable Long idReservation) {
        reservationService.assignAccountToReservation(idAccount, idReservation);
        return ResponseEntity.noContent().build();
    }


}
