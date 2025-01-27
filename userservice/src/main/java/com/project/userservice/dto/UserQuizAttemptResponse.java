package com.project.userservice.dto;

import com.project.userservice.model.UserResponse;

import lombok.Data;

@Data
public class UserQuizAttemptResponse 
{

    private Question question;

    private UserResponse userResponse;

    public UserQuizAttemptResponse(Question question, UserResponse userResponse) 
    {
        this.question = question;
        this.userResponse = userResponse;
    }

}
