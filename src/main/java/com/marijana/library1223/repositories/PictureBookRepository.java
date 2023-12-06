package com.marijana.library1223.repositories;

import com.marijana.library1223.models.PictureBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureBookRepository extends JpaRepository<PictureBook, Long> {

    //-----------IMPLEMENTED--------------------------------------------

    List<PictureBook> findAllPictureBooksByType(String type);
    List<PictureBook> findAllPictureBooksByNameIllustratorEqualsIgnoreCase(String nameIllustrator);
    List<PictureBook> findAllPictureBooksByNameIllustratorAndTypeEqualsIgnoreCase(String nameIllustrator, String type);
}
