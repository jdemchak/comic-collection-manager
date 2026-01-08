package com.josepdemchak.comiccollectionmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdemchak.comiccollectionmanager.entity.Comic;

public interface ComicRepository extends JpaRepository<Comic, String> {
    
    List<Comic> findByPublisher(String publisher);

    long countByPublisher(String publisher);

}
