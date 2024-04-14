package com.project.userservice.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data

@Table(name = "QuizAttempt")
public class QuizAttempt 
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attemptAutoId;

    @Column(name ="attemptID")
    private int attemptID = 1;

    @Temporal(TemporalType.DATE)
    @Column(name = "quizDate")
    private Date quizDate;

    @ManyToOne
    private UserQuiz userQuiz;

    @Column(name = "isDisabled")
    private boolean isDisabled = false;
}
