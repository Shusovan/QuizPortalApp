from psycopg2 import OperationalError
import requests
import os
import json

from model.user_model import User
from utility.SQLAlchemyEngine import SQLEngine
from model.Question import Question
from model.User_Quiz_Attempt_Response import UserQuizAttemptResponse

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

def get_scores(params):
    
    url = os.environ.get("USER_SERVICE") + "response/getUserQuizAttemptResponse"

    response = requests.get(url, params=params)

    response_json_list = response.json()

    response_object = UserQuizAttemptResponse(**json.loads(response_json_list))

    print(response_object.Question)
    print(response_object.userResponse)


    