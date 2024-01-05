package com.marijana.library1223.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "file_uploads")
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private String contentType;
    private String url;

    public FileDocument(String fileName, String contentType, String url) {
    }

    public FileDocument() {

    }
}
