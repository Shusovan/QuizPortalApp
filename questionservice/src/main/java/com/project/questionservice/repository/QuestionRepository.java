package com.project.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.questionservice.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>
{
    public List<Question> findByQuizId(String quizId);

    @Query("from Question q where q.questionId IN :questionId")
    public List<Question> findByQuestionIdList(List<String> questionId);
}
