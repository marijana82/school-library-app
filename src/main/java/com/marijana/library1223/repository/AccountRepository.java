package com.marijana.library1223.repository;

import com.marijana.library1223.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
