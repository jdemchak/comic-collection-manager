package com.josepdemchak.comiccollectionmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comic {

    @Id
    private String isbn;
    private String title;
    private String publisher;
    private String format;
    private Integer volume;

    public Comic() {
    }

    public Comic(String isbn, String title, String publisher, String format, Integer volume) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.format = format;
        this.volume = volume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Comic [isbn=" + isbn + ", title=" + title + ", publisher=" + publisher + ", format=" + format
                + ", volume=" + volume + "]";
    }

}
