package com.project.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questionservice.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    public List<Question> findByQuizId(String quizId);
}
