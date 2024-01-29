package com.marijana.library1223.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marijana.library1223.dtos.AccountDto;
import com.marijana.library1223.filter.JwtRequestFilter;
import com.marijana.library1223.models.Account;
import com.marijana.library1223.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    AccountService accountService;


    @BeforeEach
    void setUp() {

        Account account1;
        Account account2;
        AccountDto accountDto1;
        AccountDto accountDto2;

        account1 = new Account();
        account1.setId(1000L);
        account1.setFirstNameStudent("Student FirstName 1");
        account1.setLastNameStudent("Student LastName 1");
        account1.setDob(LocalDate.of(2018, 05, 05));
        account1.setNameOfTeacher("Teacher 1");
        account1.setStudentClass("OBB");

        account2 = new Account();
        account2.setId(1001L);
        account2.setFirstNameStudent("Student FirstName 2");
        account2.setLastNameStudent("Student LastName 2");
        account2.setDob(LocalDate.of(2015, 02, 24));
        account1.setNameOfTeacher("Teacher 1");
        account1.setStudentClass("MBE");

        accountDto1 = new AccountDto();
        accountDto1.setId(1000L);
        accountDto1.setFirstNameStudent("Student FirstName 1");
        accountDto1.setLastNameStudent("Student LastName 1");
        accountDto1.setDob(LocalDate.of(2018, 05, 05));
        accountDto1.setNameOfTeacher("Teacher 1");
        accountDto1.setStudentClass("OBB");

        accountDto2 = new AccountDto();
        accountDto2.setId(1000L);
        accountDto2.setFirstNameStudent("Student FirstName 1");
        accountDto2.setLastNameStudent("Student LastName 1");
        accountDto2.setDob(LocalDate.of(2018, 02, 24));
        accountDto2.setNameOfTeacher("Teacher 1");
        accountDto2.setStudentClass("OBB");
    }


    @Test
    @DisplayName("Should create new account")
    void createNewAccount() throws Exception {

        AccountDto accountDto1;
        accountDto1 = new AccountDto();
        accountDto1.setId(1000L);
        accountDto1.setFirstNameStudent("Student FirstName 1");
        accountDto1.setLastNameStudent("Student LastName 1");
        accountDto1.setDob(LocalDate.of(2018, 05, 05));
        accountDto1.setNameOfTeacher("Teacher 1");
        accountDto1.setStudentClass("OBB");

        Mockito.when(accountService.createAccount(accountDto1)).thenReturn(accountDto1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accountDto1)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1000)))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.firstNameStudent", is("Student FirstName 1")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.lastNameStudent", is("Student LastName 1")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.dob", is(accountDto1.getDob().toString())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.nameOfTeacher", is("Teacher 1")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.studentClass", is("OBB")));
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void getOneAccount() {
    }

    @Test
    void fullUpdateAccount() {
    }

    @Test
    void partialUpdateAccount() {
    }

    @Test
    void deleteOneAccount() {
    }

    @Test
    void assignUserToAccount() {
    }


    //(de)serialization of json.........
    public static String asJsonString(final Object obj) {
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