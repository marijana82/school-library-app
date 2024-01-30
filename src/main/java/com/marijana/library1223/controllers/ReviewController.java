package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.ReviewDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Review;
import com.marijana.library1223.repositories.ReviewRepository;
import com.marijana.library1223.services.ReviewBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController("/books-reviews")
public class ReviewController {


    private final ReviewBookService reviewBookService;

    public ReviewController(ReviewBookService reviewBookService) {
        this.reviewBookService = reviewBookService;
    }





    //TODO: CHECK below
    @GetMapping("/book/{idReview}")
    public ResponseEntity<Collection<BookDto>> getBooksByIdReview(@PathVariable("idReview") Long idReview) {
        return ResponseEntity.ok(reviewBookService.getBooksByIdReview(idReview));
    }




}
