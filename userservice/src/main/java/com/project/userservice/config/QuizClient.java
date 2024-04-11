package com.project.userservice.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.userservice.model.Quiz;

@FeignClient(url = "http://localhost:8080", value = "Quiz-Client")
public interface QuizClient 
{
    @GetMapping("/quiz/getQuizbyIds")
    // @RequestMapping(method = RequestMethod.GET, value = "/quiz/getQuizbyIds")
    List<Quiz> getQuizbyIds(@RequestParam String requestQuizIds);

    @GetMapping("/quiz/getQuizWithQuestions")
    List<Quiz> getQuizWithQuestions(@RequestParam String quizIds);

    @GetMapping("/quiz/getAllQuiz")
    List<Quiz> getAllQuiz();

}
