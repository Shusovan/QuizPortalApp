package com.project.userservice.model;

import lombok.Data;

@Data
public class Question 
{
    private Long questionId;

    private String quizId;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String correctAnswer;
}
