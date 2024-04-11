package com.project.quizservice.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/get/{quizid}")
    public Quiz getQuizById(@PathVariable(value = "quizid") String quizId)
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

    public Quiz getAllQuizById(@PathVariable String quizId)
    {
        return quizService.getAllQuizById(quizId);
    }

    @DeleteMapping("/delete/{quizId}")
    public void deleteById(@PathVariable(value = "quizId") String quizId)
    {
        quizService.deleteById(quizId);
    }

    // public ResponseEntity<Quiz> assignQuizToUser(@PathVariable String userId, @PathVariable String quizId)
    // {
    //     return ResponseEntity.status(HttpStatus.ACCEPTED).body(quizService.assignQuizToUser(userId, quizId));
    // }

    @GetMapping("/getQuizbyIds")
    public List<Quiz> getQuizbyIds(@RequestParam String requestQuizIds)
    {
        System.out.println(requestQuizIds);
        List<String> requestedIds = Arrays.asList(requestQuizIds.split(","));
        return quizService.getQuizzesByIds(requestedIds);
    }

    @GetMapping("/getAllQuizIds")
    public List<Quiz> getAllQuizIds()
    {
        return quizService.getAllQuizes();
    }

    @GetMapping("/getQuizWithQuestions")
    public List<Quiz> getQuizWithQuestions(@RequestParam String quizIds)
    {
        // return ResponseEntity.status(HttpStatus.FOUND).body(quizService.getQuizWithQuestions(quizIds));
        return quizService.getQuizWithQuestions(quizIds);
    }
}
