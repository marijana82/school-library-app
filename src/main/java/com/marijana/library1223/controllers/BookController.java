package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //post mapping
    @PostMapping
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
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

        bookService.createNewBook(bookDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + bookDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(bookDto);
    }


    //get mapping one
    @GetMapping("/{idBook}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable Long idBook) {
        BookDto bookDto = bookService.showOneBook(idBook);
        return ResponseEntity.ok(bookDto);
    }


    //get mapping all + name Author
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(value="nameAuthor", required=false) Optional<String> nameAuthor) {
        List<BookDto> bookDtoList;

        if(nameAuthor.isEmpty()) {
            bookDtoList = bookService.showAllBooks();
        } else {
            bookDtoList = bookService.showAllBooksByNameAuthor(nameAuthor.get());
        }
        return ResponseEntity.ok().body(bookDtoList);
    }



    //delete one
    @DeleteMapping("/{idBook}")
    public ResponseEntity<Object> deleteOneBook(@PathVariable Long idBook) {
        bookService.deleteById(idBook);
        return ResponseEntity.noContent().build();
    }






}
