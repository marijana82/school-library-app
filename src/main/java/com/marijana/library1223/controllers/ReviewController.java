package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.services.ReviewBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController("/books-reviews")
public class ReviewController {


    private final ReviewBookService reviewBookService;

    public ReviewController(ReviewBookService reviewBookService) {
        this.reviewBookService = reviewBookService;
    }


    @GetMapping("/book/{idReview}")
    public ResponseEntity<Collection<BookDto>> getBooksByIdReview(@PathVariable("idReview") Long idReview) {
        return ResponseEntity.ok(reviewBookService.getBooksByIdReview(idReview));
    }




}
