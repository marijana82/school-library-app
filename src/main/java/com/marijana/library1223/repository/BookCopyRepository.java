package com.marijana.library1223.repository;

import com.marijana.library1223.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
}
