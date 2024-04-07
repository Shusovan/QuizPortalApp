package com.project.userservice.config;

import java.util.List;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.userservice.model.Quiz;

import jakarta.websocket.server.PathParam;

@FeignClient(url = "http://localhost:8080", value = "Quiz-Client")
public interface QuizClient 
{
    @GetMapping("/get/{quizid}")
    List<Quiz> getQuizById(@PathVariable(value = "quizid") String quizId);
    

    @GetMapping("/quiz/getAllQuiz")
    List<Quiz> getAllQuiz();

}
