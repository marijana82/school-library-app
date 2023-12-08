package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //post-mapping
    @PostMapping
    public ResponseEntity<Object> createNewReservation(@Valid @RequestBody ReservationDto reservationDto, BindingResult bindingResult) {
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

    //get mapping all (general)
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservationDtoList = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationDtoList);
    }
}
