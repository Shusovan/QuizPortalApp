package com.project.questionservice.Service;

import java.util.List;
import java.util.Map;

import com.project.questionservice.exception.QuestionNotFoundException;
import com.project.questionservice.exception.QuizNotFoundException;
import com.project.questionservice.model.Question;

public interface QuestionService 
{

    Question addQuestions(Question question);

    List<Question> getAllQuestion();

    List<Question> getAllQuestionsOfQuiz(String quizId) throws QuizNotFoundException;

    Map<String, Question> fetchCorrectAnswer(String questionId) throws QuestionNotFoundException;

    Question updateQuestion(Long questionId, String quizId, Map<String, String> updates) throws QuestionNotFoundException;

    Boolean deleteQuestion(Long questionId) throws QuestionNotFoundException;
    
}
