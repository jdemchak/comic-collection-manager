package com.josepdemchak.comiccollectionmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.josepdemchak.comiccollectionmanager.Comic;
import com.josepdemchak.comiccollectionmanager.ComicRepository;

@Service
public class ComicService {
    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository){
        this.comicRepository = comicRepository;
    }

    public List<Comic> getComics(){
        return comicRepository.findAll();
    }

}
