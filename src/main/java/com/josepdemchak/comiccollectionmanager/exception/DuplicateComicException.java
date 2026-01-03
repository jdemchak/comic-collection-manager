package com.josepdemchak.comiccollectionmanager.exception;

public class DuplicateComicException extends RuntimeException{

    public DuplicateComicException(){
        super();
    }

    public DuplicateComicException(String message){
        super(message);
    }

}
