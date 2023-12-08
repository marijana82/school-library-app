package com.marijana.library1223.models;

import com.marijana.library1223.enums.BookTypeEnum;
import com.marijana.library1223.interfaces.BookType;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public abstract class InformationBook implements BookType {
    private String educationLevel;
    private String topic;

    @Override
    public BookTypeEnum getBookType() {
        return BookTypeEnum.INFORMATION_BOOK;
    }
}
