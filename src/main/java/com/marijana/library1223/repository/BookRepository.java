package com.marijana.library1223.repository;

import com.marijana.library1223.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
