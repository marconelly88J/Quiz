package com.example.quizzapp.controller;

import com.example.quizzapp.model.Question;
import com.example.quizzapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "question")
public class QuestionController {


       private final QuestionService questionService;
        @Autowired
        public QuestionController(QuestionService questionService){
            this.questionService = questionService;
        }

        @GetMapping("allQuestions")
        public ResponseEntity <List<Question>> getAllQuestions(){
            return questionService.getAllQuestions();
        }

        @GetMapping("category/{category}")
        public ResponseEntity <List<Question>> getQuestionsByCategory(@PathVariable("category") String category){
            return questionService.getQuestionsByCategory(category);
        }

        @PostMapping("addQuestion")
        public ResponseEntity<String> addQuestion(@RequestBody Question question){
            return questionService.addQuestion(question);

        }

        @DeleteMapping("deleteQuestion/{id}")
        public ResponseEntity<String> deleteQuestionById(@PathVariable("id") Integer id ){
            return questionService.deleteQuestionById(id);
        }

}
