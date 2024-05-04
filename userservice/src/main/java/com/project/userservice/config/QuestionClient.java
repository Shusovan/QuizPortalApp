package com.project.userservice.config;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.userservice.model.Question;

@FeignClient(url = "http://localhost:8082", value = "Question-Client")
public interface QuestionClient 
{
    @GetMapping("/question/fetchCorrectAnswer")
    Map<String, Question> fetchCorrectAnswer(@RequestParam(value = "questionId") String questionIds);
}
