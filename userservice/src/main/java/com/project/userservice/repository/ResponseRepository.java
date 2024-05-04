package com.project.userservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.userservice.model.UserResponse;

public interface ResponseRepository extends JpaRepository<UserResponse, Long>
{
    //select * from userresponse where quiz_attempt_attempt_auto_id = (select attempt_auto_id from quiz_attempt where attemptId = :attemptId AND user_quiz_auto_incr_id = :user_quiz_auto_incr_id)
    @Query("from UserResponse ur where ur.quizAttempt.attemptAutoId = :attemptAutoId")
    public List<UserResponse> getResponseOfUser(Long attemptAutoId);
}
