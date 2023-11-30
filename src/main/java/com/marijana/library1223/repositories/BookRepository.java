package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
