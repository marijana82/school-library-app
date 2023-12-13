package com.marijana.library1223.services;

import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BookDto;
import com.marijana.library1223.dtos.InformationBookDto;
import com.marijana.library1223.exceptions.IdNotFoundException;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.models.*;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookService(BookRepository bookRepository, ReservationRepository reservationRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
        this.bookCopyRepository = bookCopyRepository;
    }


    //createNewBook method
    public BookDto createNewBook(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());

        //check if it exists & connect book and reservation objects
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookDto.reservationId);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            book.setReservation(reservation);
        }

        //create and set values for InformationBook
        InformationBook informationBook = new InformationBook();
        if(bookDto.getInformationBook() != null) {
            informationBook.setEducationLevel(bookDto.getInformationBook().getEducationLevel());
            informationBook.setCurrentTopic(bookDto.getInformationBook().getCurrentTopic());
        }
        //set InformationBook in the Book entity
        book.setInformationBook(informationBook);

        //create and set values for ReadingBook
        ReadingBook readingBook = new ReadingBook();
        if(bookDto.getReadingBook() != null) {
            readingBook.setCurrentGenre(bookDto.getReadingBook().getCurrentGenre());
            readingBook.setReadingLevel(bookDto.getReadingBook().getReadingLevel());
            readingBook.setLanguage(bookDto.getReadingBook().getLanguage());
        }
        //set ReadingBook in the Book entity
        book.setReadingBook(readingBook);

        bookRepository.save(book);
        bookDto.setId(book.getId());
        return bookDto;
    }



    //showOneBook method
    public BookDto showOneBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            Book requestedBook = optionalBook.get();
            return transferBookToBookDto(requestedBook);
        } else {
            throw new RecordNotFoundException("Book with id number " + id + " has not been found.");
        }
    }

    //showAllBooks
    public List<BookDto> showAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    //showAllBooksByNameAuthor
    public List<BookDto> showAllBooksByNameAuthor(String nameAuthor) {
        List<Book> bookList = bookRepository.findAllBooksByNameAuthorEqualsIgnoreCase(nameAuthor);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    //showAllBooksByNameIllustrator
    public List<BookDto> showAllBooksByNameIllustrator(String nameIllustrator) {
        List<Book> bookList = bookRepository.findAllBooksByNameIllustratorEqualsIgnoreCase(nameIllustrator);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }

    //showAllBooksByNameIllustratorAndNameAuthor
    public List<BookDto> showAllBooksByNameIllustratorAndNameAuthor(String nameIllustrator, String nameAuthor) {
        List<Book> bookList = bookRepository.findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase(nameIllustrator, nameAuthor);
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            BookDto bookDto = transferBookToBookDto(book);
            bookDtoList.add(bookDto);
        }
        return bookDtoList;
    }


    public List<InformationBookDto> showAllBooksByTopic(String topic) {
        List<Book> bookList = bookRepository.findAllBooksByCurrentTopic(topic);
        List<InformationBookDto> informationBookDtoList = new ArrayList<>();
        for(Book book : bookList) {
            InformationBookDto informationBookDto = transferBookToInformationBookDto(book);
            informationBookDtoList.add(informationBookDto);
        }
        return informationBookDtoList;
    }


    //deleteById
    public String deleteById(Long id) {
        if(bookRepository.existsById(id)) {
            Optional<Book> bookFound = bookRepository.findById(id);
            Book bookToDelete = bookFound.get();
            bookRepository.delete(bookToDelete);
            return "Book with id number " + id + " has been successfully deleted.";
        } else {
            throw new IdNotFoundException("Book with id number " + id + " has not been found.");
        }

    }

    //updateOneBook
    public BookDto updateOneBook(Long id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()) {
            throw new IdNotFoundException("Book cannot be updated as it does not exist in the database.");
        } else {
            Book book = optionalBook.get();
            Book updatedBook = transferBookDtoToBook(bookDto);
            updatedBook.setId(book.getId());
            bookRepository.save(updatedBook);
            return transferBookToBookDto(updatedBook);

        }
    }


    //updateBookPartially
    public BookDto updateBookPartially(Long id, BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(bookOptional.isEmpty()) {
            throw new IdNotFoundException("Book with id " + id + " cannot be updated.");
        } else {

            Book bookToUpdate = bookOptional.get();
            Book book1 = transferBookDtoToBook(bookDto);
            book1.setId(bookToUpdate.getId());

            if(bookDto.getIsbn() !=-1) {
                book1.setIsbn(bookDto.getIsbn());
            }
            if(bookDto.getBookTitle() !=null) {
                book1.setBookTitle(bookDto.getBookTitle());
            }
            if(bookDto.getNameAuthor() !=null) {
                book1.setNameAuthor(bookDto.getNameAuthor());
            }
            if(bookDto.getNameIllustrator() !=null) {
                book1.setNameIllustrator(bookDto.getNameIllustrator());
            }

            if(bookDto.getSuitableAge() !=-1) {
                book1.setSuitableAge(bookDto.getSuitableAge());
            }

            Book returnBook = bookRepository.save(book1);
            return transferBookToBookDto(returnBook);

        }

    }





    //helper methods ...........................................


    //helper transfer book copy dto to book copy
    private BookCopyDto transferBookCopyToBookCopyDto(BookCopy bookCopy) {
        BookCopyDto bookCopyDto = new BookCopyDto();
        bookCopyDto.setAudioBook(bookCopy.isAudioBook());
        bookCopyDto.setDyslexiaFriendly(bookCopy.isDyslexiaFriendly());
        bookCopyDto.setInWrittenForm(bookCopy.isInWrittenForm());
        bookCopyDto.setFormat(bookCopy.getFormat());
        bookCopyDto.setBarcode(bookCopy.getBarcode());
        bookCopyDto.setTotalWordCount(bookCopy.getTotalWordCount());
        bookCopyDto.setNumberOfPages(bookCopy.getNumberOfPages());
        bookCopyDto.setId(bookCopy.getId());
        return bookCopyDto;
    }

    private BookCopy transferBookCopyDtoToBookCopy(BookCopyDto bookCopyDto) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setAudioBook(bookCopyDto.isAudioBook());
        bookCopy.setDyslexiaFriendly(bookCopyDto.isDyslexiaFriendly());
        bookCopy.setInWrittenForm(bookCopyDto.isInWrittenForm());
        bookCopy.setBarcode(bookCopyDto.getBarcode());
        bookCopy.setFormat(bookCopyDto.getFormat());
        bookCopy.setNumberOfPages(bookCopyDto.getNumberOfPages());
        bookCopy.setTotalWordCount(bookCopyDto.getTotalWordCount());
        bookCopy.setId(bookCopyDto.getId());
        bookCopy.setYearPublished(bookCopyDto.getYearPublished());
        return bookCopy;
    }


    //helper method - transfer Book to BookDto
    private BookDto transferBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setNameAuthor(book.getNameAuthor());
        bookDto.setNameIllustrator(book.getNameIllustrator());
        bookDto.setSuitableAge(book.getSuitableAge());
        return bookDto;
    }


    //helper method - transfer BookDto to Book
    private Book transferBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setBookTitle(bookDto.getBookTitle());
        book.setNameAuthor(bookDto.getNameAuthor());
        book.setNameIllustrator(bookDto.getNameIllustrator());
        book.setSuitableAge(bookDto.getSuitableAge());
        return book;
    }


    //helper method - transfer InformationBookDto To Book ?????
    private Book transferInformationBookDtoToBook(InformationBookDto informationBookDto) {
        Book book = new Book();
        InformationBook informationBook = new InformationBook();
        informationBook.setCurrentTopic(book.getInformationBook().getCurrentTopic());
        informationBook.setEducationLevel(book.getInformationBook().getEducationLevel());
        book.setInformationBook(informationBook);
        return book;
    }

    //helper method - transfer Book To InformationBookDto  ?????
    private InformationBookDto transferBookToInformationBookDto(Book book) {
        InformationBookDto informationBookDto = new InformationBookDto();
        InformationBook informationBook = book.getInformationBook();
        if(informationBook != null) {
            informationBookDto.setCurrentTopic(informationBook.getCurrentTopic());
            informationBookDto.setEducationLevel(informationBook.getEducationLevel());
        }
        return informationBookDto;
    }

    //helper method - transferInformationBookToInformationBookDto ???
    private InformationBook transferInformationBookDtoToInformationBook(InformationBookDto informationBookDto) {
        InformationBook informationBook = new InformationBook();
        informationBook.setEducationLevel(informationBookDto.getEducationLevel());
        informationBook.setCurrentTopic(informationBookDto.getCurrentTopic());
        return informationBook;
    }

}
