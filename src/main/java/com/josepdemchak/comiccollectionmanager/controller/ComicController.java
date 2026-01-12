package com.josepdemchak.comiccollectionmanager.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comics")
public class ComicController {

    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping()
    public ResponseEntity<Page<Comic>> getComics(Pageable pageable){
        Page<Comic> comicPage = comicService.getComics(pageable);
        return ResponseEntity.ok(comicPage);
    }

    @GetMapping("/{isbn}")
    public Comic getComic(@PathVariable String isbn){
        return comicService.getComic(isbn);
    }

    @PostMapping
    public Comic addComic(@Valid @RequestBody Comic comic){
        return comicService.addComic(comic);
    }

    @DeleteMapping("/{isbn}")
    public String deleteComic(@PathVariable String isbn){
        comicService.deleteComic(isbn);
        return "Deleted successfully"; 
    }

    @PutMapping("/{isbn}")
    public Comic updateComic(@PathVariable String isbn, @Valid @RequestBody Comic comic){
        return comicService.updateComic(isbn, comic);
    }

    @GetMapping("/publisher/{publisher}")
    public ResponseEntity<Page<Comic>> getComicsByPublisher(@PathVariable String publisher, Pageable pageable){
        Page<Comic> comicPage = comicService.getComicsByPublisher(publisher, pageable);
        return ResponseEntity.ok(comicPage);
    }

    @GetMapping("/publisher/{publisher}/count")
    public long getCountByPublisher(@PathVariable String publisher){
        return comicService.getCountByPublisher(publisher);
    }

    @GetMapping("/publisher/count")
    public Map<String, Long> getCountForAllPublishers(){
        return comicService.getCountForAllPublishers();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Comic>> getComicsByTitle(String title, Pageable pageable){
        Page<Comic> comicPage = comicService.getComicsByTitle(title, pageable);
        return ResponseEntity.ok(comicPage);
    }

}
