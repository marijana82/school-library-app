package com.marijana.library1223.models;

import com.marijana.library1223.enums.BookTypeEnum;
import com.marijana.library1223.interfaces.BookTypeInterface;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
@Entity
@Table(name="information_books")
public class InformationBook implements BookTypeInterface {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="education_level")
    private String educationLevel;
    @Column(name="topic")
    private String topic;

    @Override
    public BookTypeEnum getBookType() {
        return BookTypeEnum.INFORMATION_BOOK;
    }

}
