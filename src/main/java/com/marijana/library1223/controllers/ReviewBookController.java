package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.ReviewDto;
import com.marijana.library1223.models.ReviewBookKey;
import com.marijana.library1223.services.ReviewBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reviews")
public class ReviewBookController {

    private final ReviewBookService reviewBookService;

    public ReviewBookController(ReviewBookService reviewBookService) {
        this.reviewBookService = reviewBookService;
    }


    @PostMapping
    public ResponseEntity<Object> createNewReview(@RequestBody ReviewDto reviewDto) {
        reviewBookService.createNewReview(reviewDto);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{idReview}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable Long idReview) {
        ReviewDto reviewDto = reviewBookService.showOneReview(idReview);
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping("/{idReview}/{idBook}")
    public ResponseEntity<ReviewBookKey> addReviewBook(
            @PathVariable("idReview") Long idReview,
            @PathVariable("idBook") Long idBook) {
        ReviewBookKey key = reviewBookService.addReviewBook(idReview, idBook);
        return ResponseEntity.created(null).body(key);
    }



}
