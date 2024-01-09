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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book-copy")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping
    public ResponseEntity<Object> createBookCopy(@Valid @RequestBody BookCopyDto bookCopyDto, BindingResult bindingResult) {
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
       bookCopyService.createBookCopy(bookCopyDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + bookCopyDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(bookCopyDto);
    }


    //get one
    @GetMapping("/{idCopy}")
    public ResponseEntity<BookCopyDto> getOneCopy(@PathVariable Long idCopy) {
        BookCopyDto bookCopyDto = bookCopyService.showOneCopy(idCopy);
        return ResponseEntity.ok(bookCopyDto);
    }


    //get all
    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies() {
        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopies();
        return ResponseEntity.ok(bookCopyDtoList);
    }

    //get-all-year-published-after
    @GetMapping("/after")
    public ResponseEntity<List<BookCopyDto>> getAllBookCopiesPublishedAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(bookCopyService.getAllBookCopiesPublishedAfter(date));

    }


    //get-all-dyslexia-friendly
    @GetMapping("/dyslexia")
    public ResponseEntity<List<BookCopyDto>> getAllBookCopiesDyslexiaFriendly(@RequestParam boolean dyslexia) {
        return ResponseEntity.ok(bookCopyService.getAllBookCopiesDyslexiaFriendly(dyslexia));
    }

    //get-all-audio-book
    @GetMapping("/audio")
    public ResponseEntity<List<BookCopyDto>> getAllBookCopiesAudio(@RequestParam boolean audio) {
        return ResponseEntity.ok(bookCopyService.getAllBookCopiesAudio(audio));
    }

    @PutMapping("/{idCopy}")
    public ResponseEntity<BookCopyDto> fullUpdateBookCopy(@PathVariable Long idCopy, @Valid @RequestBody BookCopyDto bookCopyDto) {
        BookCopyDto bookCopyDto1 = bookCopyService.updateOneBookCopy(idCopy, bookCopyDto);
        return ResponseEntity.ok().body(bookCopyDto1);
    }

    @PutMapping("/{idCopy}/books/{idBook}")
    public ResponseEntity<Object> assignBookToBookCopy(@PathVariable Long idCopy, @PathVariable Long idBook) {
        bookCopyService.assignBookToBookCopy(idCopy, idBook);
        return ResponseEntity.noContent().build();
    }

    //delete
    //TODO:CREATE REQUEST IN POSTMAN!
    @DeleteMapping("/{idCopy}")
    public ResponseEntity<Object> deleteOneCopy(@PathVariable Long idCopy) {
        bookCopyService.deleteCopyById(idCopy);
        return ResponseEntity.noContent().build();
    }


    //TODO: CREATE PATCH
    //patch-mapping










    }






