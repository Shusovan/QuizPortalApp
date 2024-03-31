package com.project.quizservice.Service;

import java.util.List;

import com.project.quizservice.model.Quiz;

public interface QuizService 
{

    Quiz addQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

    Quiz getQuizById(String quizId);

    void deleteById(String quizId);
   
}
