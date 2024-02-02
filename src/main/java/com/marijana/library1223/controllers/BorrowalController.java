package com.marijana.library1223.controllers;

import com.marijana.library1223.configuration.HandleBindingErrors;
import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.services.BorrowalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/borrowals")

public class BorrowalController {

    private final BorrowalService borrowalService;
    public BorrowalController(BorrowalService borrowalService) {
        this.borrowalService = borrowalService;
    }

    @PostMapping
    public ResponseEntity<Object> createNewBorrowal(
            @Valid @RequestBody BorrowalDto borrowalDto,
            BindingResult bindingResult) {

        ResponseEntity<Object> bindingErrorResponse = HandleBindingErrors.handleBindingErrors(bindingResult);

        if (bindingErrorResponse != null) {
            return bindingErrorResponse;
        }

        borrowalService.createBorrowal(borrowalDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + borrowalDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(borrowalDto);

    }


    @GetMapping
    public ResponseEntity<List<BorrowalDto>> getAllBorrowals() {
        List<BorrowalDto> borrowalDtoList = borrowalService.getAllBorrowals();
        return ResponseEntity.ok(borrowalDtoList);
    }


    @GetMapping("/{idBorrowal}")
    public ResponseEntity<Object> getSingleBorrowal(@PathVariable Long idBorrowal) {
        return ResponseEntity.ok(borrowalService.getSingleBorrowal(idBorrowal));
    }

    @PutMapping("/{idBorrowal}")
    public ResponseEntity<BorrowalDto> fullUpdateBorrowal(@PathVariable Long idBorrowal, @Valid @RequestBody BorrowalDto borrowalDto) {
        BorrowalDto borrowalDto1 = borrowalService.fullUpdateBorrowal(idBorrowal, borrowalDto);
        return ResponseEntity.ok().body(borrowalDto1);
    }


    @PutMapping("/{idBorrowal}/copies/{idCopy}")
    public ResponseEntity<Object> assignBookCopyToBorrowal(@PathVariable("idBorrowal") Long idBorrowal, @PathVariable("idCopy") Long idCopy) {
        borrowalService.assignBookCopyToBorrowal(idBorrowal, idCopy);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{idBorrowal}/reservations/{idReservation}")
    public ResponseEntity<Object> assignReservationToBorrowal(@PathVariable("idBorrowal") Long idBorrowal, @PathVariable("idReservation") Long idReservation) {
        borrowalService.assignReservationToBorrowal(idBorrowal, idReservation);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{idBorrowal}/accounts/{idAccount}")
    public ResponseEntity<Object> assignAccountToBorrowal(@PathVariable("idBorrowal") Long idBorrowal, @PathVariable("idAccount") Long idAccount) {
        borrowalService.assignAccountToBorrowal(idBorrowal, idAccount);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{idBorrowal}")
    public ResponseEntity<BorrowalDto> partialUpdateBorrowal(@PathVariable Long idBorrowal, @Valid @RequestBody BorrowalDto borrowalDto) {
        BorrowalDto borrowalDto1 = borrowalService.partialUpdateBorrowal(idBorrowal, borrowalDto);
        return ResponseEntity.ok().body(borrowalDto1);
    }

   @DeleteMapping("/{idBorrowal}")
    public ResponseEntity<Object> deleteBorrowal(@PathVariable Long idBorrowal) {
        borrowalService.deleteBorrowal(idBorrowal);
        return ResponseEntity.noContent().build();
    }


}
