package com.project.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.config.QuizClient;
import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;
import com.project.userservice.model.Quiz;
import com.project.userservice.repository.UserQuizRepository;
import com.project.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserQuizRepository userQuizRepository;

    @Autowired
    private QuizClient quizClient;

    @Override
    public List<User> getAllUsers() 
    {
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User addUser(User user)
    {
        String fname = user.getFirstName();
        String lname = user.getLastName();

        String uId = (fname.substring(0, 3) + lname.substring(0, 3)).toUpperCase();

        user.setUserId(uId);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) 
    {
        User user = userRepository.findByUserId(userId);

        return user;
    }

    @Override
    public User getSingleUser(String userId) 
    {
        User user = userRepository.findByUserId(userId);

        List<Quiz> quiz = quizClient.getAllQuiz();

        user.setQuiz(quiz);

        return user;
        
    }

    // @Override
    // public User assignQuizToUser(String userId, String quizId)
    // {
    //     // Quiz quiz = quizClient.getQuizById(quizId);
    //     User user = userRepository.findByUserId(userId);
        
    //     UserQuiz uQuiz = new UserQuiz();
    //     // uQuiz.setQuizId(quiz);
    //     uQuiz.setUserId(user);

    //     userQuizRepository.save(uQuiz);

    //     List<UserQuiz> userQuizList = userQuizRepository.findByUserId(userId);

    //     List<Quiz> allUserQuiz = new ArrayList<>();
    //     for(UserQuiz uq: userQuizList)
    //     {
    //         allUserQuiz.add(uq.getQuizId());
    //     }

    //     user.setQuiz(allUserQuiz);

    //     return user;



    //     // fetch all quiz of the user List<quiz>
    //     // set list<quiz> to user : private List<Quiz> quiz; using user.setquiz()
    //     // return user

    // }

    
    
}
