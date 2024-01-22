package com.marijana.library1223.repositories;

import com.marijana.library1223.models.FileDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public interface FileUploadRepository extends JpaRepository<FileDocument, String> {
    Optional<FileDocument> findByFileName(String fileName);

}
