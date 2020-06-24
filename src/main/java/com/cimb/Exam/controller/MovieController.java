package com.cimb.Exam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.Exam.dao.CategoriesRepo;
import com.cimb.Exam.dao.MovieRepo;
import com.cimb.Exam.entity.Categories;
import com.cimb.Exam.entity.Movie;

@RestController
@RequestMapping("/movie")
@CrossOrigin

public class MovieController {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired 
	private CategoriesRepo categoriesRepo;
	
	@GetMapping 
	public Iterable<Movie> getMovie() {
		return movieRepo.findAll(); 
	}
	
	@PostMapping
	public Movie addMovie (@RequestBody Movie movie) {
		movie.setId(0);
		return movieRepo.save(movie);
	
	}
	
	@DeleteMapping ("/{id}")
	public void deleteMovie (@PathVariable int id) {
		Optional <Movie> findMovie = movieRepo.findById(id);
		movieRepo.deleteById(id);
	}
	
	@PutMapping  ("/{movieId}")
	public Movie editMovie (@RequestBody Movie movie, @PathVariable int movieId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		movie.setId(movieId);
		movie.setCategories(findMovie.getCategories());
		return movieRepo.save(movie);
		
	}
	
	@PutMapping ("{movieId}/categories/{categoriesId}")
	public Movie addMovieToCategories (@PathVariable int movieId, @PathVariable int categoriesId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		Categories findcategories = categoriesRepo.findById(categoriesId).get();
		
		if (findMovie.getCategories().contains(findcategories)) {
			throw new RuntimeException ("Movie Already Added to Categories");
		}
		
		findMovie.getCategories().add(findcategories);
		return movieRepo.save(findMovie);
	}
	
	@DeleteMapping("/deleteCategories/{categoriesId}")
	public void deleteCategories (@PathVariable int categoriesId ) {
		Categories findCategories = categoriesRepo.findById(categoriesId).get();
		
		findCategories.getMovie().forEach(movie -> {
			List <Categories> movieCategories = movie.getCategories();
			movieCategories.remove(findCategories);
			movieRepo.save(movie);
		});
		
		findCategories.setMovie(null);
		categoriesRepo.deleteById(categoriesId);
	
	}
	
	@DeleteMapping ("/{movieId}/deleteCategories/{categoriesId}")
	public Movie deleteCategoriesFromMovie (@PathVariable int movieId, @PathVariable int categoriesId) {
		Categories findCategories = categoriesRepo.findById(categoriesId).get();
		Movie findMovie = movieRepo.findById(movieId).get();
	
		findMovie.getCategories().remove(findCategories);
		return movieRepo.save(findMovie);
		
	}
}
