package com.marijana.library1223.services;

import com.marijana.library1223.dtos.PictureBookDto;
import com.marijana.library1223.models.PictureBook;
import com.marijana.library1223.repositories.BookRepository;
import com.marijana.library1223.repositories.PictureBookRepository;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureBookService {

    private final PictureBookRepository pictureBookRepository;

    public PictureBookService(PictureBookRepository pictureBookRepository) {
        this.pictureBookRepository = pictureBookRepository;
    }

    //createNewPictureBook
    public PictureBookDto createNewPictureBook(PictureBookDto pictureBookDto) {
        PictureBook pictureBook = new PictureBook();
        pictureBook.setType(pictureBookDto.getType());
        pictureBook.setNameIllustrator(pictureBookDto.getNameIllustrator());
        pictureBookRepository.save(pictureBook);
        pictureBookDto.setId(pictureBook.getId());
        return pictureBookDto;
    }



    //showAllPictureBooks
    public List<PictureBookDto> showAllPictureBooks() {
        List<PictureBook> pictureBookList = pictureBookRepository.findAll();
        List<PictureBookDto> pictureBookDtoList = new ArrayList<>();
        for(PictureBook pictureBook : pictureBookList) {
            PictureBookDto pictureBookDto = transferPictureBookToPictureBookDto(pictureBook);
            pictureBookDtoList.add(pictureBookDto);
        }
        return pictureBookDtoList;
    }

    //showAllPictureBooksByNameIllustrator
    public List<PictureBookDto> showAllPictureBooksByNameIllustrator(String nameIllustrator) {
        List<PictureBook> pictureBookList = pictureBookRepository.findAllPictureBooksByNameIllustratorEqualsIgnoreCase(nameIllustrator);
        List<PictureBookDto> pictureBookDtoList = new ArrayList<>();
        for(PictureBook pictureBook : pictureBookList) {
            PictureBookDto pictureBookDto = transferPictureBookToPictureBookDto(pictureBook);
            pictureBookDtoList.add(pictureBookDto);
        }
        return pictureBookDtoList;
    }

    //showAllPictureBooksByType
    public List<PictureBookDto> showAllPictureBooksByType(String type) {
        List<PictureBook> pictureBookList = pictureBookRepository.findAllPictureBooksByType(type);
        List<PictureBookDto> pictureBookDtoList = new ArrayList<>();
        for(PictureBook pictureBook : pictureBookList) {
            PictureBookDto pictureBookDto = transferPictureBookToPictureBookDto(pictureBook);
            pictureBookDtoList.add(pictureBookDto);
        }
        return pictureBookDtoList;
    }

    //showAllPictureBooksByNameIllustratorAndType
    public List<PictureBookDto> showAllPictureBooksByNameIllustratorAndType(String nameIllustrator, String type) {
        List<PictureBook> pictureBookList = pictureBookRepository.findAllPictureBooksByNameIllustratorAndTypeEqualsIgnoreCase(nameIllustrator, type);
        List<PictureBookDto> pictureBookDtoList = new ArrayList<>();
        for(PictureBook pictureBook : pictureBookList) {
            PictureBookDto pictureBookDto = transferPictureBookToPictureBookDto(pictureBook);
            pictureBookDtoList.add(pictureBookDto);
        }
        return pictureBookDtoList;
    }






    //helper methods ...........................................

    //helper method - transfer PictureBook to PictureBookDto
    private PictureBookDto transferPictureBookToPictureBookDto(PictureBook pictureBook) {
        PictureBookDto pictureBookDto = new PictureBookDto();
        pictureBookDto.setId(pictureBook.getId());
        pictureBookDto.setType(pictureBook.getType());
        pictureBookDto.setNameIllustrator(pictureBook.getNameIllustrator());
        return pictureBookDto;
    }

    //helper method - transfer PictureBookDto to PictureBook
    private PictureBook transferPictureBookDtoToPictureBook(PictureBookDto pictureBookDto) {
        PictureBook pictureBook = new PictureBook();
        pictureBook.setId(pictureBookDto.getId());
        pictureBook.setType(pictureBookDto.getType());
        pictureBook.setNameIllustrator(pictureBookDto.getNameIllustrator());
        return pictureBook;
    }


}
