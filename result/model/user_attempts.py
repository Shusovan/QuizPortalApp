from dataclasses import dataclass

from sqlalchemy import DateTime

from model import user_quiz

@dataclass
class Attempts():

    attemptAutoId : int

    attemptID : int

    quizDate : DateTime

    userQuiz : user_quiz

    isDisabled = False