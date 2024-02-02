package com.marijana.library1223.controllers;

import com.marijana.library1223.configuration.HandleBindingErrors;
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
    public ResponseEntity<Object> createBookCopy(
            @Valid @RequestBody BookCopyDto bookCopyDto,
            BindingResult bindingResult) {

        ResponseEntity<Object> bindingErrorResponse = HandleBindingErrors.handleBindingErrors(bindingResult);

        if (bindingErrorResponse == null) {
            return bindingErrorResponse;
        }

       bookCopyService.createBookCopy(bookCopyDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + bookCopyDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(bookCopyDto);
    }


    @GetMapping("/{idCopy}")
    public ResponseEntity<BookCopyDto> getOneCopy(@PathVariable Long idCopy) {
        BookCopyDto bookCopyDto = bookCopyService.showOneCopy(idCopy);
        return ResponseEntity.ok(bookCopyDto);
    }


    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies() {
        List<BookCopyDto> bookCopyDtoList = bookCopyService.getAllBookCopies();
        return ResponseEntity.ok(bookCopyDtoList);
    }


    @GetMapping("/after")
    public ResponseEntity<List<BookCopyDto>> getAllBookCopiesPublishedAfter(@RequestParam LocalDate date) {
        return ResponseEntity.ok(bookCopyService.getAllBookCopiesPublishedAfter(date));
    }


    @GetMapping("/dyslexia")
    public ResponseEntity<List<BookCopyDto>> getAllBookCopiesDyslexiaFriendly(@RequestParam boolean dyslexia) {
        return ResponseEntity.ok(bookCopyService.getAllBookCopiesDyslexiaFriendly(dyslexia));
    }


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


    @DeleteMapping("/{idCopy}")
    public ResponseEntity<Object> deleteOneCopy(@PathVariable Long idCopy) {
        bookCopyService.deleteCopyById(idCopy);
        return ResponseEntity.noContent().build();
    }



    }






