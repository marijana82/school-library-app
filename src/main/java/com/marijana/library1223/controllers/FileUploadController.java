package com.marijana.library1223.controllers;

import com.marijana.library1223.FileUploadResponse.FileUploadResponse;
import com.marijana.library1223.services.FileStorageService;
import org.springframework.core.io.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
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
    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) {

        //to create url
        String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();

        //to store file
        String fileName = fileStorageService.storeFile(file, url);

        String contentType = file.getContentType();

        return new FileUploadResponse(fileName, contentType, url);

    }


    //single download
    @GetMapping("/download/one/{fileName}")
    ResponseEntity<Resource> downloadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.downloadFile(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName=" + resource.getFilename()).body(resource);
    }

    //get all names in directory
    @GetMapping("/download/allNames")
    List<String> downloadMultipleFile() {
        return fileStorageService.downLoad();
    }



}
