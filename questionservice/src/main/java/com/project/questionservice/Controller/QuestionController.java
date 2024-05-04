package com.project.questionservice.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questionservice.Service.QuestionService;
import com.project.questionservice.exception.QuestionNotFoundException;
import com.project.questionservice.model.Question;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
    @Autowired
    private QuestionService questionService;

    /*
     * API Description : add/create question for a quiz
     */
    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestions(@RequestBody Question question)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestions(question));
    }

    /*
     * API Description : Get all questions
     * return : list of Questions
     */
    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestion()
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getAllQuestion());
    }

    /*
     * API Description : Fetch all questions of a quiz using quizId
     * @param quizId
     * return : list of Questions for particular Quiz
     */
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable String quizId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestionsOfQuiz(quizId));
    }

    /* 
     * API Description : Fetch the correct answer of a question
     * @param questionId
     * return : object
     */

     @GetMapping("/fetchCorrectAnswer")
    public ResponseEntity<Map<String, Question>> fetchCorrectAnswer(@RequestParam(value = "questionId") String questionId) throws QuestionNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.fetchCorrectAnswer(questionId));
    }
}
