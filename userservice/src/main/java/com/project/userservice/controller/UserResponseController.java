package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.userservice.model.UserResponse;
import com.project.userservice.pojo.UserQuizAttemptResponse;
import com.project.userservice.pojo.UserReponseBody;
import com.project.userservice.service.UserService;

@RestController
@RequestMapping("/response")
public class UserResponseController 
{
    @Autowired
    private UserService userService;
    
    @PostMapping("/attempt")
    public Boolean saveUserAttempt(@RequestParam String userId, @RequestParam String quizId, @RequestBody List<UserReponseBody> userResponseList)
    {
        return userService.saveUserAttempt(userId, quizId, userResponseList);
    }

    /*
     * Create an API with userId, quizId, attemptId that will give the user response of all questions of that attempt
     * @Param userId
     * @Param quizId
     * @Param attemptId
     * return : 
     */
    @GetMapping("/getUserQuizAttemptResponse")
    public ResponseEntity<List<UserQuizAttemptResponse>> getUserQuizAttemptResponse(@RequestParam String userId, @RequestParam String quizId, @RequestParam int attemptId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserQuizAttemptResponse(userId, quizId, attemptId));
    }
}
