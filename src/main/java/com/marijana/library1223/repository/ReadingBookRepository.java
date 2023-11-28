package com.marijana.library1223.repository;

import com.marijana.library1223.model.ReadingBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingBookRepository extends JpaRepository<ReadingBook, Long> {
}
