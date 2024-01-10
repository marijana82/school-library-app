package com.marijana.library1223.controllers;

//only used for the "in between class" AuthorBook
//it only contains post-method to add review to book
//no get method because this is only backend implementation, 
// user is not aware of its existence

import com.marijana.library1223.models.ReviewBookKey;
import com.marijana.library1223.services.ReviewBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews-books")
public class ReviewBookController {

    private final ReviewBookService reviewBookService;

    public ReviewBookController(ReviewBookService reviewBookService) {
        this.reviewBookService = reviewBookService;
    }

    @PostMapping("/{idReview}/{idBook}")
    public ResponseEntity<ReviewBookKey> addReviewBook(@PathVariable("idReview") Long idReview, @PathVariable("idBook") Long idBook) {
        ReviewBookKey key = reviewBookService.addReviewBook(idReview, idBook);
        return ResponseEntity.created(null).body(key);
    }



}
