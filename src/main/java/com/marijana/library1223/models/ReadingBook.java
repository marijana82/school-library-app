package com.marijana.library1223.models;

import jakarta.persistence.Embeddable;
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

    public ReadingBook() {}
    public ReadingBook(String language, String currentGenre, String readingLevel) {}

}
