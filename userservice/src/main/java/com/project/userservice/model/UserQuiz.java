package com.project.userservice.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data

@Table(name = "UserQuiz")
public class UserQuiz 
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long autoIncrId;

    @Column(name = "quizId")
    private String quizId;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rowCreated")
    private Timestamp rowCreated;

    @Column(name = "isDisabled")
    private boolean isDisabled = false;

    public UserQuiz(User user, String quizId)
    {
        this.user = user;
        this.quizId = quizId;
    }

    public UserQuiz()
    {}

}
