package com.josepdemchak.comiccollectionmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Comic {

    @Id
    @NotBlank(message = "ISBN must not be blank")
    private String isbn;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Publisher must not be blank")
    private String publisher;
    @NotBlank(message = "Format must not be blank")
    private String format;
    @PositiveOrZero(message = "Volume must be 0 or greater")
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
