package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
