from dataclasses import dataclass

from Question import Question
from user_response import UserResponse

@dataclass
class UserQuizAttemptResponse:

    Question : Question

    userResponse : UserResponse
