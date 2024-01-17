package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.ReviewDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.Review;
import com.marijana.library1223.models.ReviewBook;
import com.marijana.library1223.models.ReviewBookKey;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.ReviewBookRepository;
import com.marijana.library1223.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ReviewBookService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewBookRepository reviewBookRepository;

    public ReviewBookService(
            ReviewRepository reviewRepository,
            BookRepository bookRepository,
            ReviewBookRepository reviewBookRepository) {

        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.reviewBookRepository = reviewBookRepository;
    }


    public Collection<ReviewDto> getReviewsByIdBook(Long idBook) {
        Collection<ReviewDto> reviewDtos = new HashSet<>();
        Collection<ReviewBook> reviewBooks = reviewBookRepository.findAllByBookId(idBook);
        for(ReviewBook reviewBook : reviewBooks) {
            Review review = reviewBook.getReview();
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReview(review.getReview());
            reviewDto.setId(review.getId());
            reviewDto.setName(review.getName());
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }


    public Collection<BookDto> getBooksByIdReview(Long idReview) {

        Set<BookDto> bookSetDtos = new HashSet<>();
        List<ReviewBook> reviewBookList = reviewBookRepository.findAllByReviewId(idReview);
        for(ReviewBook reviewBook : reviewBookList) {
            Book book = reviewBook.getBook();
            BookDto bookDto = new BookDto();
            bookDto.setBookTitle(book.getBookTitle());
            bookDto.setId(book.getId());
            bookDto.setNameAuthor(book.getNameAuthor());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setNameIllustrator(book.getNameIllustrator());
            bookDto.setSuitableAge(book.getSuitableAge());
            bookDto.setInformationBook(book.getInformationBook());
            bookDto.setReadingBook(book.getReadingBook());
            //bookDto.setReservationDto(book.getReservation());
            //bookDto.setReservationId();
            //bookDto.setBookCopyList(book.getBookCopyList());
            bookSetDtos.add(bookDto);
        }
        return bookSetDtos;
    }


    public ReviewBookKey addReviewBook(Long idReview, Long idBook) {
        ReviewBook reviewBook = new ReviewBook();

        if(!reviewRepository.existsById(idReview)) {
            throw new RecordNotFoundException();
        }
        Review review = reviewRepository.findById(idReview).orElse(null);

        if(!bookRepository.existsById(idBook)) {
            throw new RecordNotFoundException();
        }
        Book book = bookRepository.findById(idBook).orElse(null);

        reviewBook.setReview(review);
        reviewBook.setBook(book);

        ReviewBookKey id = new ReviewBookKey();
        id.setReviewId(idReview);
        id.setBookId(idBook);

        reviewBook.setId(id);

        reviewBookRepository.save(reviewBook);

        return id;


    }




}
