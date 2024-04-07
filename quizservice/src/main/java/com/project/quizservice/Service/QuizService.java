package com.project.quizservice.Service;

import java.util.List;

import com.project.quizservice.model.Quiz;

public interface QuizService 
{

    Quiz addQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

    List<Quiz> getQuizById(String quizId);

    void deleteById(String quizId);

    List<Quiz> getAllQuizById(String quizId);

    List<Quiz> getAllQuizes();

    List<Quiz> getQuizzesByIds(List<String> quizIds);

    // Quiz assignQuizToUser(String userId, String quizId);
   
}
