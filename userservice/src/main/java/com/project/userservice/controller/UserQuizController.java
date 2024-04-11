package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.userservice.config.QuizClient;
import com.project.userservice.model.Quiz;
import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;
import com.project.userservice.service.UserService;

@RestController
@RequestMapping("/userQuiz")
public class UserQuizController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private QuizClient quizClient;

    @GetMapping("/getAllUsersQuiz")
    public ResponseEntity<List<UserQuiz>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserQuiz());
    }

    @PostMapping("/assigneQuizTouser")
    public ResponseEntity<User> assigneQuizTouser(@RequestParam String userId, @RequestParam String quizId)
    {
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.assigneQuizTouser(userId, quizId));
    }

    

    // @GetMapping("/getQuizbyIds")
    // public List<Quiz> getQuizbyIds(@RequestBody RequestedQuizId requestQuizIds)
    // {
    //     System.out.println(requestQuizIds);
    //     return quizClient.getQuizbyIds(requestQuizIds);
    // }

}