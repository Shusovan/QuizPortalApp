package com.project.questionservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questionservice.Service.QuestionService;
import com.project.questionservice.model.Question;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public Question addQuestions(@RequestBody Question question)
    {
        return questionService.addQuestions(question);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestion()
    {
        return ResponseEntity.ok().body(questionService.getAllQuestion());
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable String quizId)
    {
        return ResponseEntity.ok().body(questionService.getAllQuestionsOfQuiz(quizId));
    }
}
