package com.project.questionservice.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.questionservice.exception.QuestionNotFoundException;
import com.project.questionservice.exception.QuizNotFoundException;
import com.project.questionservice.model.Question;
import com.project.questionservice.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService
{
    /*
     * dependency injection : Field type
     */
    @Autowired
    private QuestionRepository questionRepository;

    /*
     * Method Description : add and save Questions
     */
    @Override
    public Question addQuestions(Question question) 
    {
        return questionRepository.save(question);
    }

    /*
     * Method Description : Get all Questions
     * return : List of Questions
     */
    @Override
    public List<Question> getAllQuestion() 
    {
        List<Question> question = questionRepository.findAll();
        return question;
    }

    /* 
     * Method Description : Fetch all questions of a quiz using quizId, throws exception if quizId does not exists
     * @param quizId
     * return : list of Questions for particular Quiz
     */ 
    @Override
    public List<Question> getAllQuestionsOfQuiz(String quizId) throws QuizNotFoundException
    {
        List<Question> questions = questionRepository.findByQuizId(quizId);

        if(questions.isEmpty())
        {
            throw new QuizNotFoundException("Quiz ID "+quizId+" does not exixt");
        }
        return questions;
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

    @Override
    public Question updateQuestion(Long questionId, String quizId, Map<String, String> updates) throws QuestionNotFoundException 
    {
        Question newQuestions = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question ID "+questionId+" does not exist"));

        updates.forEach((key, value) -> {
            switch (key) 
            {
                case "question":
                    newQuestions.setQuestion(value);
                    break;

                case "option1":
                    newQuestions.setOption1(value);
                    break;

                case "option2":
                    newQuestions.setOption2(value);
                    break;

                case "option3":
                    newQuestions.setOption3(value);
                    break;

                case "option4":
                    newQuestions.setOption4(value);
                    break;

                case "correctAnswer":
                    newQuestions.setCorrectAnswer(value);
                    break;
            
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        final Question updatedQuestions = questionRepository.save(newQuestions);

        return updatedQuestions;

    }

    @Override
    public Boolean deleteQuestion(Long questionId) 
    {
        questionRepository.deleteById(questionId);

        return true;
    }
    
}
