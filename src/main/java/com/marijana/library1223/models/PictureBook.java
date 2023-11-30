package com.marijana.library1223.models;

import jakarta.persistence.*;

@Entity
@Table(name="picture_books")
public class PictureBook {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="type")
    private String type;
    @Column(name="name_illustrator")
    private String nameIllustrator;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameIllustrator() {
        return nameIllustrator;
    }

    public void setNameIllustrator(String nameIllustrator) {
        this.nameIllustrator = nameIllustrator;
    }
}
