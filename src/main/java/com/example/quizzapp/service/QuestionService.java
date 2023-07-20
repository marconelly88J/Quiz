package com.example.quizzapp.service;

import com.example.quizzapp.dao.QuestionDAO;
import com.example.quizzapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

        private final QuestionDAO questionDAO;

        @Autowired
        public QuestionService(QuestionDAO questionDAO){
                this.questionDAO = questionDAO;
        }


        public ResponseEntity<List<Question>> getAllQuestions() {
                try {
                        List<Question> questions = questionDAO.findAll();

                        if (questions.isEmpty()) {
                                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No questions found, return 404
                        }

                        return new ResponseEntity<>(questions, HttpStatus.OK); // Return the list of questions
                } catch (Exception e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Server error, return 500
                }
        }

        public  ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
                try {
                        return new ResponseEntity<>(questionDAO.findByCategory(category), HttpStatus.OK) ;
                }catch (Exception e) {
                        e.printStackTrace();
                }
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;

        }

        public ResponseEntity<String> addQuestion(Question question) {
                try{
                        questionDAO.save(question);
                }catch (Exception e){
                        e.printStackTrace();
                }
                return new ResponseEntity<>("success", HttpStatus.CREATED);
        }

        public ResponseEntity <String> deleteQuestionById(Integer id) {
                try {
                        questionDAO.deleteById(id);
                }catch (Exception e){
                        e.printStackTrace();
                }
                return new ResponseEntity<>("delete success", HttpStatus.ACCEPTED);

        }
}
