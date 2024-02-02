package com.marijana.library1223.controllers;

import com.marijana.library1223.configuration.HandleBindingErrors;
import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.exceptions.AccessDeniedException;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<Object> createNewReservation(
            @Valid @RequestBody ReservationDto reservationDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

        System.out.println(userDetails.getUsername());


        ResponseEntity<Object> bindingErrorResponse = HandleBindingErrors.handleBindingErrors(bindingResult);

        if (bindingErrorResponse == null) {
            return bindingErrorResponse;
        }

        reservationService.createReservation(reservationDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + reservationDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(reservationDto);
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
                @PathVariable Long idReservation,
                @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

            if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {
                ReservationDto reservationDto = reservationService.getSingleReservation(idReservation);
                return ResponseEntity.ok(reservationDto);

            } else {
                throw new AccessDeniedException("It seems you are not authorized to access this reservation.");
            }

        }


        @PutMapping("/{idReservation}/books/{idBook}")
        public ResponseEntity<Object> assignBookToReservation (
                @PathVariable Long idBook,
                @PathVariable Long idReservation,
                @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

            if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {

                reservationService.assignBookToReservation(idBook, idReservation);
                return ResponseEntity.noContent().build();

            } else {

                throw new AccessDeniedException("It seems you are not authorized to update this reservation.");

            }
        }

        @PutMapping("/{idReservation}/accounts/{idAccount}")
        public ResponseEntity<Object> assignAccountToReservation (
                @PathVariable Long idAccount,
                @PathVariable Long idReservation,
                @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

            if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {

                reservationService.assignAccountToReservation(idAccount, idReservation);
                return ResponseEntity.noContent().build();

            } else {

                throw new AccessDeniedException("It seems you are not authorized to update this reservation.");
            }
        }


        @PutMapping("/{idReservation}")
        public ResponseEntity<ReservationDto> fullUpdateReservation (
                @PathVariable Long idReservation,
                @Valid @RequestBody ReservationDto reservationDto,
                @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

            if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {

                ReservationDto reservationDto1 = reservationService.fullUpdateReservation(idReservation, reservationDto);
                return ResponseEntity.ok().body(reservationDto1);

            } else {
                throw new AccessDeniedException("It seems you are not authorized to update this reservation");
            }
        }


        @DeleteMapping("/{idReservation}")
        public ResponseEntity<Object> deleteReservation (
                @PathVariable Long idReservation,
                @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

            if (userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {
                reservationService.deleteReservation(idReservation);
                return ResponseEntity.noContent().build();
            } else {

                throw new AccessDeniedException("It seems you are not authorized to delete this reservation");

            }
        }

    }

