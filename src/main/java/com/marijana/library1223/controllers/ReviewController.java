package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.services.ReviewBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/reviews")
public class ReviewController {


    private final ReviewBookService reviewBookService;

    public ReviewController(ReviewBookService reviewBookService) {

        this.reviewBookService = reviewBookService;
    }

    //TODO: implement post method addReviewToBook in order to make this one work
    @GetMapping("/books/{idReview}")
    public ResponseEntity<Collection<BookDto>> getBooksByIdReview(@PathVariable("idReview") Long idReview) {
        return ResponseEntity.ok(reviewBookService.getBooksByIdReview(idReview));
    }
}
