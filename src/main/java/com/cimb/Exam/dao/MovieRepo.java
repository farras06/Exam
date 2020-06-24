package com.cimb.Exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cimb.Exam.entity.Movie;

public interface MovieRepo extends JpaRepository <Movie, Integer> {

}
