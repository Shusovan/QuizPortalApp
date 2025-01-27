package com.project.userservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class Quiz 
{
    
    private Long autoIncrementId;

    private String quizId;

    private String tittle;

    private int duration;

    private List<Question> questions;
}
