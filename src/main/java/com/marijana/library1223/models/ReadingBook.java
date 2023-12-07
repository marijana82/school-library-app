package com.marijana.library1223.models;

import com.marijana.library1223.enums.BookTypeEnum;
import com.marijana.library1223.interfaces.BookTypeInterface;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
@Entity
@Table(name="reading_books")
public class ReadingBook implements BookTypeInterface {
    @Id
    @GeneratedValue
    private Long id;
    private String language;
    private String genre;
    private String readingLevel; //or use enumeration here

    @Override
    public BookTypeEnum getBookType() {
        return BookTypeEnum.READING_BOOK;
    }


}
