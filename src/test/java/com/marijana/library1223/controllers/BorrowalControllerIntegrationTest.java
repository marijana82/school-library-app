package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.dtos.BookCopyDto;
import com.marijana.library1223.dtos.BorrowalDto;
import com.marijana.library1223.dtos.ReservationDto;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.models.BookCopy;
import com.marijana.library1223.models.Borrowal;
import com.marijana.library1223.models.Reservation;
import com.marijana.library1223.repositories.AccountRepository;
import com.marijana.library1223.repositories.BookCopyRepository;
import com.marijana.library1223.repositories.BorrowalRepository;
import com.marijana.library1223.repositories.ReservationRepository;
import com.marijana.library1223.services.BorrowalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class BorrowalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BorrowalService borrowalService;

    @Autowired
    private BorrowalRepository borrowalRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    Account account1;
    Account account2;
    BookCopy bookCopy1;
    BookCopy bookCopy2;
    Reservation reservation1;
    Reservation reservation2;
    Borrowal borrowal1;
    Borrowal borrowal2;

    AccountDto accountDto1;
    AccountDto accountDto2;
    BookCopyDto bookCopyDto1;
    BookCopyDto bookCopyDto2;
    ReservationDto reservationDto1;
    ReservationDto reservationDto2;
    BorrowalDto borrowalDto1;
    BorrowalDto borrowalDto2;


    @BeforeEach
    void setUp() {

        if(borrowalRepository.count() > 0) {
            borrowalRepository.deleteAll();
        }

        //account
        account1 = new Account();
        account2 = new Account();

        account1.setId(1000L);
        account2.setId(1001L);

        account1 = accountRepository.save(account1);
        account2 = accountRepository.save(account2);

        //accountDto
        accountDto1 = new AccountDto();
        accountDto2 = new AccountDto();

        accountDto1.setId(1000L);
        accountDto2.setId(1001L);


        //book copy
        bookCopy1 = new BookCopy();
        bookCopy2 = new BookCopy();

        bookCopy1.setId(1000L);
        bookCopy2.setId(1001L);

        bookCopy1 = bookCopyRepository.save(bookCopy1);
        bookCopy2 = bookCopyRepository.save(bookCopy2);

        //book copy dto
        bookCopyDto1 = new BookCopyDto();
        bookCopyDto2 = new BookCopyDto();

        bookCopyDto1.setId(1000L);
        bookCopyDto2.setId(1001L);

        //reservation
        reservation1 = new Reservation();
        reservation2 = new Reservation();

        reservation1.setId(1000L);
        reservation2.setId(1001L);

        reservation1 = reservationRepository.save(reservation1);
        reservation2 = reservationRepository.save(reservation2);

        //reservation dto
        reservationDto1 = new ReservationDto();
        reservationDto2 = new ReservationDto();

        reservationDto1.setId(1000L);
        reservationDto2.setId(1001L);


        //borrowal
        borrowal1 = new Borrowal();
        borrowal2 = new Borrowal();

        borrowal1.setId(1000L);
        borrowal1.setDateOfBorrowal(LocalDate.of(2024, 03, 03));
        borrowal1.setDueDate(LocalDate.of(2024, 04, 03));
        borrowal1.setNumberOfBooksBorrowed(1);
        borrowal1.setAccount(account1);
        borrowal1.setBookCopy(bookCopy1);
        borrowal1.setReservation(reservation1);

        borrowal2.setId(1001L);
        borrowal2.setDateOfBorrowal(LocalDate.of(2024, 04, 04));
        borrowal2.setDueDate(LocalDate.of(2024, 05, 04));
        borrowal2.setNumberOfBooksBorrowed(1);
        borrowal2.setAccount(account2);
        borrowal2.setBookCopy(bookCopy2);
        borrowal2.setReservation(reservation2);

        borrowal1 = borrowalRepository.save(borrowal1);
        borrowal2 = borrowalRepository.save(borrowal2);


        //borrowal dto
        borrowalDto1 = new BorrowalDto();
        borrowalDto2 = new BorrowalDto();

        borrowalDto1.setId(1000L);
        borrowalDto1.setDateOfBorrowal(LocalDate.of(2024, 03, 03));
        borrowalDto1.setDueDate(LocalDate.of(2024, 04, 03));
        borrowalDto1.setNumberOfBooksBorrowed(1);
        borrowalDto1.setAccountDto(accountDto1);
        borrowalDto1.setBookCopyDto(bookCopyDto1);
        borrowalDto1.setReservationDto(reservationDto1);

        borrowalDto2.setId(1001L);
        borrowalDto2.setDateOfBorrowal(LocalDate.of(2024, 04, 04));
        borrowalDto2.setDueDate(LocalDate.of(2024, 05, 04));
        borrowalDto2.setNumberOfBooksBorrowed(1);
        borrowalDto2.setAccountDto(accountDto2);
        borrowalDto2.setBookCopyDto(bookCopyDto2);
        borrowalDto2.setReservationDto(reservationDto2);

    }

    @Test
    @DisplayName("Should create new borrowal")
    void createNewBorrowal() {
    }

    @Test
    @DisplayName("Should get all borrowals")
    void getAllBorrowals() {
    }

    @Test
    @DisplayName("Should get single borrowal")
    void getSingleBorrowal() {
    }

    @Test
    @DisplayName("Should do full update borrowal")
    void fullUpdateBorrowal() {
    }

    @Test
    @DisplayName("Should assign book copy to borrowal")
    void assignBookCopyToBorrowal() {
    }

    @Test
    @DisplayName("Should assign reservation to borrowal")
    void assignReservationToBorrowal() {
    }

    @Test
    @DisplayName("Should assign account to borrowal")
    void assignAccountToBorrowal() {
    }

    @Test
    @DisplayName("Should do partial update borrowal")
    void partialUpdateBorrowal() {
    }

    @Test
    @DisplayName("Should delete borrowal")
    void deleteBorrowal() {
    }
}