package com.marijana.library1223.controllers;

import com.marijana.library1223.services.PictureBookService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureBookController {

    private final PictureBookService pictureBookService;

    public PictureBookController(PictureBookService pictureBookService) {
        this.pictureBookService = pictureBookService;
    }

    //post
    //get-all
    //get-id
    //put
    //patch
}
