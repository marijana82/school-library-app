package com.marijana.library1223.services;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
import com.marijana.library1223.exceptions.UsernameNotFoundException;
import com.marijana.library1223.exceptions.UsernameNotProvidedException;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;


    public ReservationService(ReservationRepository reservationRepository, BookService bookService, BookRepository bookRepository, AccountService accountService, AccountRepository accountRepository) {
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;

    }

    //createReservation - post mapping
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setBookTitle(reservationDto.getBookTitle());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setSidenote(reservationDto.getSidenote());
        reservationRepository.save(reservation);
        reservationDto.setId(reservation.getId());
        return reservationDto;
    }


    //get all reservations - get mapping
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservationList) {

            ReservationDto reservationDto = transferReservationToReservationDto(reservation);

            if(reservation.getAccount() !=null) {
                reservationDto.setAccountDto(accountService.transferAccountToAccountDto(reservation.getAccount()));
            }
            if(reservation.getBook() !=null) {
                reservationDto.setBookDto(bookService.transferBookToBookDto(reservation.getBook()));
            }

            reservationDtoList.add(reservationDto);
        }
        return reservationDtoList;
    }

    //get all reservations per date
    public List<ReservationDto> showAllReservationsByReservationDate(LocalDate reservationDate) {
        List<Reservation> reservationList = reservationRepository.findAllReservationsByReservationDate(reservationDate);
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservationList) {
            ReservationDto reservationDto = transferReservationToReservationDto(reservation);
            if(reservation.getAccount() !=null) {
                reservationDto.setAccountDto(accountService.transferAccountToAccountDto(reservation.getAccount()));
            }
            if(reservation.getBook() !=null) {
                reservationDto.setBookDto(bookService.transferBookToBookDto(reservation.getBook()));
            }
            reservationDtoList.add(reservationDto);
        }
        return reservationDtoList;
    }


    //get single reservation - get mapping (id)
    public ReservationDto getSingleReservation(Long id, String username) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        //delete if not necessary
        if (username == null) {
            throw new UsernameNotProvidedException("Please provide a username.");
        } else {

        if(optionalReservation.isPresent()) {
            Reservation reservationFound = optionalReservation.get();
            ReservationDto reservationDto = transferReservationToReservationDto(reservationFound);

            if(reservationFound.getBook() != null) {
                reservationDto.setBookDto(bookService.transferBookToBookDto(reservationFound.getBook()));
            }
            if(reservationFound.getAccount() !=null) {
                reservationDto.setAccountDto((accountService.transferAccountToAccountDto(reservationFound.getAccount())));
            }

            return reservationDto;

        } else {
            throw new RecordNotFoundException("Reservation has not been found.");
        }
        //this } belongs to delete if not necessary
        }
    }

    //put
    public ReservationDto fullUpdateReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isEmpty()) {
            throw new RecordNotFoundException("Reservation with id number " + id + " has not been found.");
        } else {
            Reservation reservation = optionalReservation.get();
            Reservation updatedReservation = transferReservationDtoToReservation(reservationDto);
            updatedReservation.setId(reservation.getId());
            reservationRepository.save(updatedReservation);
            return transferReservationToReservationDto(updatedReservation);
        }
    }

    //TODO: CREATE PATCH METHOD
    //patch

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }






    //helper methods.........................................

    //helper method - transferReservationToReservationDto
    public ReservationDto transferReservationToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setBookTitle(reservation.getBookTitle());
        reservationDto.setReservationDate(reservation.getReservationDate());
        reservationDto.setSidenote(reservation.getSidenote());
        //null-check
        if(reservation.getBook() != null) {
            reservationDto.setBookDto(bookService.transferBookToBookDto(reservation.getBook()));
        }
        if(reservation.getAccount() !=null) {
            reservationDto.setAccountDto(accountService.transferAccountToAccountDto(reservation.getAccount()));
        }
        return reservationDto;
    }

    //helper method - transferReservationDtoToReservation
    public Reservation transferReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setBookTitle(reservationDto.getBookTitle());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setSidenote(reservationDto.getSidenote());
        //TODO: CHECK IF THIS IS NECESSARY:
        reservation.setAccount(accountService.transferAccountDtoToAccount(reservationDto.getAccountDto()));
        reservation.setBook(bookService.transferBookDtoToBook(reservationDto.getBookDto()));

        return reservation;
    }

    //assign book to reservation
    public void assignBookToReservation(Long idBook, Long idReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        Optional<Book> optionalBook = bookRepository.findById(idBook);

        if(optionalReservation.isPresent() && optionalBook.isPresent()) {
            Reservation reservationIsPresent = optionalReservation.get();
            Book bookIsPresent = optionalBook.get();

            reservationIsPresent.setBook(bookIsPresent);
            reservationRepository.save(reservationIsPresent);
        } else {
            throw new RecordNotFoundException("Reservation not found.");
        }
    }

    //assign account to reservation
    public void assignAccountToReservation(Long idAccount, Long idReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        Optional<Account> optionalAccount = accountRepository.findById(idAccount);

        if(optionalReservation.isPresent() && optionalAccount.isPresent()) {
            Reservation reservationIsPresent = optionalReservation.get();
            Account accountIsPresent = optionalAccount.get();

            reservationIsPresent.setAccount(accountIsPresent);
            reservationRepository.save(reservationIsPresent);
        } else {
            throw new RecordNotFoundException("Reservation not found.");
        }
    }




}
