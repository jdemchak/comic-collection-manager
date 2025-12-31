package com.josepdemchak.comiccollectionmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, String> {

}
