package com.project.userservice.pojo;

import com.project.userservice.model.Question;
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
