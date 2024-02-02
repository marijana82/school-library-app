package com.marijana.library1223.repositories;

import com.marijana.library1223.models.ReviewBook;
import com.marijana.library1223.models.ReviewBookKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewBookRepository extends JpaRepository<ReviewBook, ReviewBookKey> {

    List<ReviewBook> findAllByReviewId(Long idReview);

    List<ReviewBook> findAllByBookId(Long idBook);

}
