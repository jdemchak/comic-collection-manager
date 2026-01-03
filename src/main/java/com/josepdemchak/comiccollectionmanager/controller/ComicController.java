package com.josepdemchak.comiccollectionmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josepdemchak.comiccollectionmanager.entity.Comic;
import com.josepdemchak.comiccollectionmanager.service.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public List<Comic> getComics(){
        return comicService.getComics();
    }

    @GetMapping("/{isbn}")
    public Comic getComic(@PathVariable String isbn){
        return comicService.getComic(isbn);
    }

    @PostMapping
    public Comic addComic(@RequestBody Comic comic){
        return comicService.addComic(comic);
    }

    @DeleteMapping("/{isbn}")
    public String deleteComic(@PathVariable String isbn){
        comicService.deleteComic(isbn);
        return "Deleted successfully"; 
    }

    @PutMapping("/{isbn}")
    public Comic updateComic(@PathVariable String isbn, @RequestBody Comic comic){
        return comicService.updateComic(isbn, comic);
    }

}
