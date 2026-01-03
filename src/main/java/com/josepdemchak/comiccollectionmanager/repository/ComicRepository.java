package com.josepdemchak.comiccollectionmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josepdemchak.comiccollectionmanager.entity.Comic;

public interface ComicRepository extends JpaRepository<Comic, String> {

}
