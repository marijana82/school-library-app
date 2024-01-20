package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllBooksByNameAuthorEqualsIgnoreCase(String nameAuthor);
    List<Book> findAllBooksByNameIllustratorEqualsIgnoreCase(String nameIllustrator);
    List<Book> findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase(String nameIllustrator, String type);

}
