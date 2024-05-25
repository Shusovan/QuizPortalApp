from sqlalchemy import and_
from sqlalchemy.sql import exists
import requests
import os
import json

from model.Question import Questions
from model.user_model import User
from utility.SQLAlchemyEngine import SQLEngine
from model.User_Quiz_Attempt_Response import UserQuizAttemptResponse
from model.User_Score import Score
from model.Report import Report

session = SQLEngine()

def get_all_users() -> list:
    
    # building the URL of UserService
    url = os.environ.get("USER_SERVICE") + "user/getAllUsers"

    # calling the API(GET) from url
    response = requests.get(url)

    # converting response to json
    user_json_list = response.json()

    # returns list of users (** -> kwargs)
    user_list = [User(**user_json) for user_json in user_json_list]

    return user_list

'''
GET userId
GET quizId
GET attemptId
GET correctAnswer
GET userResponse
Calculate score for users
Save result in DB
'''

def get_scores(params : dict) -> Score:
    
    url = os.environ.get("USER_SERVICE") + "response/getUserQuizAttemptResponse"

    response = requests.get(url, params=params)

    response_json_list = response.json()

    response_list = []

    userScore_list = []

    for i in response_json_list:
        response_object = UserQuizAttemptResponse(**i)
        response_list.append(response_object)
        
        response_correctAnswer = response_object.question.get("correctAnswer")
        response_userResponse = response_object.userResponse.get("userResponse")

        if response_correctAnswer == response_userResponse:
            userScore_list.append(1)

        else:
            userScore_list.append(0)

    userScore_sum = sum(userScore_list)

    record_exists = session.query(Score).filter(and_(Score.attemptId == params.get("attemptId"), Score.userId == params.get("userId"), Score.quizId == params.get("quizId"))).first()
    
    if record_exists:
        record_exists.score = userScore_sum

    else:
        userScore = Score(userId = params.get("userId"), quizId = params.get("quizId"), attemptId = params.get("attemptId"), score = userScore_sum)
        session.add(userScore)

    session.commit()

    return userScore


'''
    Description : GET complete report of user
    @param : userId, quizId, attemptId
    return : Report
'''
def get_report(params : dict) -> Report:
    
    '''
        fetching getUserQuizAttemptResponse details
    '''
    url = os.environ.get("USER_SERVICE") + "response/getUserQuizAttemptResponse"
    
    # getUserQuizAttemptResponse
    response = requests.get(url, params = params)

    # getUserQuizAttemptResponse json
    response_json = response.json()

    response_list = []

    questionList = []

    for i in response_json:
        response_object = UserQuizAttemptResponse(**i)
        response_list.append(response_object)
        
        response_correctAnswer = response_object.question.get("correctAnswer")
        response_userResponse = response_object.userResponse.get("userResponse")

        # creating object for Question
        question_object = Questions(**response_object.question)

        if response_correctAnswer == response_userResponse:
            question_object.isCorrect = True

        else:
            question_object.isCorrect = False

        question_object.response = response_userResponse

        questionList.append(question_object)


    '''
        Description : fetching User details 
        @param userId
    '''
    user_url = os.environ.get("USER_SERVICE") + "user/getUser/" + str(params.get("userId"))

    # get user
    response_user = requests.get(user_url, params = params)

    # user json
    response_user_json = response_user.json()

    # fetching user details from database
    fname = response_user_json.get("firstName")
    lname = response_user_json.get("lastName")
    email = response_user_json.get("email")


    '''
        Description : fetching Quiz details
        @param quizId
    '''
    quiz_url = os.environ.get("QUIZ_SERVICE") + "quiz/get/" + str(params.get("quizId"))

    # get Quiz
    response_quiz = requests.get(quiz_url, params = params)

    # quiz json
    response_quiz_json = response_quiz.json()

    # fetching quiz details from database
    tittle = response_quiz_json.get("tittle")
    duration = response_quiz_json.get("duration")
    quizId = response_quiz_json.get("quizId")


    '''
        Description : fetching Attempt details
        @param userId
        @param quizId
    '''
    attempt_url = os.environ.get("USER_SERVICE") + "response/attempt/" + str(params.get("userId")) + "/" + str(params.get("quizId"))

    response_attempt = requests.get(attempt_url, params = params)

    # attempt json
    response_attempt_json = response_attempt.json()

    # fetching attempt details from Database
    attempt = response_attempt_json.get("attemptID")


    '''
        Description : fetching Score details
        @param userId
        @param quizId
        @param attemptId
    '''
    score_object = session.query(Score).filter(and_(Score.attemptId == params.get("attemptId"), Score.userId == params.get("userId"), Score.quizId == params.get("quizId"))).first()

    total_score = score_object.score


    '''
        set and return details for user Report
    '''

    userReport = Report(firstName=fname, lastName=lname, email=email, tittle=tittle, duration=duration, attempt=attempt, question_list=questionList, final_score=total_score, quizId=quizId)


    # report_object = Report()

    # report_object.firstName = fname
    # report_object.lastName = lname
    # report_object.email = email
    # report_object.tittle = tittle
    # report_object.duration = duration
    # report_object.attempt = attempt
    # report_object.question_list = questionList
    # report_object.final_score = total_score

    return userReport