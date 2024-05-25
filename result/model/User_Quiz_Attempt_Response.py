from dataclasses import dataclass
from typing import Dict

from model.Question import Questions
from model.user_attempts import Attempts
from model.user_response import UserResponse

@dataclass
class UserQuizAttemptResponse:

    question : Questions

    userResponse : UserResponse

    @classmethod
    def from_dict(cls, data: Dict[str, any]) -> "UserQuizAttemptResponse":
        
        question = Questions(**data.get('question'))

        quizAttempt = Attempts
        userResponse = UserResponse(**data.get('userResponse'))

        
