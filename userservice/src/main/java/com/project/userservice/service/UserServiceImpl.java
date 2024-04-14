package com.project.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.config.QuizClient;
import com.project.userservice.model.Quiz;
import com.project.userservice.model.QuizAttempt;
import com.project.userservice.model.User;
import com.project.userservice.model.UserQuiz;
import com.project.userservice.model.UserResponse;
import com.project.userservice.pojo.UserReponseBody;
import com.project.userservice.repository.QuizAttemptRepository;
import com.project.userservice.repository.ResponseRepository;
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

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private ResponseRepository responseRepository;

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

    // @Override
    // public User getSingleUser(String userId) 
    // {
    //     User user = userRepository.findByUserId(userId);

    //     List<Quiz> quiz = quizClient.getAllQuiz();

    //     user.setQuiz(quiz);

    //     return user;
        
    // }

    @Override
    public List<UserQuiz> getAllUserQuiz() {
        
        return userQuizRepository.findAll();
     }

    @Override
    public User assigneQuizTouser(String userId, String quizId) 
    {
        
        User user = userRepository.findByUserId(userId);
        UserQuiz uq = new UserQuiz(user,quizId);
        userQuizRepository.save(uq);


        List<String> allUserQuiz = userQuizRepository.findQuizIdsByUserId(user.getUserId());
        List<Quiz> quizList = quizClient.getQuizWithQuestions( String.join(",", allUserQuiz));
        user.setQuiz(quizList);
        return user;
    }

    @Override
    public User getUserAndQuiz(String userId) 
    {
        User user = userRepository.findByUserId(userId);
        List<String> allUserQuiz = userQuizRepository.findQuizIdsByUserId(user.getUserId());
        List<Quiz> quizList = quizClient.getQuizWithQuestions( String.join(",", allUserQuiz));
        user.setQuiz(quizList);

        return user;
    }

    @Override
    public boolean deleteUserQuiz(String userId, String quizId) 
    {
        // User userToDelete = userRepository.findByUserId(userId);
        try
        {
            userQuizRepository.deleteUserQuizById(userId, quizId);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("error in deleting the quiz ::" + quizId + " for user::" + userId);
            return false;
        }
        
    }

    @Override
    public Boolean saveUserAttempt(String userId, String quizId,List<UserReponseBody> userResponseList) 
    {
        try {
        /*
         * Fetching User and Quiz from UserQuiz
         */
        User user = userRepository.findByUserId(userId);
        UserQuiz userQuiz = userQuizRepository.findUserQuiz(user.getUserAutoIncrementId().toString(), quizId);

        /*
         * Creating a variable(Integer) to get count of attempts
         */
        Integer maxAttempt = quizAttemptRepository.maxAttemptId(userQuiz.getAutoIncrId());

        /*
         * Check attempts of User
         * If user has not attempted (maxAttempt == null), assign default attempt = 1
         * save the attempt
         */
        QuizAttempt qa = new QuizAttempt();
        if(maxAttempt == null)
            qa.setUserQuiz(userQuiz);


        /*
         * Check attempts of User
         * If user has already attempted the quiz (maxAttempt != null), increment the attempt(attemptId + 1)
         * modify and save the attempts
         */
        else
        {
            qa.setAttemptID(maxAttempt + 1);
            qa.setUserQuiz(userQuiz);
            
        }
        quizAttemptRepository.save(qa);

        UserResponse ur = new UserResponse();

        for(UserReponseBody ur1 : userResponseList)
        {
            ur.setQuizAttempt(qa);
            ur.setQuestionId(ur1.getQusetionId());
            ur.setUserResponse(ur1.getUserAnswer());
            responseRepository.save(ur);
        }
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
        
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
