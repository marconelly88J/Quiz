package com.example.quizzapp.dao;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {


}
