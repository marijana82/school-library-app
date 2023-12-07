package com.marijana.library1223.models;

import com.marijana.library1223.enums.BookTypeEnum;
import com.marijana.library1223.interfaces.BookTypeInterface;
import jakarta.persistence.*;
import lombok.Data;

@Data
//@Embeddable
@Entity
@Table(name="picture_books")
public class PictureBook implements BookTypeInterface {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "name_illustrator")
    private String nameIllustrator;

    @Override
    public BookTypeEnum getBookType() {
        return BookTypeEnum.PICTURE_BOOK;
    }

}