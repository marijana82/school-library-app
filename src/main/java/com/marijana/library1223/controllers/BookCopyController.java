package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.services.BookCopyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/book-copies")
public class BookCopyController {

    //constructor injection
    private final BookCopyService bookCopyService;
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    //post-mapping
    public ResponseEntity<Object> createNewBookCopy(@Valid @RequestBody BookCopyDto bookCopyDto, BindingResult bindingResult) {
       if(bindingResult.hasFieldErrors()) {
           //create a string which we return as body
           StringBuilder stringBuilder = new StringBuilder();
           for(FieldError fieldError : bindingResult.getFieldErrors()) {
               stringBuilder.append(fieldError.getField());
               stringBuilder.append(" : ");
               stringBuilder.append(fieldError.getDefaultMessage());
               stringBuilder.append(("\n"));
           }
           return ResponseEntity.badRequest().body(stringBuilder.toString());
       }
       bookCopyService.createNewBookCopy(bookCopyDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + bookCopyDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(bookCopyDto);
    }


    //get-mapping-one
    @GetMapping("/{idCopy}")
    public ResponseEntity<BookCopyDto> getOneCopy(@PathVariable Long idCopy) {
        BookCopyDto bookCopyDto = bookCopyService.showOneCopy(idCopy);
        return ResponseEntity.ok(bookCopyDto);
    }


    //get-mapping-all +
    //put-mapping
    //patch-mapping
    //delete-mapping






    }






