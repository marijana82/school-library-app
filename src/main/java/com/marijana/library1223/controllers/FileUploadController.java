package com.marijana.library1223.controllers;

import com.marijana.library1223.services.FileStorageService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    FileUpload singleFileUpload(@RequestParam("file") MultipartFile file, Long id) {

    //to create url
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                //.path(String.valueOf(file))
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();

        String contentType = file.getContentType();
        String fileName = fileStorageService.storeFile(file, url, id);

        return new FileUpload();
        //return new FileUpload(fileName, contentType, url);

    }



}
