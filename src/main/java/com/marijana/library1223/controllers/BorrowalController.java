package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.dtos.IdInputDto;
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

    //constructor injection
    private final BorrowalService borrowalService;
    public BorrowalController(BorrowalService borrowalService) {
        this.borrowalService = borrowalService;
    }

    //post-mapping
    @PostMapping
    public ResponseEntity<Object> createNewBorrowal(@Valid @RequestBody BorrowalDto borrowalDto, BindingResult bindingResult) {
        //if there are field errors
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
       //if there are no errors
        borrowalService.createBorrowal(borrowalDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + borrowalDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(borrowalDto);

    }


    //get-mapping-all (all borrowals incl. reservations)
    @GetMapping
    public ResponseEntity<List<BorrowalDto>> getAllBorrowals() {
        List<BorrowalDto> borrowalDtoList = borrowalService.getAllBorrowals();
        return ResponseEntity.ok(borrowalDtoList);
    }

    //get-mapping-one (incl. reservation)
    @GetMapping("/{idBorrowal}")
    public ResponseEntity<Object> getSingleBorrowal(@PathVariable Long idBorrowal) {
        return ResponseEntity.ok(borrowalService.getSingleBorrowal(idBorrowal));
    }

    //put-mapping (update borrowal)
    @PutMapping("/{idBorrowal}")
    public ResponseEntity<BorrowalDto> fullUpdateBorrowal(@PathVariable Long idBorrowal, @Valid @RequestBody BorrowalDto borrowalDto) {
        BorrowalDto borrowalDto1 = borrowalService.fullUpdateBorrowal(idBorrowal, borrowalDto);
        return ResponseEntity.ok().body(borrowalDto1);
    }

    //put-mapping (add reservation to borrowal, with one path variable and one request body)
    @PutMapping("/{idBorrowal}/reservation")
    public ResponseEntity<Object> assignReservationToBorrowal(@PathVariable("idBorrowal") Long idBorrowal, @Valid @RequestBody IdInputDto input) {
        borrowalService.assignReservationToBorrowal(idBorrowal, input.id);
        return ResponseEntity.noContent().build();
    }

    //put-mapping (add reservation entity to borrowal entity with 2 x path variables)
    @PutMapping("/{idBorrowal}/{idReservation}")
    public ResponseEntity<Object> assignReservationToBorrowal(@PathVariable("idBorrowal") Long idBorrowal, @PathVariable("idReservation") Long idReservation) {
        borrowalService.assignReservationToBorrowal(idBorrowal, idReservation);
        return ResponseEntity.noContent().build();
    }

    //patch-mapping (borrowal incl. reservation)
    @PatchMapping("/{idBorrowal}")
    public ResponseEntity<BorrowalDto> partialUpdateBorrowal(@PathVariable Long idBorrowal, @Valid @RequestBody BorrowalDto borrowalDto) {
        BorrowalDto borrowalDto1 = borrowalService.partialUpdateBorrowal(idBorrowal, borrowalDto);
        return ResponseEntity.ok().body(borrowalDto1);
    }


    //delete-mapping (borrowal)
   @DeleteMapping("/{idBorrowal}")
    public ResponseEntity<Object> deleteBorrowal(@PathVariable Long idBorrowal) {
        borrowalService.deleteBorrowal(idBorrowal);
        return ResponseEntity.noContent().build();
    }


}
