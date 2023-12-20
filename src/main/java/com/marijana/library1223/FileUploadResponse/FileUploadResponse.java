package com.marijana.library1223.FileUploadResponse;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileUploadResponse {
    String fileName;
    String contentType;
    String url;
}
