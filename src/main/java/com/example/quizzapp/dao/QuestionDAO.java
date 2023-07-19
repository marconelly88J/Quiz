package com.example.quizzapp.dao;

import com.example.quizzapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                   // Interface        <Table_Name, Primary Key>
public interface QuestionDAO extends JpaRepository<Question, Integer> {

        // findByCategory - Spring DATA JPA razume da je to kolona u tabeli i sam pravi upit za kolonu jer koristim rec 'Category'
        List<Question> findByCategory(String category);

        // nativeQuery: This parameter is set to true, indicating that the custom query is written in native SQL (not JPQL) since it starts with "SELECT * FROM".
        // Native queries are written in the native SQL dialect of the underlying database.
        @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
        List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
