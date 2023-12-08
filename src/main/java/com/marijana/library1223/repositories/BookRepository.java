package com.marijana.library1223.repositories;

import com.marijana.library1223.models.Book;
import com.marijana.library1223.models.InformationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    //-----------IMPLEMENTED--------------------------------------------
    List<Book> findAllBooksByNameAuthorEqualsIgnoreCase(String nameAuthor);
    List<Book> findAllBooksByNameIllustratorEqualsIgnoreCase(String nameIllustrator);
    List<Book> findAllBooksByNameIllustratorAndNameAuthorEqualsIgnoreCase(String nameIllustrator, String type);

    //JPQL = java persistence query language to find all books where the 'currentTopic' field in the embedded InformationBook
    // matches the provided currentTopic:
    @Query("SELECT b FROM Book b WHERE b.informationBook.currentTopic = :currentTopic")
    List<Book> findAllBooksByCurrentTopic(@Param("currentTopic") String currentTopic);


}
