package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.userservice.config.QuizClient;
import com.project.userservice.model.Quiz;
import com.project.userservice.model.User;
import com.project.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private QuizClient quizClient;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/addUsers")
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "userId") String userId)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserById(userId));
    }

    // @GetMapping("/getUser/{userId}/quiz/{quizId}")
    // public ResponseEntity<User> assignQuizToUser(@PathVariable(value = "userId") String userId, @PathVariable(value = "quizId") String quizId)
    // {
    //     return ResponseEntity.status(HttpStatus.OK).body(userService.assignQuizToUser(userId, quizId));
    // }

    @GetMapping("/getAllQuizes")
    public List<Quiz> getAllQuizes()
    {
        return quizClient.getAllQuiz();
    }

    // @GetMapping("/getQuizById/{quizId}")
    // public Quiz getQuizById(@PathVariable(value = "quizId") String quizId)
    // {
    //     return quizClient.getQuizById(quizId);
    // }

    // @GetMapping("/all/{userId}")
    // public User getSingleUser(@PathVariable String userId)
    // {
    //     return userService.getSingleUser(userId);
    // }

    
}