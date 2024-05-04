from dataclasses import dataclass

from model import user_attempts

@dataclass
class UserResponse:
    
    userQuestionAutoID : int    
    
    QuizAttempt : user_attempts

    questionId : int
    
    userResponse : str