package com.cimb.Exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.Exam.entity.Categories;

public interface CategoriesRepo extends JpaRepository <Categories, Integer> {

}
