package com.project.questionservice.exception;

public class QuestionNotFoundException extends Exception
{
    String resourceName;
    String fieldName;
    long fieldValue;

    public QuestionNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    
}
