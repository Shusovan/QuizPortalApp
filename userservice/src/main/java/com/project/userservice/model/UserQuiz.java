package com.project.userservice.model;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    //@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH })
    @Column(name = "userId")
    //@JoinColumn(name = "fk_userId", referencedColumnName = "userId")
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rowCreated")
    private Timestamp rowCreated;

    @Column(name = "isDisabled")
    private boolean isDisabled = false;
}
