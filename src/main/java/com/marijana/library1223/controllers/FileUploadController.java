package com.marijana.library1223.controllers;

import com.marijana.library1223.FileUploadResponse.FileUploadResponse;
import com.marijana.library1223.services.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@CrossOrigin
@RestController
public class FileUploadController {
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    //single upload
    @PostMapping("single/upload")
    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file, Long id) {

    //to create url
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                //.path(String.valueOf(file))
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();

        String contentType = file.getContentType();
        String fileName = fileStorageService.storeFile(file, url, id);

        return new FileUploadResponse(fileName, contentType, url);

    }

    //single download
    @GetMapping("/download/{fileName}")
    ResponseEntity<Response> downloadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

    }



}
