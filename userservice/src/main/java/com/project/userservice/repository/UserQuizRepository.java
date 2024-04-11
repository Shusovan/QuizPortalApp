package com.project.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.userservice.model.Quiz;
import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long>
{
    // public List<UserQuiz> findByUserId(String userId);

    public UserQuiz findByQuizId(String quizId);

    @Query("select quizId from UserQuiz uq where uq.user.userId = :userId")
    public List<String> findQuizIdsByUserId(String userId);
}
