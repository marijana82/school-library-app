package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllAccountsByStudentClassEqualsIgnoreCase(String studentClass);

    boolean existsByFirstNameStudentIgnoreCaseAndLastNameStudentIgnoreCase(String firstNameStudent, String lastNameStudent);

}
