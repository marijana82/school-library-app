package com.marijana.library1223.repositories;

import com.marijana.library1223.models.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    //-----------IMPLEMENTED-------------------
    List<BookCopy> findByYearPublishedAfter(LocalDate date);

    List<BookCopy> findByDyslexiaFriendly(boolean dyslexia);

    List<BookCopy> findByAudioBook(boolean audio);

    //-----------NOT IMPLEMENTED YET----------------
    boolean existsBookCopiesByBarcode(int barcode);

}
