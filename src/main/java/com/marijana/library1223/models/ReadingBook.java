package com.marijana.library1223.models;

import com.marijana.library1223.enums.GenreEnum;
import com.marijana.library1223.enums.TopicEnum;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class ReadingBook {
    private String language;
    //private GenreEnum currentGenre;
    private String currentGenre;
    private String readingLevel;

    //setter for the enum field
    public void setCurrentGenre(String currentGenre) {
        this.currentGenre = currentGenre;
    }

    //getter for the enum field
    public String getCurrentGenre() {
        return currentGenre;
    }

}
