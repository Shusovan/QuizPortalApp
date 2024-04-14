package com.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.userservice.model.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long>
{
    @Query("select max(qa.attemptID) from QuizAttempt qa where qa.userQuiz.autoIncrId = :userQuiz_autoIncrId")
    public Integer maxAttemptId(Long userQuiz_autoIncrId);
}
