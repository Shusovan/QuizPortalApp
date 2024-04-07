package com.project.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;

public interface UserService 
{

    List<User> getAllUsers();

    User addUser(User user);

    User getUserById(String userId);

    User getSingleUser(String userId);

    // User assignQuizToUser(String userId, String quizId);
    
}
