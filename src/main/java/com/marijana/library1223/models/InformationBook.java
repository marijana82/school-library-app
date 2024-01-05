package com.marijana.library1223.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class InformationBook {
    private String educationLevel;
    private String currentTopic;

}
