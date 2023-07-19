package com.example.quizzapp.service;

import com.example.quizzapp.dao.QuestionDAO;
import com.example.quizzapp.dao.QuizDAO;
import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.QuestionWrapper;
import com.example.quizzapp.model.Quiz;
import com.example.quizzapp.model.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class QuizService {

        @Autowired
       private final QuizDAO quizDAO;

        @Autowired
       private final QuestionDAO questionDAO;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Optional<Quiz> quiz = quizDAO.findById(id); // ako ne postoji quiz sa tim ID
        List<Question> quizQuestions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : quizQuestions) {
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(), q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3() );
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses) {
            if( response.getResponse().equals(questions.get(i).getCorrect_answer()) )
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
