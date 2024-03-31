package com.project.questionservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questionservice.model.Question;
import com.project.questionservice.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

    @SuppressWarnings("null")
    @Override
    public Question addQuestions(Question question) 
    {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestion() 
    {
        List<Question> question = questionRepository.findAll();

        return question;
    }

    @Override
    public List<Question> getAllQuestionsOfQuiz(String quizId) 
    {
        return questionRepository.findByQuizId(quizId);
    }
    
}
