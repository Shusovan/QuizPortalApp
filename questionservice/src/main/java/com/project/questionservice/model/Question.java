package com.project.questionservice.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

@Table(name = "Question")
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionId")
    private Long questionId;

    @Column(name = "quizId")
    private String quizId;

    @Column(name = "questions")
    private String question;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "correctAnswer")
    private String correctAnswer;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Format for JSON serialization
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // Format for JSON serialization
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Override
    public String toString() 
    {
        return "Question [questionId=" + questionId + ", quizId=" + quizId + ", question=" + question + ", option1="
                + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4
                + ", correctAnswer=" + correctAnswer + "]";
    }

    
}
