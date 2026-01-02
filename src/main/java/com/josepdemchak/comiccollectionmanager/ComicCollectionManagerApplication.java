package com.josepdemchak.comiccollectionmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.josepdemchak.comiccollectionmanager.service.ComicService;

@SpringBootApplication
public class ComicCollectionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicCollectionManagerApplication.class, args);
	}


	/*
	@Bean
    CommandLineRunner testUpdateComic(ComicService comicService) {
        return args -> {
			Comic rocketfell = new Comic(
				"978-1-5343-3352-9",
				"Rocketfellers",
				"Image",
				"TPB",
				1
			);
			comicService.updateComic("978-1-5343-3352-9", rocketfell);
            System.out.println("Comics in database:");
            comicService.getComics().forEach(System.out::println);
        };
    }
	@Bean
    CommandLineRunner testDeleteComic(ComicService comicService) {
        return args -> {
			comicService.deleteComic("978-1-5343-3352-9");
            System.out.println("Comics in database:");
            comicService.getComics().forEach(System.out::println);
        };
    }
	@Bean
    CommandLineRunner testAddComic(ComicService comicService) {
        return args -> {
			Comic rocketfellers = new Comic(
				"978-1-5343-3352-9",
				"RocketFellers",
				"Image",
				"TPB",
				1
			);
			comicService.addComic(rocketfellers);
            System.out.println("Comics in database:");
            comicService.getComics().forEach(System.out::println);
        };
    }
	@Bean
    CommandLineRunner testGetComics(ComicService comicService) {
        return args -> {
            System.out.println("Comics in database:");
            comicService.getComics().forEach(System.out::println);
        };
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
	*/

}
