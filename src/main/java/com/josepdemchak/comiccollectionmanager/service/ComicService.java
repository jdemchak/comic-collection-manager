package com.josepdemchak.comiccollectionmanager.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.josepdemchak.comiccollectionmanager.entity.Comic;
import com.josepdemchak.comiccollectionmanager.exception.ComicNotFoundException;
import com.josepdemchak.comiccollectionmanager.exception.DuplicateComicException;
import com.josepdemchak.comiccollectionmanager.repository.ComicRepository;
import com.josepdemchak.comiccollectionmanager.repository.ComicRepository.PublisherCount;

import jakarta.transaction.Transactional;

@Service
public class ComicService {
    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository){
        this.comicRepository = comicRepository;
    }

    public List<Comic> getAllComics(){
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

    public List<Comic> getAllComicsByPublisher(String publisher){
        return comicRepository.findByPublisher(publisher);
    }

    public Page<Comic> getComicsByPublisher(String publisher, Pageable pageable){
        return comicRepository.findByPublisher(publisher, pageable);
    }

    public long getCountByPublisher(String publisher){
        return comicRepository.countByPublisher(publisher);
    }

    public Map<String, Long> getCountForAllPublishers(){
        return comicRepository.countComicsByPublisher()
            .stream()
            .collect(Collectors.toMap(PublisherCount::getPublisher, PublisherCount::getCount));
    }

    public Page<Comic> getComics(Pageable pageable){
        return comicRepository.findAll(pageable);
    }

    public Page<Comic> getComicsByTitle(String title, Pageable pageable){
        return comicRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

}
