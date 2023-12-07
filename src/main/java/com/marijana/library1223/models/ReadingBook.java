package com.marijana.library1223.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ReadingBook {
    private String language;
    private String genre;
    private String readingLevel;

}
