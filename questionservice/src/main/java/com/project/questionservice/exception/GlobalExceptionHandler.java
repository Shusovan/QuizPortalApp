package com.project.questionservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ApiResponse> handleQuestionNotFoundException(QuestionNotFoundException exception)
    {
        ApiResponse apiResponse = new ApiResponse(new Date(), exception.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiResponse> handleQuizNotFoundException(QuizNotFoundException exception)
    {
        ApiResponse apiResponse = new ApiResponse(new Date(), exception.getMessage(), false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
