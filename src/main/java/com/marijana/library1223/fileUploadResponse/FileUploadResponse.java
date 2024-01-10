package com.marijana.library1223.fileUploadResponse;

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
