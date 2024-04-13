package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;
import com.project.userservice.service.UserService;

@RestController
@RequestMapping("/userQuiz")
public class UserQuizController 
{
    @Autowired
    private UserService userService;

    // @Autowired
    // private QuizClient quizClient;

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

    @GetMapping("/getUserAndQuiz")
    public ResponseEntity<User> getUserAndQuiz(@RequestParam String userId)
    {
        //return userId == null ? ResponseEntity.status(HttpStatus.OK).body(userService.getUserAndQuiz(userId): 
        // ResponseEntity.status(HttpStatus.OK).body(userService.getUserAndQuiz(userId));
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserAndQuiz(userId));
    }

    @DeleteMapping("/deleteUserQuiz")
    public boolean deleteUserQuiz(@RequestParam String userId, @RequestParam String quizId)
    {
        return userService.deleteUserQuiz(userId, quizId);
    }
    

    // @GetMapping("/getQuizbyIds")
    // public List<Quiz> getQuizbyIds(@RequestBody RequestedQuizId requestQuizIds)
    // {
    //     System.out.println(requestQuizIds);
    //     return quizClient.getQuizbyIds(requestQuizIds);
    // }

}