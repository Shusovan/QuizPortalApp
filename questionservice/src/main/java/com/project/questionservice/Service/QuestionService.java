package com.project.questionservice.Service;

import java.util.List;

import com.project.questionservice.model.Question;

public interface QuestionService 
{

    Question addQuestions(Question question);

    List<Question> getAllQuestion();

    List<Question> getAllQuestionsOfQuiz(String quizId);
    
}
