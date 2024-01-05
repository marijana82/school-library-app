package com.marijana.library1223.services;

import com.marijana.library1223.models.FileDocument;
import com.marijana.library1223.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileStorageService {

    private final FileUploadRepository fileUploadRepository;
    private Path fileStoragePath;
    private final String fileStorageLocation;

    public FileStorageService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository fileUploadRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.fileUploadRepository = fileUploadRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

    }

    public String storeFile(MultipartFile file, String url) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + File.separator + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }
        fileUploadRepository.save(new FileDocument(fileName, file.getContentType(), url));

        return fileName;
    }


    //download one file
    public Resource downloadFile(String fileName) {
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        Resource resource;

        try {
            resource = new UrlResource(path.toUri());

        } catch(MalformedURLException exception) {
            throw new RuntimeException("The file cannot be read.", exception);
        }

        if(resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("The file does not exist or is not readable.");
        }
    }


    //download list
    public List<String> downLoad() {
        // Directory path here
        var list = new ArrayList<String>();
        File folder = new File(fileStorageLocation);
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++){
            if(listOfFiles[i].isFile()){
                String name = listOfFiles[i].getName();
                list.add(name);
            }
        }
        return list;
    }


}



