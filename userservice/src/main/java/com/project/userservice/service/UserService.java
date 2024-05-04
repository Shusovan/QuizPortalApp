package com.project.userservice.service;

import java.util.List;

import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;
import com.project.userservice.pojo.UserQuizAttemptResponse;
import com.project.userservice.pojo.UserReponseBody;

public interface UserService 
{

    List<User> getAllUsers();

    User addUser(User user);

    User getUserById(String userId);

    List<UserQuiz> getAllUserQuiz();

    User assigneQuizTouser(String userId, String quizId);

    User getUserAndQuiz(String userId);

    boolean deleteUserQuiz(String userId, String quizId);

    Boolean saveUserAttempt(String userId, String quizId, List<UserReponseBody> userResponseList);

    List<UserQuizAttemptResponse> getUserQuizAttemptResponse(String userId, String quizId, int attemptId);
    
}
