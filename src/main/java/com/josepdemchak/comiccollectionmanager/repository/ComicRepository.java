package com.josepdemchak.comiccollectionmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.josepdemchak.comiccollectionmanager.entity.Comic;

public interface ComicRepository extends JpaRepository<Comic, String> {

    List<Comic> findByPublisher(String publisher);

    long countByPublisher(String publisher);

    public interface PublisherCount{
        String getPublisher();
        long getCount();
    }
    @Query("SELECT c.publisher AS publisher, COUNT(c) AS count FROM Comic c GROUP BY c.publisher")
    List<PublisherCount> countComicsByPublisher();

}
