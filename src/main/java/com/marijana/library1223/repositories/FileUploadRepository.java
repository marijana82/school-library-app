package com.marijana.library1223.repositories;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
    Optional<FileUpload> findByFileName(String fileName);
}
