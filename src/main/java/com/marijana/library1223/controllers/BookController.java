package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.*;
import com.marijana.library1223.services.BookService;
import com.marijana.library1223.services.ReviewBookService;
import jakarta.validation.Valid;
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
    private final ReviewBookService reviewBookService;

    public BookController(BookService bookService, ReviewBookService reviewBookService) {
        this.bookService = bookService;
        this.reviewBookService = reviewBookService;
    }

    @PostMapping
    public ResponseEntity<Object> createBook(
            @Valid @RequestBody BookDto bookDto,
            BindingResult bindingResult) {

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


    @GetMapping("/{idBook}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable Long idBook) {
        BookDto bookDto = bookService.showOneBook(idBook);
        return ResponseEntity.ok(bookDto);
    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(value="nameAuthor", required=false) Optional<String> nameAuthor,
            @RequestParam(value="nameIllustrator", required=false) Optional<String> nameIllustrator) {

        List<BookDto> bookDtoList;

            //---no parameters
        if(nameAuthor.isEmpty() && nameIllustrator.isEmpty() ) {
            bookDtoList = bookService.showAllBooks();

            //---only nameAuthor present
        } else if(nameAuthor.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameAuthor(nameAuthor.get());

            //---only nameIllustrator present
        } else if(nameIllustrator.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameIllustrator(nameIllustrator.get());

            //---both parameters present
        } else {
            bookDtoList = bookService.showAllBooksByNameIllustratorAndNameAuthor(nameIllustrator.get(), nameAuthor.get());
        }
        return ResponseEntity.ok().body(bookDtoList);
    }

    @GetMapping("/topics")
    public ResponseEntity<List<InformationBookDto>> getAllBooksByTopic(@RequestParam String currentTopic) {
        return ResponseEntity.ok(bookService.showAllBooksByTopic(currentTopic));
    }


    @DeleteMapping("/{idBook}")
    public ResponseEntity<Object> deleteOneBook(@PathVariable Long idBook) {
        bookService.deleteById(idBook);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idBook}")
    public ResponseEntity<BookDto> fullUpdateBook(@PathVariable Long idBook, @Valid @RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateOneBook(idBook, bookDto);
        return ResponseEntity.ok().body(bookDto1);
    }


    @PatchMapping("/{idBook}")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable Long idBook, @Valid @RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateBookPartially(idBook, bookDto);
        return ResponseEntity.ok().body(bookDto1);
    }

    //all reviews connected to a certain book
    @GetMapping("/reviews/{idBook}")
    public ResponseEntity<Collection<ReviewDto>> getReviewsByIdBook(@PathVariable("idBook") Long idBook) {
        return ResponseEntity.ok(reviewBookService.getReviewsByIdBook(idBook));
    }


}
