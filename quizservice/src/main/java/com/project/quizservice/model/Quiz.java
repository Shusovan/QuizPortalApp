package com.project.quizservice.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
import lombok.Data;

@Entity(name = "Quiz")
@Data

@Table(name = "Quiz")

public class Quiz 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "autoIncrementId")
    private Long autoIncrementId;

    @Column(name = "quizId")
    private String quizId;

    @Column(name = "Tittle")
    private String tittle;

    @Column(name = "duration_minutes")
    private int duration;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH })
    @JoinColumn(name = "fk_quizId", referencedColumnName = "quizId")
    transient private List<Question> questions;
}
