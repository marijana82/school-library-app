package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.*;
import com.marijana.library1223.dtosoutput.BookOutputDto;
import com.marijana.library1223.services.AuthorBookService;
import com.marijana.library1223.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorBookService authorBookService;

    public BookController(BookService bookService, AuthorBookService authorBookService) {
        this.bookService = bookService;
        this.authorBookService = authorBookService;
    }


    //post mapping books
    @PostMapping
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookDto bookDto, BindingResult bindingResult) {
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


    //get mapping all + name Author + name Illustrator
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(value="nameAuthor", required=false) Optional<String> nameAuthor,
            @RequestParam(value="nameIllustrator", required=false) Optional<String> nameIllustrator) {

        List<BookDto> bookDtoList;

            //no parameters present
        if(nameAuthor.isEmpty() && nameIllustrator.isEmpty() ) {
            bookDtoList = bookService.showAllBooks();

            //only nameAuthor parameter is present
        } else if(nameAuthor.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameAuthor(nameAuthor.get());

            //only nameIllustrator parameter is present
        } else if(nameIllustrator.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameIllustrator(nameIllustrator.get());

            //both parameters are present
        } else {
            bookDtoList = bookService.showAllBooksByNameIllustratorAndNameAuthor(nameIllustrator.get(), nameAuthor.get());
        }
        return ResponseEntity.ok().body(bookDtoList);
    }

    //get mapping all + current topic
    @GetMapping("/topics")
    public ResponseEntity<List<InformationBookDto>> getAllBooksByTopic(@RequestParam String currentTopic) {
        return ResponseEntity.ok(bookService.showAllBooksByTopic(currentTopic));
    }


    //delete one
    @DeleteMapping("/{idBook}")
    public ResponseEntity<Object> deleteOneBook(@PathVariable Long idBook) {
        bookService.deleteById(idBook);
        return ResponseEntity.noContent().build();
    }

    //update all
    @PutMapping("/{idBook}")
    public ResponseEntity<BookDto> fullUpdateBook(@PathVariable Long idBook, @Valid @RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateOneBook(idBook, bookDto);
        return ResponseEntity.ok().body(bookDto1);
    }


    //update partially
    @PatchMapping("/{idBook}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable Long idBook, @Valid @RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateBookPartially(idBook, bookDto);
        return ResponseEntity.ok().body(bookDto1);
    }

    //method that gets all authors connected to a certain book
    //makes use of authorBookService
    @GetMapping("/authors/{idBook}")
    public ResponseEntity<Collection<AuthorDto>> getAuthorsByIdBook(@PathVariable("idBook") Long idBook) {
        return ResponseEntity.ok(authorBookService.getAuthorsByIdBook(idBook));


    }


}
