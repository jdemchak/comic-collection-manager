package com.josepdemchak.comiccollectionmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ComicNotFoundException.class)
    public ResponseEntity<String> handleComicNotFound(ComicNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateComicException.class)
    public ResponseEntity<String> handleDuplicateComic(DuplicateComicException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

}
