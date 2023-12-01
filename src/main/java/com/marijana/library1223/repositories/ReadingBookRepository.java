package com.marijana.library1223.repositories;

import com.marijana.library1223.models.ReadingBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingBookRepository extends JpaRepository<ReadingBook, Long> {
}
