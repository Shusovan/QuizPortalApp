package com.project.quizservice.repository;

import java.util.List;

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
    @Query("SELECT q FROM Quiz q WHERE q.quizId IN :quizIds")
    public List<Quiz> findByQuizIds(List<String> quizIds);
    
    public List<Quiz> findAllByQuizIdIn(List<String> ids);

    @Modifying
    @Query("DELETE FROM Quiz q WHERE q.quizId = :quizId")
    public void deleteByQuizKey(@Param("quizId")String quizId);
}
