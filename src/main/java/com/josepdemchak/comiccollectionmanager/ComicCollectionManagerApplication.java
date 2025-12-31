package com.josepdemchak.comiccollectionmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComicCollectionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicCollectionManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ComicRepository comicRepository){
		return args -> {
			Comic hornsbyAndHalo = new Comic(
				"978-1-5343-3035-1",
				"Honsby & Halo",
				"Image",
				"TPB",
				1
			);
			comicRepository.save(hornsbyAndHalo);
		};
	}

}
