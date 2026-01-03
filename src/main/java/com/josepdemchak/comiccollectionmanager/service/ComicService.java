package com.josepdemchak.comiccollectionmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josepdemchak.comiccollectionmanager.Comic;
import com.josepdemchak.comiccollectionmanager.ComicRepository;
import com.josepdemchak.comiccollectionmanager.exception.ComicNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ComicService {
    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository){
        this.comicRepository = comicRepository;
    }

    public List<Comic> getComics(){
        return comicRepository.findAll();
    }

    public Comic addComic(Comic comic){
        comicRepository.save(comic);
        return comic;
    }

    public Comic getComic(String isbn){
        return comicRepository.findById(isbn)
                .orElseThrow(() ->
                    new ComicNotFoundException("Comic not found with ISBN: " + isbn)
                );
    }

    public Comic deleteComic(String isbn){
        Comic removedComic = getComic(isbn);
        comicRepository.delete(removedComic);
        return removedComic;
    }

    @Transactional
    public Comic updateComic(String isbn, Comic comic){
        Comic update = getComic(isbn);
        update.setTitle(comic.getTitle());
        update.setPublisher(comic.getPublisher());
        update.setFormat(comic.getFormat());
        update.setVolume(comic.getVolume());
        comicRepository.save(update);
        return update;
    }

}
