package com.project.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;

import feign.Param;
@Transactional
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long>
{
    // public List<UserQuiz> findByUserId(String userId);

    public UserQuiz findByQuizId(String quizId);

    @Query("select quizId from UserQuiz uq where uq.user.userId = :userId")
    public List<String> findQuizIdsByUserId(String userId);


    @Modifying
    @Query("DELETE FROM UserQuiz uq WHERE uq.user.userId = :userId AND uq.quizId = :quizId")
    public void deleteUserQuizById(@Param("userId") String userId, @Param("quizId")String quizId);
}
