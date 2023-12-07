package com.marijana.library1223.controllers;

import com.marijana.library1223.dtos.PictureBookDto;
import com.marijana.library1223.services.PictureBookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/picture-books")
public class PictureBookController {
    private final PictureBookService pictureBookService;

    public PictureBookController(PictureBookService pictureBookService) {
        this.pictureBookService = pictureBookService;
    }

    //post mapping
    @PostMapping
    public ResponseEntity<Object> createPictureBook(@Valid @RequestBody PictureBookDto pictureBookDto, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getField());
                stringBuilder.append(" : ");
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append(("\n"));
            }
            return ResponseEntity.badRequest().body(stringBuilder.toString());
        }
        pictureBookService.createNewPictureBook(pictureBookDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + pictureBookDto.getId())
                .toUriString());
        return ResponseEntity.created(uri).body(pictureBookDto);
    }


    //get-all + illustrator + type
    @GetMapping
    public ResponseEntity<List<PictureBookDto>> getAllPictureBooks(
            @RequestParam(value="nameIllustrator", required=false) Optional<String> nameIllustrator,
            @RequestParam(value="type", required=false) Optional<String> type) {

       List<PictureBookDto> pictureBookDtoList;

            //no parameters present
       if(nameIllustrator.isEmpty() && type.isEmpty()) {
           pictureBookDtoList = pictureBookService.showAllPictureBooks();

           //only nameIllustrator parameter is present
       } else if(nameIllustrator.isPresent()) {
           pictureBookDtoList = pictureBookService.showAllPictureBooksByNameIllustrator(nameIllustrator.get());

           //only type parameter is present
       } else if(type.isPresent()) {
           pictureBookDtoList = pictureBookService.showAllPictureBooksByType(type.get());

          //both parameters are present
       } else {
           pictureBookDtoList = pictureBookService.showAllPictureBooksByNameIllustratorAndType(nameIllustrator.get(), type.get());
       }
       return ResponseEntity.ok().body(pictureBookDtoList);
    }


    //get-id
    //put
    //patch
}
