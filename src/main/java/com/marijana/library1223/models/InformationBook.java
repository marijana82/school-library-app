package com.marijana.library1223.models;

import jakarta.persistence.*;

@Entity
@Table(name="information_books")
public class InformationBook {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="education_level")
    private String educationLevel; //or use enumeration here
    @Column(name="topic")
    private String topic;
}
