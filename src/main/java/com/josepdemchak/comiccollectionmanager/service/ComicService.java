package com.josepdemchak.comiccollectionmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josepdemchak.comiccollectionmanager.entity.Comic;
import com.josepdemchak.comiccollectionmanager.exception.ComicNotFoundException;
import com.josepdemchak.comiccollectionmanager.exception.DuplicateComicException;
import com.josepdemchak.comiccollectionmanager.repository.ComicRepository;

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
        if(comicRepository.findById(comic.getIsbn()).isPresent()){
            throw new DuplicateComicException("Comic already exists with ISBN: " + comic.getIsbn());
        } else {
            comicRepository.save(comic);
            return comic;
        }
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

    public List<Comic> getComicsByPublisher(String publisher){
        return comicRepository.findByPublisher(publisher);
    }

    public long getCountByPublisher(String publisher){
        return comicRepository.countByPublisher(publisher);
    }

}
