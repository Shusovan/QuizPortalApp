package com.project.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
