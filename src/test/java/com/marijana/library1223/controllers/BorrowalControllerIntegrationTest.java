package com.marijana.library1223.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class BorrowalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    BorrowalDto borrowalUpdate;


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
        borrowalUpdate = new BorrowalDto();

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

        borrowalUpdate.setId(1000L);
        borrowalUpdate.setDateOfBorrowal(LocalDate.of(2025, 03, 03));
        borrowalUpdate.setDueDate(LocalDate.of(2025, 04, 03));
        borrowalUpdate.setNumberOfBooksBorrowed(1);
        borrowalUpdate.setAccountDto(accountDto1);
        borrowalUpdate.setBookCopyDto(bookCopyDto1);
        borrowalUpdate.setReservationDto(reservationDto1);

    }

    @Test
    @DisplayName("Should create new borrowal")
    void createNewBorrowal() throws Exception {

        given(borrowalService.createBorrowal(borrowalDto1)).willReturn(borrowalDto1);

        mockMvc.perform(MockMvcRequestBuilders.post("/borrowals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(borrowalDto1)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1000));
    }


    @Test
    @DisplayName("Should create new borrowal with binding result errors")
    void createNewBorrowalWithBindingResultErrors() throws Exception {

        BorrowalDto errorBorrowalDto = new BorrowalDto();
        errorBorrowalDto.setDateOfBorrowal(LocalDate.of(2023, 01, 01));

        mockMvc.perform(MockMvcRequestBuilders.post("/borrowals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(errorBorrowalDto)))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("dateOfBorrowal : must be a date in the present or in the future")));
    }



    @Test
    @DisplayName("Should get all borrowals")
    void getAllBorrowals() throws Exception {
        given(borrowalService.getAllBorrowals()).willReturn(List.of(borrowalDto1, borrowalDto2));

        mockMvc.perform(get("/borrowals"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBorrowal").value("2024-03-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dueDate").value("2024-04-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfBooksBorrowed").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountDto.id").value(accountDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookCopyDto.id").value(bookCopyDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservationDto.id").value(reservationDto1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBorrowal").value("2024-04-04"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dueDate").value("2024-05-04"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numberOfBooksBorrowed").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].accountDto.id").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookCopyDto.id").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].reservationDto.id").value(1001));
    }


    @Test
    @DisplayName("Should get single borrowal")
    void getSingleBorrowal() throws Exception {

        given(borrowalService.getSingleBorrowal(1000L)).willReturn(borrowalDto1);

        mockMvc.perform(get("/borrowals/1000"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBorrowal").value("2024-03-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("dueDate").value("2024-04-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberOfBooksBorrowed").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("accountDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("bookCopyDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("reservationDto.id").value(1000));
    }


    @Test
    @DisplayName("Should do full update borrowal")
    void fullUpdateBorrowal() throws Exception {

        given(borrowalService.fullUpdateBorrowal(1000L, borrowalDto1)).willReturn(borrowalUpdate);

        mockMvc.perform(put("/borrowals/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(borrowalDto1)))
                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBorrowal").value("2025-03-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("dueDate").value("2025-04-03"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberOfBooksBorrowed").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("accountDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("bookCopyDto.id").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("reservationDto.id").value(1000));
    }


    @Test
    @DisplayName("Should do partial update borrowal")
    void partialUpdateBorrowal() {
    }

    @Test
    @DisplayName("Should delete borrowal")
    void deleteBorrowal() throws Exception {
        mockMvc.perform(delete("/borrowals/1000"))
                .andExpect(status().isNoContent());

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



    //(de)serialization of json.........
    public static String asJsonString(final BorrowalDto obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
}

}