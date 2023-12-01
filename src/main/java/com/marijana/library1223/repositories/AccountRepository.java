package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //add a method that searches students by date of birth
    List<Account> findByDob(LocalDate dob);

    //add a method that checks if first name and last name already exist
    boolean existsByFirstNameStudentIgnoreCaseAndLastNameStudentIgnoreCase(String firstNameStudent, String lastNameStudent);




}
