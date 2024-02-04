package com.marijana.library1223.controllers;

import com.marijana.library1223.configuration.HandleBindingErrors;
import com.marijana.library1223.dtos.*;
import com.marijana.library1223.fileUploadResponse.FileUploadResponse;
import com.marijana.library1223.services.BookService;
import com.marijana.library1223.services.FileStorageService;
import com.marijana.library1223.services.ReviewBookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ReviewBookService reviewBookService;
    private final FileStorageService fileStorageService;
    private final FileUploadController fileUploadController;

    public BookController(BookService bookService, ReviewBookService reviewBookService, FileStorageService fileStorageService, FileUploadController fileUploadController) {
        this.bookService = bookService;
        this.reviewBookService = reviewBookService;
        this.fileStorageService = fileStorageService;
        this.fileUploadController = fileUploadController;
    }

    @PostMapping
    public ResponseEntity<Object> createBook(
            @Valid @RequestBody BookDto bookDto,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return HandleBindingErrors.handleBindingErrors(bindingResult);

        } else {

            bookService.createNewBook(bookDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + bookDto.getId())
                    .toUriString());
            return ResponseEntity.created(uri).body(bookDto);

        }
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

        if(nameAuthor.isEmpty() && nameIllustrator.isEmpty() ) {
            bookDtoList = bookService.showAllBooks();

        } else if(nameAuthor.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameAuthor(nameAuthor.get());

        } else if(nameIllustrator.isPresent()) {
            bookDtoList = bookService.showAllBooksByNameIllustrator(nameIllustrator.get());

        } else {
            bookDtoList = bookService.showAllBooksByNameIllustratorAndNameAuthor(nameIllustrator.get(), nameAuthor.get());
        }
        return ResponseEntity.ok().body(bookDtoList);
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
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable Long idBook, @RequestBody BookDto bookDto) {
        BookDto bookDto1 = bookService.updateBookPartially(idBook, bookDto);
        return ResponseEntity.ok().body(bookDto1);
    }


    @GetMapping("/reviews/{idBook}")
    public ResponseEntity<Collection<ReviewDto>> getReviewsByIdBook(@PathVariable("idBook") Long idBook) {
        return ResponseEntity.ok(reviewBookService.getReviewsByIdBook(idBook));
    }


    @PutMapping("/{idBook}/photo")
    public ResponseEntity<Object> assignPhotoToBook(@PathVariable("idBook") Long idBook, @RequestBody MultipartFile file) {

        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        bookService.assignPhotoToBook(idBook, photo.getFileName());

        return ResponseEntity.ok(photo.getUrl());

    }


}
