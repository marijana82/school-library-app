package com.marijana.library1223.configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class HandleBindingErrors {

    public static ResponseEntity<Object> handleBindingErrors(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append("\n");
        }
        return ResponseEntity.badRequest().body(stringBuilder.toString());
    }

}
