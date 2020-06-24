package com.cimb.Exam.controller;

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
import com.cimb.Exam.entity.Categories;
import com.cimb.Exam.entity.Movie;

@RestController
@RequestMapping("/categories")
@CrossOrigin

public class CategoriesController {
  
	@Autowired
	private CategoriesRepo categoriesRepo;
	
	@GetMapping
	private Iterable <Categories> getCategories (){
		return categoriesRepo.findAll();
	}
	
	@PostMapping 
	private Categories addCategories(@RequestBody Categories categories) {
		categories.setId(0);
		return categoriesRepo.save(categories);
	}
	
	@DeleteMapping ("/{id}")
	public void deleteCategories (@PathVariable int id) {
		Optional <Categories> findProduct = categoriesRepo.findById(id);
		categoriesRepo.deleteById(id);
	}
	
	@PutMapping  ("/edit")
	public Categories editCategories (@RequestBody Categories categories) {
		return categoriesRepo.save(categories);
	}
	
}
