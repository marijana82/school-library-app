package com.marijana.library1223.services;

import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.exceptions.RecordNotFoundException;
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


    public ReservationService(ReservationRepository reservationRepository,
                              BookService bookService,
                              BookRepository bookRepository,
                              AccountService accountService,
                              AccountRepository accountRepository) {

        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;

    }


    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setSidenote(reservationDto.getSidenote());
        reservationRepository.save(reservation);
        reservationDto.setId(reservation.getId());
        return reservationDto;
    }



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


    public ReservationDto getSingleReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if(optionalReservation.isPresent()) {
            Reservation reservationFound = optionalReservation.get();
            ReservationDto reservationDto = transferReservationToReservationDto(reservationFound);

            if(reservationFound.getBook() != null) {
                reservationDto.setBookDto(bookService.transferBookToBookDto(reservationFound.getBook()));
            }
            if(reservationFound.getAccount() !=null) {
                reservationDto.setAccountDto(accountService.transferAccountToAccountDto(reservationFound.getAccount()));
            }

            return reservationDto;

        } else {
            throw new RecordNotFoundException("Reservation has not been found.");
        }

        }


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


    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }



    //helper methods.........................................
    public ReservationDto transferReservationToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setReservationDate(reservation.getReservationDate());
        reservationDto.setSidenote(reservation.getSidenote());

        if(reservation.getBook() != null) {
            reservationDto.setBookDto(bookService.transferBookToBookDto(reservation.getBook()));
        }
        if(reservation.getAccount() !=null) {
            reservationDto.setAccountDto(accountService.transferAccountToAccountDto(reservation.getAccount()));
        }
        return reservationDto;
    }


    public Reservation transferReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setSidenote(reservationDto.getSidenote());
        reservation.setAccount(accountService.transferAccountDtoToAccount(reservationDto.getAccountDto()));
        reservation.setBook(bookService.transferBookDtoToBook(reservationDto.getBookDto()));

        return reservation;
    }


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
