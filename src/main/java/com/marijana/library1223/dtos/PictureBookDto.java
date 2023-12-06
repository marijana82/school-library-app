package com.marijana.library1223.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PictureBookDto {
    private Long id;
    private String type;
    @NotBlank(message = "Please provide name of the illustrator.")
    private String nameIllustrator;

}
