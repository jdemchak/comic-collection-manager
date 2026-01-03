package com.josepdemchak.comiccollectionmanager.exception;

public class ComicNotFoundException extends RuntimeException{

    public ComicNotFoundException(){
        super();
    }

    public ComicNotFoundException(String message){
        super(message);
    }

}
