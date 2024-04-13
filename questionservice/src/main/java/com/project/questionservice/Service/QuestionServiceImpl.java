package com.project.questionservice.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questionservice.exception.QuestionNotFoundException;
import com.project.questionservice.model.Question;
import com.project.questionservice.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
    @Autowired
    private QuestionRepository questionRepository;

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

    @Override
    public Map<String, Question> fetchCorrectAnswer(String questionId) throws QuestionNotFoundException
    {
        //Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question", "ID", questionId));
        //question.getCorrectAnswer();
        
        /* Algorithm :
         * create list of questionId
         * get all details for the questionids
         * create Map<questionIds, Question> and map each questionId(key) with Question(value) object
         * this will return all question of specific ID seperated by ','
         */

        List<String> questionIds = Arrays.asList(questionId.split(","));
        List<Question> questions = questionRepository.findByQuestionIdList(questionIds);

        Map<String, Question> map = new HashMap<>();

        for(Question question : questions)
        {
            map.put(question.getQuestionId().toString(), question);
        }
            
        return map;
    }
    
}
