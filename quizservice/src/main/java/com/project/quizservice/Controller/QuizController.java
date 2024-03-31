package com.project.quizservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizservice.Service.QuizService;
import com.project.quizservice.model.Quiz;

@RestController
@RequestMapping("/quiz")

public class QuizController 
{
    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public Quiz addQuiz(@RequestBody Quiz quiz)
    {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/getAllQuiz")
    public ResponseEntity<List<Quiz>> getAllQuiz()
    {
        return ResponseEntity.ok().body(quizService.getAllQuiz());
    }

    @GetMapping("/get/{id}")
    public Quiz getQuizById(@PathVariable(value = "id") String quizId)
    {
        return quizService.getQuizById(quizId);
    }

    /*@DeleteMapping("/remove/quiz")
    public ResponseEntity<?> deleteById(@RequestBody Quiz quiz)
    {
        if (quizService.deleteByQuizId(quiz.getQuizId()) == true)
        {
           return  new ResponseEntity<>("{'message':'success'}", HttpStatus.OK);
        }
        return new ResponseEntity<>("QUIZ NOT FOUND", HttpStatus.NOT_FOUND);

    }*/

    @DeleteMapping("/delete/{quizId}")
    public void deleteById(@PathVariable(value = "quizId") String quizId)
    {
        quizService.deleteById(quizId);
    }
}
