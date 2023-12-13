package com.marijana.library1223.repositories;

import com.marijana.library1223.models.AuthorBook;
import com.marijana.library1223.models.AuthorBookKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, AuthorBookKey> {

    //custom query to find all AuthorBooks that belong to a certain author
    List<AuthorBook> findAllByIdAuthor(Long idAuthor);

    //custom query to find all AuthorBooks that belong to a certain Book
    List<AuthorBook> findAllByIdBook(Long idBook);
}
