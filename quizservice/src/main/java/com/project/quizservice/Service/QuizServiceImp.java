package com.project.quizservice.Service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.quizservice.Config.QuestionClient;
import com.project.quizservice.model.Quiz;
import com.project.quizservice.repository.QuizRepository;


@Service
public class QuizServiceImp implements QuizService
{
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    @Override
    public Quiz addQuiz(Quiz quiz) 
    {
        String str = quiz.getTittle();
        
        String[] arr = str.split(" ");

        int l = arr.length;

        String cId = "";

        for(int i=0; i<l; i++)
        {
            cId += arr[i].charAt(0);
        }

        cId += Math.round(Math.random()*1000) ;

        quiz.setQuizId(cId);
        
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuiz() 
    {
        List<Quiz> quiz = quizRepository.findAll();

        List<Quiz> allQuiz = quiz.stream()
                                 .map(q -> {
                                    q.setQuestions(questionClient.getQuestionOfQuiz(q.getQuizId()));
                                    return q;
                                 }).collect(Collectors.toList());
        
        if(quiz.isEmpty())
            System.out.println("No Quiz Found");
        
        return allQuiz;
    }

    /*@SuppressWarnings("null")
    @Override
    public Boolean deleteById(Long quizId) 
    {
        try
        {
            Optional<Quiz> found = quizRepository.findByQuizId(quizId);
            if (found.isPresent())
            {
                quizRepository.deleteById(quizId);
            }
            else
            {
                return false;
            }
            
        }
        
        catch(Exception exception)
        {
            return false;
        }

        return true;
    }*/

    @Override
    public List<Quiz> getQuizById(String quizId) 
    {
        List<Quiz> q = quizRepository.findByQuizId(quizId);

        return q;
    }

    @Override
    public void deleteById(String quizId) 
    {
        quizRepository.deleteByQuizKey(quizId);
    }

    @Override
    public List<Quiz> getAllQuizById(String quizId) 
    {
        List<Quiz> quiz = quizRepository.findByQuizId(quizId);

        return quiz;
    }
    
    public List<Quiz> getQuizzesByIds(List<String> quizIds) {
        return quizRepository.findAllByQuizIdIn(quizIds);
    }

    @Override
    public List<Quiz> getAllQuizes() {
        // TODO Auto-generated method stub
        return quizRepository.findAll();

    }

}
