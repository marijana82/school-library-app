package com.marijana.library1223.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reading_books")
public class ReadingBook {
    @Id
    @GeneratedValue
    private Long id;
    private String language;
    private String genre;
    private String readingLevel; //or use enumeration here

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReadingLevel() {
        return readingLevel;
    }

    public void setReadingLevel(String readingLevel) {
        this.readingLevel = readingLevel;
    }
}
