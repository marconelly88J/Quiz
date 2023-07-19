package com.example.quizzapp.controller;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.QuestionWrapper;
import com.example.quizzapp.model.Quiz;
import com.example.quizzapp.model.Response;
import com.example.quizzapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title) {
        ResponseEntity<String> quiz = quizService.createQuiz(category, numQ, title);
        return quiz;
    }

    @GetMapping ("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") Integer id) {
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz( @PathVariable Integer id, @RequestBody List<Response> responses ) {
            return quizService.calculateResult(id, responses);
    }


}

