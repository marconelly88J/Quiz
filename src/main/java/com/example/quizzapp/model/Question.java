package com.example.quizzapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Question {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String category;
        private String question;
        private String answer1;
        private String answer2;
        private String answer3;
        private String correct_answer;


}
