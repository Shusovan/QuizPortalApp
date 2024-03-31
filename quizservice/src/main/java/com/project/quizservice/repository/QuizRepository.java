package com.project.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.quizservice.model.Quiz;

import feign.Param;

@Transactional
public interface QuizRepository extends JpaRepository<Quiz, Long>
{
    public Quiz findByQuizId(String quizId);

    @Modifying
    @Query("DELETE FROM Quiz q WHERE q.quizId = :quizId")
    public void deleteByQuizKey(@Param("quizId")String quizId);
}
